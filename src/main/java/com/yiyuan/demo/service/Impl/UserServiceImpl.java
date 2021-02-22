package com.yiyuan.demo.service.Impl;

import com.yiyuan.demo.dao.*;
import com.yiyuan.demo.entiy.Permission;
import com.yiyuan.demo.entiy.Role;
import com.yiyuan.demo.entiy.User;
import com.yiyuan.demo.entiy.token.Token;
import com.yiyuan.demo.result.AjaxResult;
import com.yiyuan.demo.service.UserService;
import com.yiyuan.demo.utils.CurrentUserUtils;
import com.yiyuan.demo.utils.DateUtil;
import com.yiyuan.demo.utils.RandomUtil;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    @Resource
    TokenDao tokenDao;
    @Resource
    UserRoleDao userRoleDao;
    @Resource
    RoleDao roleDao;
    @Resource
    RolePermissionDao rolePermissionDao;
    @Resource
    PermissionDao permissionDao;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int insertSelective(User record) throws ParseException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        record.setPassword(passwordEncoder.encode(record.getPassword()));
        record.setSalt(RandomUtil.random32());
        record.setCreator(CurrentUserUtils.getCurrentUser());
        record.setCreatetime(DateUtil.parseStringToDate(DateUtil.getDate()));
        record.setDeleted(false);
        record.setState(false);
        int a = userDao.insert(record);
//        record.getId();
//        record.getRole();
//        userRoleDao.save(Long.valueOf(record.getRole()), record.getId());
        return a;
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }

    @Override
    public int deleteupdate(Long id) {
        return userDao.deleteupdate(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User selectByMobilePassword(String mobile, String password) {
        return userDao.selectByMobilePassword(mobile, password);
    }

    @Override
    public User selectByName(String username) {
        return userDao.selectByName(username);
    }

    @Override
    public AjaxResult<Token> login(String username, String password) {

        if (username == null && password == null) {
            return AjaxResult.failed("手机号和密码不能为空");
        }
        User user = userDao.selectByMobilePassword(username, password);
        if (user == null) {
            return AjaxResult.failed("手机号和密码错误");
        }

        //生成token
        return AjaxResult.success(operateToKen(user, user.getId()));
    }

    public Token operateToKen(User user, Long id) {
        Token token = tokenDao.selectByPrimaryKeyUser(id);

        String TokenStr = "";
        Date date = new Date();
        int nowTime = (int) (date.getTime() / 1000);
        TokenStr = creatToken(id, date);
        if (null == token) {
            token = new Token();
            token.setId(Long.valueOf(user.getMobile()));
            token.setUserid(id);
            token.setToken(TokenStr);
            tokenDao.insertSelective(token);
        } else {

            TokenStr = creatToken(id, date);
            token.setToken(TokenStr);
            tokenDao.updateByPrimaryKey(token);
        }
        return token;
    }


    private String creatToken(Long userId, Date date) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                // 设置header
                .setHeaderParam("alg", "HS256").setIssuedAt(date)
                // 设置签发时间
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 60))
                .claim("userId", String.valueOf(userId))
                // 设置内容
                .setIssuer("lws")
                .signWith(signatureAlgorithm, "iwqjhda8232bjgh432");
        // 设置签发;
        // 签名，需要算法和key
        String jwt = builder.compact();
        return jwt;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //根据用户名查询用户
        User user = userDao.selectByName(s);
        Token token = operateToKen(user, user.getId());

        List<String> roleId = userRoleDao.findUserId(user.getId());
        List<Role> list = new ArrayList<>();
        List<Permission> permissionList = new ArrayList<>();
        for (String id : roleId) {
            Role role = roleDao.selectByPrimaryKey(Long.valueOf(id));
            list.add(role);
            List<String> permissionDaoRoleId = rolePermissionDao.findRoleId(Long.valueOf(id));
            for (String ids : permissionDaoRoleId) {
                Permission permission = permissionDao.selectByPrimaryKey(Long.valueOf(ids));
                permissionList.add(permission);
            }
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        token.setRoles(list);
        user.setTokens(token);
        return user;
    }
}
