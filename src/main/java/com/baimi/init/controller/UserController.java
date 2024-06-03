package com.baimi.init.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baimi.init.entity.User;
import com.baimi.init.result.Result;
import com.baimi.init.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhang
 * @since 2024-05-30
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @SaCheckLogin
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        String token = userService.login(loginUser);
        //System.out.println(StpUtil.getSession().get("user"));
        return Result.ok().data("token", token);
    }
}
