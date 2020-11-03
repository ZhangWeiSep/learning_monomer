package indi.zhangweisep.backstage.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 登录令牌配置
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/26 9:44
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "learning.token")
public class LoginTokenProperties {

    /**
     * 是否开启单点登录
     */
    private Boolean sdl = Boolean.FALSE;

    /**
     * 令牌到期时间
     */
    private Integer tokenExpireTime = 60;

    /**
     * 保存登录时间,登录状态过期时间
     */
    private Integer saveLoginTime = 7;

    /**
     * 限制用户登陆错误次数（次）
     */
    private Integer loginTimeLimit = 10;

    /**
     * 错误超过次数后多少分钟后才能继续登录（分钟）
     */
    private Integer loginAfterTime = 10;

}
