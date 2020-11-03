package indi.zhangweisep.common.constants;

/**
 * 常量
 */
public interface CommonConstant {

    /**
     * 用户默认头像
     */
    String USER_DEFAULT_AVATAR = "https://s1.ax1x.com/2018/05/19/CcdVQP.png";

    /**
     * 普通用户
     */
    Integer USER_TYPE_NORMAL = 0;

    /**
     * 管理员
     */
    Integer USER_TYPE_ADMIN = 1;

    /**
     * 正常状态
     */
    Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    Integer DEL_FLAG = 1;

    /**
     * 顶部菜单类型权限
     */
    Integer PERMISSION_NAV = -1;

    /**
     * 页面类型权限
     */
    Integer PERMISSION_PAGE = 0;

    /**
     * 操作类型权限
     */
    Integer PERMISSION_OPERATION = 1;

    /**
     * 菜单
     */
    String MENU = "0";

    /**
     * 成功标记
     */
    Integer SUCCESS = 0;

    /**
     * 失败标记
     */
    Integer FAIL = -1;

    /**
     * 验证码前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

    /**
     * 手机验证码前缀
     */
    String DEFAULT_SMS_KEY = "DEFAULT_SMS_KEY_";

    /**
     * 后端工程名
     */
    String BACK_END_PROJECT = "laboratory";
}
