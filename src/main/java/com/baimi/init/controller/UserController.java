package com.baimi.init.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.entity.User;
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

//    @SaCheckLogin
    @GetMapping("/userInfo")
    public Result  hello() {
        String phone = StpUtil.getLoginIdAsString();
        return Result.ok().data("user",StpUtil.getSession().get(phone));
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
}
