package indi.zhangweisep.backstage.config.security;

import com.baomidou.mybatisplus.extension.api.R;
import indi.zhangweisep.backstage.config.filter.TokenAuthenticationFilter;
import indi.zhangweisep.backstage.config.filter.ValidateCodeFilter;
import indi.zhangweisep.backstage.config.properties.LoginTokenProperties;
import indi.zhangweisep.backstage.config.security.handler.CustomAccessDeniedHandler;
import indi.zhangweisep.backstage.config.security.handler.CustomFailHandler;
import indi.zhangweisep.backstage.config.security.handler.CustomSuccessHandler;
import indi.zhangweisep.backstage.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * <p>
 * Security鉴权配置类
 * </p>
 *
 * @author ZhangWei
 * @since 2020/8/14 14:22
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * redis操作接口
     */
    private final StringRedisTemplate redisTemplate;

    /**
     * 用户详细信息服务
     */
    private final UserDetailsService userDetailsService;

    /**
     * 身份验证提供者
     */
    private final UserAuthenticationProvider authenticationProvider;

    /**
     * 验证成功处理类
     */
    private final CustomSuccessHandler customSuccessHandler;

    /**
     * 验证失败处理类
     */
    private final CustomFailHandler customFailHandler;

    /**
     * 拒绝访问处理类
     */
    private final CustomAccessDeniedHandler accessDeniedHandler;

    /**
     * 验证过滤器
     */
    private final ValidateCodeFilter validateCodeFilter;

    /**
     * token属性配置
     */
    private final LoginTokenProperties tokenProperties;

    /**
     * 配置被忽略的URL
     * 这些请求不会要求进行认证
     */
    private final String[] urls = {
            "/system/index",
            "/**/*.html",
            "/**/*.js",
            "/**/*.vue",
            "/**/*.css"
            //"/auth/login"
    };

    /**
     * 配置自定义认证
     * authenticationProvider：用户登录验证
     * userDetailsService：用户基本信息、权限信息
     *
     * @param auth 身份验证
     * @throws Exception 异常
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider)
                .userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * spring security过滤器链
     *
     * @param httpSecurity http安全性
     * @throws Exception 异常
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        //配置登录认证的URL
        String loginUrl = "/login";
        //自定义登录页面
        String loginHtml = "http://127.0.0.1:9300/login";
        //初始化过滤器链
        httpSecurity.authorizeRequests()
                //配置被忽略的请求
                .antMatchers(urls).permitAll()
                //配置需要认证的请求，如果没有认证则跳转到登录页面，loginPoint：配置的登录页面
                .and().formLogin().loginPage(loginHtml).loginProcessingUrl(loginUrl).permitAll()
                //配置登录成功的处理
                .successHandler(customSuccessHandler)
                //配置登录失败处理
                .failureHandler(customFailHandler)
                //允许网页iframe
                .and().headers().frameOptions().disable()
                .and().logout().permitAll()
                //任何请求都需要认证
                .and().authorizeRequests().anyRequest().authenticated()
                .and().cors()
                //关闭跨站请求保护，开放跨域
                .and().csrf().disable()
                //前后端分离采用token,不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //自定义异常处理
                .and().exceptionHandling()
                //重写LoginUrlAuthenticationEntryPoint，在方法中返回JSON，不再做重定向操作
                .authenticationEntryPoint((request, response, authException) ->
                        ResponseUtil.out(response, R.failed("登录已失效，请重新登录").setCode(401), 401))
                //自定义权限拒绝处理类
                .accessDeniedHandler(accessDeniedHandler)
                //自定义过滤器配置
                .and()
                //自定义验证码过滤
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //自定义token过滤
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenProperties, redisTemplate));
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

}
