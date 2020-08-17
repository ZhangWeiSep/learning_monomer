package indi.zhangweisep.backstage.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import indi.zhangweisep.common.base.controller.BaseController;
import indi.zhangweisep.common.base.result.ResultData;
import indi.zhangweisep.modules.system.entity.SysUser;
import indi.zhangweisep.modules.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统用户——请求控制器
 *
 * @author ZhangWei
 * @since 2020/8/17 13:27
 */
@Slf4j
@RestController
@RequestMapping("/sysUser")
@RequiredArgsConstructor
public class SysUserController extends BaseController {

    /**
     * 引入系统用户业务处理
     */
    private final SysUserService sysUserService;

    /**
     * 找到用户的所有数据
     *
     * @return {@link ResultData<List<SysUser>>}
     */
    @GetMapping("/findUserAllData")
    public ResultData<List<SysUser>> findUserAllData() {
        return ResultData.success(sysUserService.list());
    }

}