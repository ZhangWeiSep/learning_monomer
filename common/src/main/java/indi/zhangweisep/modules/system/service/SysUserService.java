package indi.zhangweisep.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import indi.zhangweisep.modules.system.entity.SysUser;
import indi.zhangweisep.modules.system.vo.LoginUserVO;

/**
 * 系统用户业务处理
 *
 * @author ZhangWei
 * @since 2020/8/17 13:24
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 查询登录用户信息
     *
     * @param user 用户
     * @return {@link LoginUserVO}
     */
    LoginUserVO findLoginUser(SysUser user);

}
