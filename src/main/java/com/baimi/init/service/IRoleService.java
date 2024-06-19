package com.baimi.init.service;

import com.baimi.init.entity.Role;
import com.baimi.init.query.PageQuery;
import com.baimi.init.vo.RoleVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IRoleService extends IService<Role> {
    boolean addRole(Role role);

    List<RoleVO> getRoleList(PageQuery pageQuery);
}
