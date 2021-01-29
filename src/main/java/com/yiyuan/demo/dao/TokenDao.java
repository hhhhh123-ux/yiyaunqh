package com.yiyuan.demo.dao;


import com.yiyuan.demo.entiy.token.Token;

public interface TokenDao {
    int deleteByPrimaryKey(Long id);

    int insert(Token record);

    int insertSelective(Token record);

    Token selectByPrimaryKey(Long id);

    Token selectByPrimaryKeyUser(Long userid);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}