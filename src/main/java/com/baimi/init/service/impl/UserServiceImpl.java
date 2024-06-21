package com.baimi.init.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.common.Asserts;
import com.baimi.init.dto.UserInfo;
import com.baimi.init.entity.User;
import com.baimi.init.mapper.UserMapper;
import com.baimi.init.query.UserQuery;
import com.baimi.init.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public String login(User loginUser) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, loginUser.getAccount()).eq(User::getPassword, loginUser.getPassword());
        User user = this.baseMapper.selectOne(wrapper);
        Asserts.notNull(user, "账户或密码错误");
        StpUtil.login(user.getPhone());
        StpUtil.getSession().set("userId", user.getId());
        UserInfo userInfo = new UserInfo(user);
        StpUtil.getSession().set("user", userInfo);
        return StpUtil.getTokenInfo().tokenValue;
    }

    @Override
    public List<UserInfo> getUserList(UserQuery userQuery) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if(userQuery.getName()!=null) wrapper.like(User::getUsername,userQuery.getName());
        Asserts.isTrue(userQuery.getPageNo()!=null&&userQuery.getPageSize()!=null,"请输入分页参数！");
        Page<User> page = new Page<>(userQuery.getPageNo(),userQuery.getPageSize());
        this.baseMapper.selectPage(page,wrapper);
        List<UserInfo> list = new ArrayList<>();
        page.getRecords().forEach(user->{
            UserInfo userInfo = new UserInfo(user);
            list.add(userInfo);
        });
        return list;
    }

    @Override
    public Integer getUserIdByPhone(String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone,phone);
        User user = this.baseMapper.selectOne(wrapper);
        if(user==null) return -1;
        return user.getId();
    }

    @Override
    public String getUsernameById(Integer userId) {
        User user = this.baseMapper.selectById(userId);
        return user!=null?user.getUsername():"用户状态已更改";
    }
}
