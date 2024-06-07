package com.baimi.init.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.entity.User;
import com.baimi.init.query.UserQuery;
import com.baimi.init.result.Result;
import com.baimi.init.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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


    @GetMapping("/userInfo")
    public Result hello() {
        return Result.ok().data("user",StpUtil.getSession().get("user"));
    }

    @GetMapping("/test")
    public Result test() {
        return Result.ok().data("time", LocalDateTime.now().plusHours(1));
    }

    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        String token = userService.login(loginUser);
        return Result.ok().data("token", token);
    }
    @SaCheckRole("admin")
    @GetMapping("/list")
    public Result list(UserQuery userQuery) {
        return Result.ok().data("list", userService.getUserList(userQuery));
    }
}
