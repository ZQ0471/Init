package com.baimi.init.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baimi.init.entity.Role;
import com.baimi.init.query.PageQuery;
import com.baimi.init.result.Result;
import com.baimi.init.service.IRoleService;
import com.baimi.init.vo.RoleVO;
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
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;

    /**
     * 获取角色列表
     * @since  2024/6/19
     * @param pageQuery 分页数据
     * @return com.baimi.init.result.Result
     **/
    @SaCheckPermission("role:list")
    @GetMapping("/list")
    public Result getRoleList(PageQuery pageQuery) {
        List<RoleVO> list = roleService.getRoleList(pageQuery);
        return Result.ok().data("list", list).data("total", list.size());
    }

    /**
     * 新增角色
     * date   2024/6/19
     * @param role 角色
     * @return Result
     **/
    @SaCheckPermission("role:add")
    @PostMapping("/add")
    public Result addRole(@RequestBody Role role) {
        if(roleService.addRole(role)) return Result.ok().message("新增角色成功！");
        return Result.error().message("新增角色失败！");
    }
}
