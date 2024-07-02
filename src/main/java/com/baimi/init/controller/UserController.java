package com.baimi.init.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.common.annotation.Log;
import com.baimi.init.common.enums.OperationType;
import com.baimi.init.common.enums.OrderStatus;
import com.baimi.init.entity.User;
import com.baimi.init.query.UserQuery;
import com.baimi.init.result.Result;
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

    /**
     * @since 上午10:55 2024/6/17
     * @return com.baimi.init.result.Result
     **/
    @Log(remark = "查询用户信息",operationType = OperationType.LIST)
    @GetMapping("/userInfo")
    public Result hello() {
        return Result.ok().data("userInfo",StpUtil.getSession().get("userInfo"));
    }
    @Log(remark = "用户测试",operationType = OperationType.OTHER)
    @SaCheckRole("user")
    @GetMapping("/test")
    public Result test() {
        int value = OrderStatus.CANCELED.getValue();
        return Result.ok().data("value",value);
    }
    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        String token = userService.login(loginUser);
        return Result.ok().data("token", token);
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        String token = userService.register(user);
        return Result.ok().data("token", token);
    }
    @Log(remark = "获取用户列表",operationType = OperationType.LIST)
    @SaCheckPermission("user.list")
    @GetMapping("/list")
    public Result list(UserQuery userQuery) {
        return Result.ok().data("list", userService.getUserList(userQuery));
    }
}
