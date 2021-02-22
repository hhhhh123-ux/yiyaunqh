package com.yiyuan.demo.controller;

import com.yiyuan.demo.entiy.Role;
import com.yiyuan.demo.entiy.User;
import com.yiyuan.demo.result.AjaxResult;
import com.yiyuan.demo.service.RoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @author SongYC
 */
@Api(value = "角色功能")
@Slf4j
@Controller
@RequestMapping("/role/form")
public class RoleController {
    @Autowired
    RoleService roleService;
    private String prefix = "system/role/";

    @RequestMapping(value = "/list")
    public String List() {
        return prefix+ "role";
    }

    @PostMapping("/add")
    private AjaxResult add(@RequestBody Role role) throws ParseException {
        return AjaxResult.success(roleService.insertSelective(role));
    }


    /**
     * @描述 用户数据
     * @date 2018/9/15 12:30
     */
    @RequestMapping(value = "/tableList",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult list(Role role) {
        List<Role> roles = roleService.findAll();
        return AjaxResult.success(roles);
    }
}
