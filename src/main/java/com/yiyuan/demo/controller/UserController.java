package com.yiyuan.demo.controller;


import com.yiyuan.demo.entiy.User;
import com.yiyuan.demo.result.AjaxResult;
import com.yiyuan.demo.service.RoleService;
import com.yiyuan.demo.service.UserRoleService;
import com.yiyuan.demo.service.UserService;
import com.yiyuan.demo.service.token.TokenService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /****
     *登录功能
     *
     *
     */
//    @ApiOperation(value = "登录")
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public AjaxResult login(@RequestParam("mobile") String mobile, @RequestParam("password") String password) {
//        AjaxResult<Token> tokenAjaxResult = userService.login(mobile, password);
//        Token token = tokenAjaxResult.getData();
//        User user = userService.selectByMobilePassword(mobile, password);
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

    @PostMapping("/get/{id}")
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
     s sl
     *
     * @描述: 登录页面
     *
     * @params:
     * @return:
     * @date: 2018/9/29 21:20
     */
    @RequestMapping("/toLogin")
    public String toLogin()
    {
        return "login";
    }


}
