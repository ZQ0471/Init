package com.baimi.init.service.impl;

import com.baimi.init.entity.Role;
import com.baimi.init.mapper.RoleMapper;
import com.baimi.init.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @ClassName RoleServiceImpl
 * @Author zhang
 * @DATE 2024/6/18 下午3:34
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
}
