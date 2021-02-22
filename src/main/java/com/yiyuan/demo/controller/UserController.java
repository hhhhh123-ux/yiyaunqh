package com.yiyuan.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.util.PageObjectUtil;
import com.yiyuan.demo.entiy.Permission;
import com.yiyuan.demo.entiy.Role;
import com.yiyuan.demo.entiy.User;
import com.yiyuan.demo.entiy.session.CurrentUser;
import com.yiyuan.demo.entiy.token.Token;
import com.yiyuan.demo.page.PageHelp;
import com.yiyuan.demo.result.AjaxResult;
import com.yiyuan.demo.service.*;
import com.yiyuan.demo.service.token.TokenService;
import com.yiyuan.demo.utils.CurrentUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private String prefix = "system/user/";

    @RequestMapping(value = "/list")
    public String List() {
        return prefix + "user";
    }

    @RequestMapping(value = "/main")
    public String main() {
        //userService.findAll();
        return "main";
    }

    /**
     * @描述 用户数据
     * @date 2018/9/15 12:30
     */
    @RequestMapping(value = "/tableList",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult list(User user) {
        List<User> users = userService.findAll();
        return AjaxResult.success(users);
    }

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
        User user = userService.selectByName(currentUser.getUserName());
        model.addAttribute("user", user);
        //permissionService.selectByPrimaryKey(Long.valueOf(id))
        Role role = userRoleService.selectId(user.getId());
        model.addAttribute("role", role);
        List<Permission> permissionList = rolePermissionService.selectId(role.getId());
        System.out.println(permissionList);
        model.addAttribute("menus", permissionList);
        return "index";
    }

    @RequestMapping(value = "/failure")
    public String failure(Model model){
        model.addAttribute("failure","T");
        return "login";
    }


    /****
     *保存功能
     *
     *
     */

    //@PreAuthorize("hasAuthority('system.user.save')")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(User user) {
        JSONObject object = new JSONObject();
        object.put("user", user);
        log.info("注册人", user);
        try {
            int a=userService.insertSelective(user);
            userRoleService.save(Long.valueOf(user.getRole()),user.getId());
            return AjaxResult.success(a);
        } catch (Exception e) {
            log.error("个人用户注册,异常：" + e.toString());
            return AjaxResult.failed();
        }
    }


    /**
     * @描述 添加用户页面
     * @date 2018/9/15 18:46
     */
    @RequestMapping("/toAdd")
    //@PreAuthorize("hasAuthority('system.user.save')")
    public String toaddUser(Model model) {
        Map<String, Object> role_post_dept = getRole_Post_Dept();
        model.addAttribute("roles", role_post_dept.get("role"));
        return  "system/user/save";
    }

    public Map<String, Object> getRole_Post_Dept() {
        Map<String, Object> map = new HashMap<>();
//        角色
        List<Role> roles = roleService.selectByAllList(new Role());
        map.put("role", roles);
        return map;
    }
}
