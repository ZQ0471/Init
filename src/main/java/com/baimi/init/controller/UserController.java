package com.baimi.init.controller;

import com.baimi.init.common.Result;
import com.baimi.init.common.UserState;
import com.baimi.init.dto.UserQuery;
import com.baimi.init.entity.User;
import com.baimi.init.service.IUserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private UserState userState;


    @GetMapping("/userInfo")
    public Result info() {
        return Result.ok().data("userInfo",userState.getUserInfo());
    }
    @GetMapping("/test")
    public Result test() {
        userService.asyncMethod();
        return Result.ok();
    }
    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        String token = userService.login(loginUser);
        return Result.ok().data("token", token);
    }
    @PostMapping("/add")
    public Result register(@RequestBody User user) {
        String token = userService.addUser(user);
        return Result.ok().data("token", token);
    }
    @GetMapping("/list")
    public Result list(UserQuery userQuery) {
        return Result.ok().data("list", userService.getUserList(userQuery));
    }
}
