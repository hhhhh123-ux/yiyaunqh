package com.yiyuan.demo.dao;

import com.yiyuan.demo.entiy.mid.RolePermission;

import java.util.List;

public interface RolePermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    List<String> findRoleId(Long roleId);

    void deleteRoleIdUserId(Long roleId, Long userId);

    RolePermission selectById(Long roleId);


}