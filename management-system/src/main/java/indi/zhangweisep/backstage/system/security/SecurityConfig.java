//package indi.zhangweisep.backstage.system.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
//
//
///**
// * <p>
// * Security鉴权配置类
// * </p>
// *
// * @author: ZhangWei
// * @since: 2020/8/14 14:22
// */
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    /**
//     * 配置被忽略的URL
//     * 这些请求不会要求进行认证
//     */
//    private final String[] urls = {
//            "/system/index", "/**/*.html", "/**/*.js", "/**/*.vue", "/**/*.css"
//    };
//
//    /**
//     * 登录网址
//     * 配置登录认证的URL
//     */
//    private final String loginUrl = "/system/login";
//
//    /**
//     * 自定义登录页面
//     */
//    private final String loginHtml = "http://127.0.0.1:9300/login";
//
//
//    /**
//     * spring security过滤器链
//     *
//     * @param httpSecurity http安全性
//     * @return {@link SecurityWebFilterChain}
//     */
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity httpSecurity) {
//        //配置自定义登录页面
//        RedirectServerAuthenticationEntryPoint loginPoint = new RedirectServerAuthenticationEntryPoint(loginHtml);
//        httpSecurity.authorizeExchange()
//                //配置那些请求被忽略
//                .pathMatchers(urls).permitAll()
////                .pathMatchers("/user/**").hasRole("ADMIN")
//                .and().httpBasic()
//                //配置需要认证的请求，如果没有认证则跳转到登录页面，loginPoint：配置的登录页面
//                .and().formLogin().loginPage(loginUrl).authenticationEntryPoint(loginPoint)
//                //配置其它请求都必须经过认证（登录成功）之后才能访问
//                .and().authorizeExchange().anyExchange().authenticated()
//                //禁用csrf模式
//                .and().csrf().disable()
//                //禁用默认的logout
//                .logout().disable();
//        return httpSecurity.build();
//    }
//
//}
