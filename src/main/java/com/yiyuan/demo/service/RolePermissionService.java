package com.yiyuan.demo.service;
import com.yiyuan.demo.entiy.Permission;
import com.yiyuan.demo.entiy.Role;
import com.yiyuan.demo.entiy.mid.RolePermission;

import java.awt.*;
import java.util.List;

public interface RolePermissionService {
    void insert(Long roelId, List<String> menuIds);
    List<Permission> selectId(Long roleId);
    /**
     * 根据数据显示菜单
     *
     * @return 返回树状图
     */
//    List<RolePermission> findAllMenu();
}
