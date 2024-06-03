package com.baimi.init.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.common.Asserts;
import com.baimi.init.dto.UserInfo;
import com.baimi.init.entity.Employee;
import com.baimi.init.entity.User;
import com.baimi.init.mapper.UserMapper;
import com.baimi.init.service.IEmployeeService;
import com.baimi.init.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private IEmployeeService employeeService;
    @Override
    public String login(User loginUser) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, loginUser.getAccount()).eq(User::getPassword, loginUser.getPassword());
        User user = this.baseMapper.selectOne(wrapper);
        Asserts.notNull(user, "账户或密码错误");
        StpUtil.login(user.getPhone());
        UserInfo userInfo = new UserInfo(user);
        if (user.getRoles().equals("manager")||user.getRoles().equals("employee")) {
            Employee employee = employeeService.selectByUserId(user.getId());
            Asserts.notNull(employee, "身份认证有误");
            StpUtil.getSession().set("shop", employee.getShopId());
        }
        StpUtil.getSession().set("role", user.getRoles());
        StpUtil.getSession().set("user", userInfo);
        // 第2步，获取 Token 相关参数
        return StpUtil.getTokenInfo().tokenValue;
    }
}
