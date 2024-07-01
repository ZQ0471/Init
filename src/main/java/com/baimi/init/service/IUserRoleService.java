package com.baimi.init.service;

import com.baimi.init.entity.Role;
import com.baimi.init.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangqi
 * @since 2024-06-21
 */
public interface IUserRoleService extends IService<UserRole> {
    List<Role> getRoleListByUserId(Integer userId);
}
