package com.yiyuan.demo.dao;


import com.yiyuan.demo.entiy.mid.UserRole;

import java.util.List;

public interface UserRoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

//    int insert(Long userId, List<String> roleIds);
    List<String> findUserId(Long userId);

    void deleteRoleIdUserId(Long roleId, Long userId);

    void save(Long roleId, Long userId);

   Long selectById(Long userId);
}