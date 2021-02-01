package com.yiyuan.demo.controller;


import com.yiyuan.demo.entiy.Permission;
import com.yiyuan.demo.entiy.Role;
import com.yiyuan.demo.entiy.User;
import com.yiyuan.demo.entiy.session.CurrentUser;
import com.yiyuan.demo.entiy.token.Token;
import com.yiyuan.demo.result.AjaxResult;
import com.yiyuan.demo.service.*;
import com.yiyuan.demo.service.token.TokenService;
import com.yiyuan.demo.utils.CurrentUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongYC
 */
@Api(value = "登录功能")
@Slf4j
@Controller
@RequestMapping("/user/form")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    RolePermissionService rolePermissionService;
    /****
     *登录功能
     *
     *
     */
//    @ApiOperation(value = "登录")
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public AjaxResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
//        AjaxResult<Token> tokenAjaxResult = userService.login(username, password);
//        Token token = tokenAjaxResult.getData();
//        User user = userService.selectByMobilePassword(username, password);
//        List<Role> role = roleService.selectByPrimaryKey(user.getId());
//        token.setRoles(role);
//        return AjaxResult.success(tokenAjaxResult);
//    }


    /****
     *添加用户-角色
     *
     *
     */
    @PostMapping(value = "/roleUser")
    @ResponseBody
    public AjaxResult RoleUser(@RequestParam Long userId, @RequestBody List<String> roleIds) {
        userRoleService.insert(userId, roleIds);
        return AjaxResult.success(1);
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public AjaxResult get(@PathVariable String id) {

        return AjaxResult.success(userService.selectByPrimaryKey(Long.valueOf(id)));
    }


    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(@RequestBody User user) {
        return AjaxResult.success(userService.updateByPrimaryKeySelective(user));
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public AjaxResult updateready(@PathVariable String id) {
        User user = userService.selectByPrimaryKey(Long.valueOf(id));
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return AjaxResult.success(userService.deleteupdate(user.getId()));
    }

    /**
     * s sl
     *
     * @描述: 登录页面
     * @params:
     * @return:
     * @date: 2018/9/29 21:20
     */
    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * s sl
     *
     * @描述: 登录页面
     * @params:
     * @return:
     * @date: 2018/9/29 21:20
     */
    @RequestMapping(value = "/index")
    public String toIndex(Model model) {
        CurrentUser currentUser = CurrentUserUtils.getCurrent();
       User user= userService.selectByName(currentUser.getUserName());
        model.addAttribute("user", user);
        //permissionService.selectByPrimaryKey(Long.valueOf(id))
       Role role= userRoleService.selectId(user.getId());
       model.addAttribute("role",role);
       List<Permission> permissionList=rolePermissionService.selectId(role.getId());
        model.addAttribute("menu",permissionList);
        return "index";
    }
}
