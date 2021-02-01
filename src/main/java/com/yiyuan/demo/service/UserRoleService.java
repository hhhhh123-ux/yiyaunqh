package com.yiyuan.demo.service;




import com.yiyuan.demo.entiy.Role;

import java.util.List;

/**
 * @author SongYC
 */
public interface UserRoleService {

    void insert(Long userId, List<String> roleIds);

    Role selectId(Long userId);
}
