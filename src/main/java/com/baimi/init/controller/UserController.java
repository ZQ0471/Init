package com.baimi.init.controller;

import com.baimi.init.common.Result;
import com.baimi.init.common.UserState;
import com.baimi.init.common.annotation.Log;
import com.baimi.init.common.enums.OperationType;
import com.baimi.init.dto.UserQuery;
import com.baimi.init.entity.User;
import com.baimi.init.service.IUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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



    /**
     * @since 上午10:55 2024/6/17
     * @return com.baimi.init.common.Result
     **/
    @Log(remark = "查询用户信息",operationType = OperationType.LIST)
    @GetMapping("/userInfo")
    public Result info() {
        return Result.ok().data("userInfo",userState.getUserInfo());
    }
    @Log(remark = "用户测试",operationType = OperationType.OTHER)
    @GetMapping("/test")
    public Result test(UserQuery userQuery) {
        Page<User> page = userService.getUserPage(userQuery);
        return Result.ok().data("page",page);
    }
    @Log(remark = "用户登录",operationType = OperationType.OTHER)
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
    @Log(remark = "获取用户列表",operationType = OperationType.LIST)
    @GetMapping("/list")
    public Result list(UserQuery userQuery) {
        return Result.ok().data("list", userService.getUserList(userQuery));
    }
}
