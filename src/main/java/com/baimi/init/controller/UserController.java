package com.baimi.init.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.entity.User;
import com.baimi.init.query.UserQuery;
import com.baimi.init.result.Result;
import com.baimi.init.service.IUserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * Date 上午10:54 2024/6/17
     * Param [user, userQuery]
     * @return com.baimi.init.result.Result
     **/

    @PostMapping("/something")
    public Result something(@RequestBody UserQuery userQuery) {
        log.error("userQuery:{}", userQuery.toString());
        return Result.ok().data("ok","ok");
    }

    /**
     * Date 上午10:55 2024/6/17
     * Param []
     * @return com.baimi.init.result.Result
     **/
    @GetMapping("/userInfo")
    public Result hello() {
       Object o  = redisTemplate.opsForValue().get("test");
        return Result.ok().data("user",StpUtil.getSession().get("user")).data("test",o);
    }

    @SaCheckRole("admin")
    @GetMapping("/test")
    public Result test() {
        redisTemplate.opsForValue().set("test","just test");
        return Result.ok().data("roles", StpUtil.getSession().get("roles"))
                .data("permissions", StpUtil.getSession().get("permissions"));
    }

    @SaCheckPermission("role.add")
    @GetMapping("/code")
    public Result voidTest() {
        return Result.ok().data("roles", StpUtil.getSession().get("roles"));
    }

    @PostMapping("/login")
    public Result login(@RequestBody User loginUser) {
        String token = userService.login(loginUser);
        return Result.ok().data("token", token);
    }
    public Result list(UserQuery userQuery) {
        return Result.ok().data("list", userService.getUserList(userQuery));
    }
}
