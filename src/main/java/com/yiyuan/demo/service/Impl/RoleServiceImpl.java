package com.yiyuan.demo.service.Impl;

import com.yiyuan.demo.dao.RoleDao;
import com.yiyuan.demo.dao.UserRoleDao;
import com.yiyuan.demo.entiy.Role;
import com.yiyuan.demo.entiy.session.CurrentUser;
import com.yiyuan.demo.service.RoleService;
import com.yiyuan.demo.utils.CurrentUserUtils;
import com.yiyuan.demo.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaozhiqiang
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleDao roleDao;
    @Resource
    UserRoleDao userRoleDao;
    @Override
    public int insertSelective(Role record) throws ParseException {
        record.setCreatetime(DateUtil.parseStringToDate(DateUtil.getDate()));
        CurrentUser currentUser= CurrentUserUtils.getCurrent();
        record.setCreator(String.valueOf(currentUser.getUserName()));
        return roleDao.insertSelective(record);
    }

    @Override
    public List<Role> selectByPrimaryKey(Long id) {
        List<Role> roleList=new ArrayList<>();
        List<String> ids=userRoleDao.findUserId(id);
        for(String string:ids) {
        Role role=roleDao.selectByPrimaryKey(Long.valueOf(string));
        roleList.add(role);
        }
        return roleList;
    }
}
