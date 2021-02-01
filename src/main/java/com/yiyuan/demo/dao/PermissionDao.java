package com.yiyuan.demo.dao;


import com.yiyuan.demo.entiy.Permission;

import java.security.Permissions;
import java.util.List;

public interface PermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    int countselectCode(String code);

    List<Permission> findPermission();
}