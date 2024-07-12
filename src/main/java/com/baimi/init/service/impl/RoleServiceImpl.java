package com.baimi.init.service.impl;

import com.baimi.init.common.Asserts;
import com.baimi.init.dto.PageQuery;
import com.baimi.init.entity.Role;
import com.baimi.init.mapper.RoleMapper;
import com.baimi.init.service.IRolePermissionService;
import com.baimi.init.service.IRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangqi
 * @since 2024-06-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private IRolePermissionService rolePermissionService;
    @Override
    public boolean addRole(Role role) {
        Asserts.notNull(role.getName(),"角色名称不能为空！");
        if(role.getRemark()==null) role.setRemark("暂无描述");
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getName, role.getName());
        Asserts.isTrue(this.count(wrapper)==0,"角色名称已存在");
        boolean save =  this.save(role);
        if (save){
            Integer roleId = this.getOne(wrapper).getId();
            rolePermissionService.updateRole(roleId,role.getPermissions());
        }
        return save;
    }

    @Override
    public List<Role> getRoleList(PageQuery pageQuery) {
        Asserts.isTrue(pageQuery.getPageNo()!=null&&pageQuery.getPageSize()!=null,"请输入分页参数！");
        Page<Role> page = new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        return this.list(page,null);
    }

    @Override
    public String getRoleById(Integer roleId) {
        return this.getById(roleId).getName();
    }

    @Override
    public boolean updateRole(Role role) {
        Asserts.notNull(role.getId(),"角色id不能为空！");
        if(role.getName()!=null&&!role.getName().equals(getRoleById(role.getId()))) {
            LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Role::getName, role.getName());
            Asserts.isTrue(this.count(wrapper)==0,"角色名称已存在");
        }
        return false;
    }
}
