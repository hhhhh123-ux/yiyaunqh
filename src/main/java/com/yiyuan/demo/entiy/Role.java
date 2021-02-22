package com.yiyuan.demo.entiy;

import com.google.common.collect.Lists;
import com.yiyuan.demo.utils.StreamUtils;
import com.yiyuan.demo.utils.YamlUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * role
 * @author 
 */
@Data
public class Role implements Serializable {
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 所属父级角色ID
     */
    private Long parentId;

    /**
     * 角色唯一CODE代码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色介绍
     */
    private String intro;

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

    private static final long serialVersionUID = 1L;

    private List<Permission> auths;

    /**
     * 从配置文件中加载权限
     */
    public List<Permission> getAuths() {
//        auths = Lists.newArrayList();
//        Map<String, Object> role = (Map<String, Object>) YamlUtil.getValueByKey(this.code);
//        List<String> list = (List<String>) role.get("code");
//        if (null == list) {
//            list = new ArrayList<>();
//        }
//        list.forEach(auth -> {
//            Permission accountAuthority = new Permission();
//            accountAuthority.setUrl(auth);
//            if (auths.stream().noneMatch(item -> item.getUrl().equals(auth))) {
//                auths.add(accountAuthority);
//            }
//        });
//        setAuths(auths.stream().filter(StreamUtils.distinctByKey(Permission::getUrl)).collect(Collectors.toList()));
        return auths;
    }
}