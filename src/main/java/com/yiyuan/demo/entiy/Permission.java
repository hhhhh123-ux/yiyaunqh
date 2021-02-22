package com.yiyuan.demo.entiy;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * permission
 * @author 
 */
@Data
public class Permission implements GrantedAuthority {
    /**
     * 权限ID
     */
    private Long id;

    /**
     * 所属父级权限ID
     */

    private Long parentId;

    /**
     * 权限唯一CODE代码
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限介绍
     */
    private String intro;

    /**
     * 权限类别  1 目录，2 菜单 3.按钮
     */
    private Integer category;

    /**
     * URL规则
     */
    private String url;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改时间
     */
    private Date editetime;

    /**
     * 修改人
     */
    private String editor;

    /**
     * 逻辑删除:0=未删除,1=已删除
     */
    private Boolean deleted;

    @Immutable
    private List<Permission> children;
    private static final long serialVersionUID = 1L;

    @Override
    public String getAuthority() {
        return null;
    }
}