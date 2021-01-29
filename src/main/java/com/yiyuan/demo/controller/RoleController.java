package com.yiyuan.demo.controller;

import com.yiyuan.demo.entiy.Role;
import com.yiyuan.demo.result.AjaxResult;
import com.yiyuan.demo.service.RoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @author SongYC
 */
@Api(value = "角色功能")
@Slf4j
@RestController
@RequestMapping("/role/form")
public class RoleController {
    @Autowired
   RoleService roleService;


    @PostMapping("/add")
    private AjaxResult add(@RequestBody Role role) throws ParseException {
        return AjaxResult.success(roleService.insertSelective(role));
    }
}
