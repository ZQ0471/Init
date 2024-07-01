package com.baimi.init.controller;

import com.baimi.init.service.IUserRoleService;
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
@RequestMapping("/userRole")
public class UserRoleController {
    @Resource
    private IUserRoleService userRoleService;
}
