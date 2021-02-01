package com.yiyuan.demo.service.Impl;

import com.yiyuan.demo.dao.RoleDao;
import com.yiyuan.demo.dao.UserRoleDao;
import com.yiyuan.demo.entiy.Role;
import com.yiyuan.demo.entiy.mid.UserRole;
import com.yiyuan.demo.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaozhiqiang
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    UserRoleDao userRoleDao;
    @Resource
    RoleDao roleDao;
    @Override
    public void insert(Long userId, List<String> roleIds) {
        List<String> list = userRoleDao.findUserId(userId);
        List<String> list1 = userRoleDao.findUserId(userId);
        List<String> stringList1 = list1(list, roleIds);
        for (String s : stringList1) {
            userRoleDao.deleteRoleIdUserId(userId, Long.valueOf(s));
        }
        List<String> stringList = list(list1, roleIds);
        for (String s : stringList) {
            UserRole userRole=new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(Long.valueOf(s));
            userRoleDao.insertSelective(userRole);
        }
}

    @Override
    public Role selectId(Long userId) {
       Long roleId= userRoleDao.selectById(userId);
       Role role= roleDao.selectByPrimaryKey(roleId);
        return role;
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