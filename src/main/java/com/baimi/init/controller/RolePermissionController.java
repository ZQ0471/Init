package com.baimi.init.controller;

import com.baimi.init.service.IRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName RolePermissionController
 * @Author zhang
 * @DATE 2024/6/18 下午3:47
 */
@Slf4j
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {
    @Resource
    private IRolePermissionService rolePermissionService;
}
