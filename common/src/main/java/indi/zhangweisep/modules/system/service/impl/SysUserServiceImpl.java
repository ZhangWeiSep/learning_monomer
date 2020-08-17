package indi.zhangweisep.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.zhangweisep.modules.system.entity.SysUser;
import indi.zhangweisep.modules.system.mapper.SysUserMapper;
import indi.zhangweisep.modules.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 系统用户业务处理实现
 *
 * @author ZhangWei
 * @since 2020/8/17 13:25
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {



}