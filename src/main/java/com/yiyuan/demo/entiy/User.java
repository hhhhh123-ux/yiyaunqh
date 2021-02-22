package com.yiyuan.demo.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.yiyuan.demo.entiy.token.Token;
import com.yiyuan.demo.utils.StreamUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * user
 *
 * @author
 */
@Data
public class User implements UserDetails {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户状态:0=正常,1=禁用
     */
    private Boolean state;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像图片地址
     */
    private String headImgUrl;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 密码加盐
     */
    private String salt;

    /**
     * 登录密码
     */
    private String password;

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

    private String role;

    private List<Role> roles;

    /**
     * 逻辑删除:0=未删除,1=已删除
     */
    private Boolean deleted;

    private Token tokens;

    private static final long serialVersionUID = 1L;

    public User(String username, String encode, List<GrantedAuthority> admin) {
    }

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<Role> roles = this.getRoles();
        List<Permission> permissions = Lists.newArrayList();
        if (null == roles) {
            return Lists.newArrayList();
        }
        roles.forEach(role -> permissions.addAll(role.getAuths()));
        return permissions.stream().filter(StreamUtils.distinctByKey(Permission::getCode)).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}