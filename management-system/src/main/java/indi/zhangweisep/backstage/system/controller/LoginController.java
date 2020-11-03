package indi.zhangweisep.backstage.system.controller;

import indi.zhangweisep.backstage.util.SecurityUtil;
import indi.zhangweisep.common.base.controller.BaseController;
import indi.zhangweisep.common.base.result.ResultData;
import indi.zhangweisep.modules.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 登录控制器
 * </p>
 *
 * @author ZhangWei
 * @since 2020/10/22 11:05
 */
@Slf4j
@RestController
public class LoginController extends BaseController {

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return {@link ResultData<SysUser>}
     */
    @GetMapping("/loginTest")
    public ResultData<SysUser> login(String userName, String password) {
        log.info("登录成功" + userName + password);
        return ResultData.success(SecurityUtil.getCurrUsername());
    }

}
