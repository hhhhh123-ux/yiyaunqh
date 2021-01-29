package com.yiyuan.demo.service.token.Impl;


import com.yiyuan.demo.entiy.token.Token;
import com.yiyuan.demo.service.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public Token createToken(Long id, Date date) {
        return null;
    }
}
