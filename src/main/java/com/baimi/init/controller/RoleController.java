package com.baimi.init.controller;

import com.baimi.init.result.Result;
import com.baimi.init.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;

    @GetMapping("/list")
    public Result getRoleList() {
        return Result.ok();
    }
}
