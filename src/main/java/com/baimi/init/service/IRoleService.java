package com.baimi.init.service;

import com.baimi.init.dto.PageQuery;
import com.baimi.init.entity.Role;
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
public interface IRoleService extends IService<Role> {
    boolean addRole(Role role);

    List<Role> getRoleList(PageQuery pageQuery);

    String getRoleById(Integer roleId);

    boolean updateRole(Role role);
}
