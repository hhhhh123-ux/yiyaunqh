package com.yiyuan.demo.service;




import com.yiyuan.demo.entiy.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SongYC
 */
public interface UserRoleService {

    void insert(Long userId, List<String> roleIds);

    Role selectId(Long userId);

    void save(@Param("roleId")Long roleId,@Param("userId") Long userId);
}
