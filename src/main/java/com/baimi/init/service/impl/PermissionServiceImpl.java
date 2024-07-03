package com.baimi.init.service.impl;

import com.baimi.init.common.Asserts;
import com.baimi.init.entity.Permission;
import com.baimi.init.mapper.PermissionMapper;
import com.baimi.init.dto.PageQuery;
import com.baimi.init.service.IPermissionService;
import com.baimi.init.vo.PermissionVO;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<PermissionVO> getPermissionList(PageQuery pageQuery) {
        Asserts.isTrue(pageQuery.getPageNo()!=null&&pageQuery.getPageSize()!=null,"请输入分页参数！");
        Page<Permission> page = new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        List<Permission> permissionList = this.page(page).getRecords();
        return permissionList.stream().map(PermissionVO::new).collect(Collectors.toList());
    }

    @Override
    public boolean addPermission(Permission permission) {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getName, permission.getName());
        Asserts.isTrue(this.getOne(wrapper)==null,"权限已存在！");
        if(permission.getRemark()==null) permission.setRemark("暂无说明");
        return this.save(permission);
    }
}