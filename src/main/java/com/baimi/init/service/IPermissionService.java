package com.baimi.init.service;

import com.baimi.init.entity.Permission;
import com.baimi.init.query.PageQuery;
import com.baimi.init.vo.PermissionVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IPermissionService extends IService<Permission> {
    List<PermissionVO> getPermissionList(PageQuery pageQuery);
}
