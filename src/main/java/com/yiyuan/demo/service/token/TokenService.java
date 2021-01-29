package com.yiyuan.demo.service.token;



import com.yiyuan.demo.entiy.token.Token;

import java.util.Date;


public interface TokenService {
    Token createToken(Long id, Date date);
}
