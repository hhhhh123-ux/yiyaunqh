package com.yiyuan.demo.service;

import com.yiyuan.demo.entiy.User;
import com.yiyuan.demo.entiy.token.Token;
import com.yiyuan.demo.result.AjaxResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.text.ParseException;
import java.util.List;

public interface UserService extends UserDetailsService {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record) throws ParseException;

    User selectByPrimaryKey(Long id);

    User selectByMobilePassword(@Param("mobile") String mobile, @Param("password") String password);

    User selectByName(@Param("username") String username);

    AjaxResult<Token> login(@Param("username") String username, @Param("password") String password);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int deleteupdate(Long id);

    List<User> findAll();
}
