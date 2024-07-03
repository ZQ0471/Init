package com.baimi.init.controller;

import com.baimi.init.common.annotation.Log;
import com.baimi.init.common.enums.OperationType;
import com.baimi.init.entity.Permission;
import com.baimi.init.dto.PageQuery;
import com.baimi.init.result.Result;
import com.baimi.init.service.IPermissionService;
import com.baimi.init.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhang
 * @since 2024-05-30
 */
@Slf4j
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private IPermissionService permissionService;

    @Log(remark = "获取权限列表",operationType = OperationType.LIST)
    @GetMapping("/list")
    public Result getPermissionList(PageQuery pageQuery){
        List<PermissionVO> list = permissionService.getPermissionList(pageQuery);
        return Result.ok().data("list", list).data("total", list.size());
    }
    @Log(remark = "新增权限",operationType = OperationType.INSERT)
    @PostMapping("/add")
    public Result addPermission(@RequestBody Permission permission){
        boolean result = permissionService.addPermission(permission);
        if (result) return Result.ok().message("新增权限成功！");
        return Result.error().message("新增权限失败！");
    }
}
