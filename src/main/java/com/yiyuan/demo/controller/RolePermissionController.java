package com.yiyuan.demo.controller;

import com.yiyuan.demo.result.AjaxResult;
import com.yiyuan.demo.service.RolePermissionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "角色权限管理")
@Slf4j
@RestController
@RequestMapping("/permission/role_menu")
public class RolePermissionController {
    @Autowired
    RolePermissionService rolePermissionService;


    /****
     *添加角色-菜单
     *
     *
     */
    @PostMapping(value = "/rolemenu")
    @ResponseBody
    public AjaxResult RoleUser(@RequestParam Long roleId, @RequestBody List<String> menuIds) {
        rolePermissionService.insert(roleId, menuIds);
        return AjaxResult.success(1);
    }
}
