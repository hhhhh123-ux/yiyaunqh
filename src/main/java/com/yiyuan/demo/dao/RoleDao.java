package com.yiyuan.demo.dao;

import com.yiyuan.demo.entiy.Role;

import java.util.List;

public interface RoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectByAllList(Role role);

    List<Role> findAll();
}