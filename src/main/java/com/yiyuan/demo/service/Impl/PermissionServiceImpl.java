package com.yiyuan.demo.service.Impl;

import com.sun.media.jfxmedia.logging.Logger;
import com.yiyuan.demo.dao.PermissionDao;
import com.yiyuan.demo.entiy.Permission;
import com.yiyuan.demo.service.PermissionService;
import com.yiyuan.demo.utils.CurrentUserUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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

    @Override
    public List<Permission> findMenu() {
      List<Permission> permissionList=permissionDao.findPermission();
        List<Permission> permissionList1 = new ArrayList<>();
        // 找到所有的一级分类
        for (Permission entity : permissionList) {
            System.out.println("id:"+entity.getParentId());
            if ("0".equals(String.valueOf(entity.getParentId()))) {
                permissionList1.add(entity);
               entity.setChildren(getChildrens(entity, permissionList));

            }
        }
        return permissionList1;
    }
    /**
     * 递归查找所有的下级分类
     * 在这一级别的分类中找下级分类
     *
     * @param root 当前级别的分类
     * @param all  全部分类
     * @return 下一级分类
     */
    public static List<Permission> getChildrens(Permission root, List<Permission> all) {
        List<Permission> children = new ArrayList<>();
        for (Permission a : all) {
            String pid = String.valueOf(a.getParentId());
            String pi = String.valueOf(root.getId());
            if (pid.equals(pi)) {
                children.add(a);
            }

        }

        for (Permission level1Menu :children) {
            level1Menu.setChildren(getChildrens(level1Menu, all));
        }
        if(children.size()==0){
            return null;
        }
        return children;
    }
}
