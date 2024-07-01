package com.baimi.init.controller;

import com.baimi.init.service.IRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
@RequestMapping("/rolePermission")
public class RolePermissionController {
    @Resource
    private IRolePermissionService rolePermissionService;
}
