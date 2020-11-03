## 项目环境

* JDK版本：java_1.8
* spring-boot版本：2.3.2.RELEASE
spring security核心组件
SecurityContextHolder：提供对SecurityContext的访问
SecurityContext,：持有Authentication对象和其他可能需要的信息
AuthenticationManager 其中可以包含多个AuthenticationProvider
ProviderManager对象为AuthenticationManager接口的实现类
AuthenticationProvider 主要用来进行认证操作的类 调用其中的authenticate()方法去进行认证操作
Authentication：Spring Security方式的认证主体
GrantedAuthority：对认证主题的应用层面的授权，含当前用户的权限信息，通常使用角色表示
UserDetails：构建Authentication对象必须的信息，可以自定义，可能需要访问DB得到
UserDetailsService：通过username构建UserDetails对象

作者：破地瓜
链接：http://www.imooc.com/article/287269
来源：慕课网

