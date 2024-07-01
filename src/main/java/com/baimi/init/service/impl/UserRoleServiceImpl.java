package com.baimi.init.service.impl;

import com.baimi.init.entity.Role;
import com.baimi.init.entity.UserRole;
import com.baimi.init.mapper.UserRoleMapper;
import com.baimi.init.service.IRoleService;
import com.baimi.init.service.IUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangqi
 * @since 2024-06-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Resource
    private IRoleService roleService;
    /**
     * 根据用户id获取角色列表
     * @since  2024/6/19
     * @param userId 用户id
     * @return java.util.List<com.baimi.init.entity.Role>
     **/
    @Override
    public List<Role> getRoleListByUserId(Integer userId) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        List<UserRole> list = this.list(wrapper);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.stream()
                .map(UserRole::getRoleId) // 提取每个UserRole对象的roleId
                .map(roleId -> roleService.getById(roleId))
                .collect(Collectors.toList());
    }
}
