package com.yiyuan.demo.security.user;


import com.sun.org.apache.bcel.internal.classfile.Code;
import com.yiyuan.demo.result.AjaxResult;
import com.yiyuan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author SongYC
 */
@Component
public class UserDetailsServiceImpl extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    UserService userService;
    @Autowired
     PasswordEncoder passwordEncoder;


    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
       System.out.println("用户名："+s);
        UserDetails loadedUser = userService.selectByName(s);
        if (loadedUser == null) {
            logger.error("UserDetails为空！");
            throw new RuntimeException("用户账户错误，找不到账户!");
        }

        boolean flag = true;

        if (usernamePasswordAuthenticationToken.getCredentials() == null) {
            logger.error("认证信息为空!");
            throw new RuntimeException("认证信息为空!");
        }

        String presentedPassword = usernamePasswordAuthenticationToken.getCredentials().toString();
        System.out.println("用户名：="+presentedPassword);
        // 账户密码校验
        if (!passwordEncoder.matches(presentedPassword, loadedUser.getPassword())) {
            logger.error("密码不正确！");

            flag = false;
        }

        // 其他认证信息校验
        if (false) {
            flag = false;
        }
        // 认证失败
        if (!flag) {
            throw new RuntimeException("用户密码认证失败");
        }
        loadedUser=userService.loadUserByUsername(s);
        return loadedUser;
    }

}
