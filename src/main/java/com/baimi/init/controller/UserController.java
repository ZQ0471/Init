package com.baimi.init.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
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
     * @Date 上午10:54 2024/6/17
     * @Param [user, userQuery]
     * @return com.baimi.init.result.Result
     **/

    @PostMapping("/something")
    public Result something(@RequestBody UserQuery userQuery) {
        log.error("userQuery:{}", userQuery.toString());
        return Result.ok().data("ok","ok");
    }

    /**
     * @Date 上午10:55 2024/6/17
     * @Param []
     * @return com.baimi.init.result.Result
     **/
    @GetMapping("/userInfo")
    public Result hello() {
        return Result.ok().data("user",StpUtil.getSession().get("user"));
    }

    @GetMapping("/test")
    public Result test() {
        log.error("真的执行力");
        return Result.ok().data("seconds", "seconds");
    }

    @PutMapping("/code")
    public void voidTest() {
//        log.error("真的执行力");
    }

    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        String token = userService.login(loginUser);
        return Result.ok().data("token", token);
    }
    @SaCheckRole("admin")
    @GetMapping("/list")
    public Result list(UserQuery userQuery, User user) {
        return Result.ok().data("list", userService.getUserList(userQuery));
    }
}
