package com.yiyuan.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiyuan.demo.security.CustomizeAuthenticationEntryPoint;
import com.yiyuan.demo.security.user.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SongYC
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConf extends WebSecurityConfigurerAdapter {
    @Resource
    CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    AccessFailureHandler accessFailureHandler;
    @Resource
    LoginSuccessHandler loginSuccessHandler;
@Resource
UserDetailsServiceImpl userDetailsService;
    @Resource
    LoginFailureHandler loginFailureHandler;

    @Resource
    private ObjectMapper objectMapper;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        //配置认证方式
        auth.authenticationProvider(userDetailsService);
    }


    /**
     * 配置web的拦截器
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //关闭csrf保护功能
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/login","/toLogin").permitAll()
                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(accessFailureHandler)
//                //匿名登录
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .authorizeRequests().anyRequest().fullyAuthenticated()
                //.authorizeRequests().anyRequest().access("@authService.hasPermission(request, authentication)")
                .and()
                .formLogin()
                .loginPage("/user/form/toLogin")//登录页面url
                .loginProcessingUrl("/login")//登录验证url
                .successForwardUrl("/user/form/index")
                .failureForwardUrl("/user/form/failure")
//                .successHandler(loginSuccessHandler)
//                .failureHandler(loginFailureHandler)
                .permitAll()
                .and()
                .logout()
                //退出成功，返回json
                .logoutSuccessHandler((request,response,authentication) -> {
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("code",200);
                    map.put("message","退出成功");
                    map.put("data",authentication);
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                .permitAll();
        // session配置
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false);
    }

    /**
     * 密码生成策略.
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web){
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/static/**","/css/**","/css/**");
    }
}
