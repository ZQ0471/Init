package com.baimi.init.service;

import com.baimi.init.entity.Permission;
import com.baimi.init.dto.PageQuery;
import com.baimi.init.vo.PermissionVO;
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
public interface IPermissionService extends IService<Permission> {
    List<PermissionVO> getPermissionList(PageQuery pageQuery);

    boolean addPermission(Permission permission);
}
