package com.baimi.init.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.entity.User;
import com.baimi.init.query.UserQuery;
import com.baimi.init.result.Result;
import com.baimi.init.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/userInfo")
    public Result  hello() {
        return Result.ok().data("user",StpUtil.getSession().get("user"));
    }

    @GetMapping("/test")
    public String test() {
        return (String) redisTemplate.opsForValue().get("user");
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
