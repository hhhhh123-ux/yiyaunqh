package com.yiyuan.demo.entiy.token;

import com.yiyuan.demo.entiy.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * token
 * @author 
 */
@Data
public class Token implements Serializable {
    private Long id;

    private Long userid;

    private String token;

    private List<Role> roles;

    private static final long serialVersionUID = 1L;
}