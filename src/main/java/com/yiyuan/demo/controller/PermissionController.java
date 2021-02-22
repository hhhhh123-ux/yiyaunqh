package com.yiyuan.demo.controller;


import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
import com.yiyuan.demo.entiy.Permission;
import com.yiyuan.demo.eunm.CsEnum;
import com.yiyuan.demo.result.AjaxResult;
import com.yiyuan.demo.service.PermissionService;
import com.yiyuan.demo.service.RolePermissionService;
import io.swagger.annotations.Api;
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
@Api(value = "权限管理")
@Slf4j
@Controller
@RequestMapping("/permission/menu")
public class PermissionController {
    @Autowired
    PermissionService permissionService;
    @Autowired
    RolePermissionService rolePermissionService;

    private String prefix = "system/menu/";
    @PostMapping("/save")
    public AjaxResult save(@RequestBody Permission permission)  {
         int result=permissionService.countselectCode(permission.getCode());
         if(result!=0){
             throw new  RuntimeException("唯一CODE代码");
         }
        if(permission.getCategory().equals(CsEnum.menu.Menu_ParentId_One.getValue())){
              permission.setParentId(Long.valueOf(CsEnum.menu.Menu_PaeentId.getValue()));
        }

        return AjaxResult.success(permissionService.insertSelective(permission));
    }

    @PostMapping("/update")
    public AjaxResult update(@RequestBody Permission permission){
        return AjaxResult.success(permissionService.updateByPrimaryKeySelective(permission));
    }

    @GetMapping("/get/{id}")
    public AjaxResult get(@PathVariable String id){
        return AjaxResult.success(permissionService.selectByPrimaryKey(Long.valueOf(id)));
    }

    /**
     * 菜单多级页面（菜单）
     *
     * @param
     * @param
     * @return 返回
     */
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult select(Permission permission) {
        List<Permission> permissionList = permissionService.findMenu();
        return AjaxResult.success(permissionList);
    }


    @RequestMapping(value = "/list")
    public String List(Model model) {
        List<Permission> permissionList = permissionService.findMenu();
        model.addAttribute("permissionList",permissionList);
        return "system/menu/menu";
    }
}
