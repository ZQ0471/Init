package com.baimi.init.controller;

import com.baimi.init.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName UserRoleController
 * @Author zhang
 * @DATE 2024/6/18 下午3:46
 */
@Slf4j
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    @Resource
    private IUserRoleService userRoleService;
}
