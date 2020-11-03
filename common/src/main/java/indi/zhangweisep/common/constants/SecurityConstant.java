package indi.zhangweisep.common.constants;

/**
 * <p>
 * security键配置
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/29 16:41
 */
public interface SecurityConstant {

    /**
     * token分割
     */
    String BEARER_TYPE = "Bearer ";

    /**
     * token参数头
     */
    String AUTHORIZATION = "Authorization";

    /**
     * 权限参数头
     */
    String AUTHORITIES = "authorities";

    /**
     * 用户选择token保存时间参数头
     */
    String SAVE_LOGIN = "saveLogin";

    /**
     * 交互token前缀key
     */
    String TOKEN_PRE = "LEARNING_TOKEN_PRE:";

    /**
     * 用户token前缀key 单点登录使用
     */
    String USER_TOKEN = "LEARNING_USER_TOKEN:";

    /**
     * 外部功能交互token前缀key
     */
    String OUTSIDER_TOKEN_PRE = "OUTSIDER_TOKEN_PRE:";

    /**
     * 外部用户token前缀key 单点登录使用
     */
    String OUTSIDER_USER_TOKEN = "OUTSIDER_USER_TOKEN:";

    /**
     * 角色前缀
     */
    String ROLE = "ROLE_";

}
