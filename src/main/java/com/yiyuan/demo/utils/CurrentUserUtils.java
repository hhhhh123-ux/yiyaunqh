package com.yiyuan.demo.utils;

import com.yiyuan.demo.entiy.User;
import com.yiyuan.demo.entiy.session.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author gaozhiqiang
 */
@Slf4j
public class CurrentUserUtils {

    /**
     * 获取当前登录用户（从session、header、token中获取）
     * @param
     * @return          返回用户封装
     */
    public static CurrentUser getCurrent() {
          User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
          CurrentUser currentUser = new CurrentUser();
          currentUser.setUserId(String.valueOf(user.getId()));
          currentUser.setUserName(user.getUsername());
        return currentUser;
    }


    public static Authentication getCurrentUserAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    /**
     * 获取当前用户
     * @return
     */
    public static Object getCurrentPrincipal(){
        return getCurrentUserAuthentication().getPrincipal();
    }

    public static String getCurrentUser(){
      CurrentUser currentUser= CurrentUserUtils.getCurrent();
       return currentUser.getUserName();
    }
}
