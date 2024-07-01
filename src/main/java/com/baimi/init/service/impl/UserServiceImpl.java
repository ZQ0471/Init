package com.baimi.init.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.common.Asserts;
import com.baimi.init.common.UserState;
import com.baimi.init.dto.UserInfo;
import com.baimi.init.entity.User;
import com.baimi.init.mapper.UserMapper;
import com.baimi.init.query.UserQuery;
import com.baimi.init.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangqi
 * @since 2024-06-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserState userState;
    @Override
    public String login(User loginUser) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, loginUser.getAccount()).eq(User::getPassword, loginUser.getPassword());
        User user = this.baseMapper.selectOne(wrapper);
        Asserts.notNull(user, "账户或密码错误");
        StpUtil.login(user);
        userState.updateUserStatement(user);
        return StpUtil.getTokenInfo().tokenValue;
    }

    @Override
    public List<UserInfo> getUserList(UserQuery userQuery) {
        Asserts.isTrue(userQuery.getPageNo()!=null&&userQuery.getPageSize()!=null,"请输入分页参数！");
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if(userQuery.getName()!=null) wrapper.like(User::getUsername,userQuery.getName());
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
    public User getUserByPhone(String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone,phone);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public String getUsernameById(Integer userId) {
        User user = this.baseMapper.selectById(userId);
        return user!=null?user.getUsername():"用户状态已更改";
    }

    @Override
    public String register(User registerUser) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, registerUser.getAccount());
        Asserts.isTrue(this.getOne(wrapper)==null, "账号已存在！");
        if(registerUser.getRoleId()==null) registerUser.setRoleId(4);
        if(registerUser.getUsername()==null) registerUser.setUsername("请尽快改名！");
        this.save(registerUser);
        StpUtil.login(registerUser);
        userState.updateUserStatement(registerUser);
        return StpUtil.getTokenInfo().tokenValue;
    }
}
