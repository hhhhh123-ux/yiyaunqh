package com.yiyuan.demo.service;

import com.yiyuan.demo.entiy.Role;
import com.yiyuan.demo.entiy.User;

import java.text.ParseException;
import java.util.List;

/**
 * @author SongYC
 */
public interface RoleService {
    int insertSelective(Role record) throws ParseException;
    /**
     * @author SongYC
     */
    List<Role> selectByPrimaryKey(Long id);
    List<Role> selectByAllList(Role role);

    List<Role> findAll();

}
