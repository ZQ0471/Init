package com.baimi.init.controller;

import com.baimi.init.entity.Role;
import com.baimi.init.query.PageQuery;
import com.baimi.init.result.Result;
import com.baimi.init.service.IRoleService;
import com.baimi.init.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;

    @GetMapping("/list")
    public Result getRoleList(PageQuery pageQuery) {
        List<RoleVO> list = roleService.getRoleList(pageQuery);
        return Result.ok().data("list", list).data("total", list.size());
    }
    @PostMapping("/add")
    public Result addRole(@RequestBody Role role) {
        if(roleService.addRole(role)) return Result.ok().message("新增角色成功！");
        return Result.error().message("新增角色失败！");
    }
}
