package com.yiyuan.demo.service.Impl;

import com.yiyuan.demo.dao.PermissionDao;
import com.yiyuan.demo.dao.RolePermissionDao;
import com.yiyuan.demo.entiy.Permission;
import com.yiyuan.demo.entiy.mid.RolePermission;

import com.yiyuan.demo.service.PermissionService;
import com.yiyuan.demo.service.RolePermissionService;
import com.yiyuan.demo.utils.CurrentUserUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Resource
    RolePermissionDao rolePermissionDao;
    @Resource
    PermissionDao permissionDao;
    @Override
    public void insert(Long roleId, List<String> roleIds) {
        List<String> list = rolePermissionDao.findRoleId(roleId);
        List<String> list1 = rolePermissionDao.findRoleId(roleId);
        List<String> stringList1 = list1(list, roleIds);
        for (String s : stringList1) {
            rolePermissionDao.deleteRoleIdUserId(roleId, Long.valueOf(s));
        }
        List<String> stringList = list(list1, roleIds);
        for (String s : stringList) {
            RolePermission rolePermission=new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(Long.valueOf(s));
            rolePermission.setCreatetime(new Date());
            rolePermission.setCreator(CurrentUserUtils.getCurrentUser());
            rolePermissionDao.insertSelective(rolePermission);
        }
    }

    @Override
    public List<Permission> selectId(Long roleId) {
        List<String> permissionId=rolePermissionDao.findRoleId(roleId);

        List<Permission> permissionList=new ArrayList<>();

        for(String permissionid:permissionId){
            Permission permisssion=permissionDao.selectByPrimaryKey(Long.valueOf(permissionid));
            permissionList.add(permisssion);
        }
        return permissionList;
    }



    public List<String> list1(List<String> list, List<String> roleIds) {
        List<String> list1 = new ArrayList<>();
        if (roleIds.containsAll(list)) {
            assert true;
        } else {
            list.removeAll(roleIds);
            System.out.println(list);
            for (String s : list) {
                list1.add(s);
            }
        }
        return list1;
    }

    public List<String> list(List<String> list, List<String> roleIds) {
        List<String> list1 = new ArrayList<>();
        if (list.containsAll(roleIds)) {
            assert true;
        } else {
            roleIds.removeAll(list);
            System.out.println(roleIds);
            for (String s : roleIds) {
                list1.add(s);
            }
        }

        return list1;
    }
}
