package com.baimi.init.service.impl;

import com.baimi.init.common.Asserts;
import com.baimi.init.entity.Role;
import com.baimi.init.mapper.RoleMapper;
import com.baimi.init.dto.PageQuery;
import com.baimi.init.service.IRoleService;
import com.baimi.init.vo.RoleVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Override
    public boolean addRole(Role role) {
        Asserts.notNull(role.getName(),"角色名称不能为空！");
        Asserts.notNull(role.getRemark(),"角色描述不能为空！");
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getName, role.getName());
        Asserts.isTrue(this.baseMapper.selectCount(wrapper)==0,"角色名称已存在");
        return this.baseMapper.insert(role) == 1;
    }

    @Override
    public List<RoleVO> getRoleList(PageQuery pageQuery) {
        Asserts.isTrue(pageQuery.getPageNo()!=null&&pageQuery.getPageSize()!=null,"请输入分页参数！");
        Page<Role> page = new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        List<Role> roleList = this.page(page).getRecords();
        return roleList.stream().map(RoleVO::new).collect(Collectors.toList());
    }

    @Override
    public String getRoleById(Integer roleId) {
        Role role = this.getById(roleId);
        return role==null?"":role.getName();
    }
}
