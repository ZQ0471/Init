package com.baimi.init.controller;

import com.baimi.init.common.Result;
import com.baimi.init.common.UserState;
import com.baimi.init.common.annotation.Idempotent;
import com.baimi.init.common.annotation.MLog;
import com.baimi.init.common.enums.TypeEnum;
import com.baimi.init.dto.UserQuery;
import com.baimi.init.entity.User;
import com.baimi.init.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
    @GetMapping("/userInfo")
    public Result info() {
        return Result.ok().data("userInfo",userState.getUserInfo());
    }


    @MLog(remark = "用户测试")
    @GetMapping("/test")
    @Idempotent(
            type = TypeEnum.TOKEN,
            message = "访问太频繁，请稍后再试"
    )
    public Result test(String msg,Integer id) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String s = sra.getRequest().getServletPath();
        return Result.ok().data("path",s);
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
