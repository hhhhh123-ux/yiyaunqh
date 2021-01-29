package com.yiyuan.demo.service.Impl;

import com.yiyuan.demo.dao.PermissionDao;
import com.yiyuan.demo.entiy.Permission;
import com.yiyuan.demo.service.PermissionService;
import com.yiyuan.demo.utils.CurrentUserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author SongYC
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    PermissionDao permissionDao;

    @Override
    public int insertSelective(Permission record) {
        record.setCreatetime(new Date());
        record.setCreator(CurrentUserUtils.getCurrentUser());
        return permissionDao.insertSelective(record);
    }

    @Override
    public int countselectCode(String code) {
        return permissionDao.countselectCode(code);
    }

    @Override
    public int updateByPrimaryKeySelective(Permission record) {
        record.setEditetime(new Date());
        record.setEditor(CurrentUserUtils.getCurrentUser());
        return permissionDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public Permission selectByPrimaryKey(Long id) {
        return permissionDao.selectByPrimaryKey(id);
    }
}
