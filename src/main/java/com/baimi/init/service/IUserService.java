package com.baimi.init.service;

import com.baimi.init.dto.UserInfo;
import com.baimi.init.dto.UserQuery;
import com.baimi.init.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhang
 * @since 2024-05-30
 */
public interface IUserService extends IService<User> {

    String login(User loginUser);

    List<UserInfo> getUserList(UserQuery userQuery);


    String addUser(User user);

    Page<User> getUserPage(UserQuery userQuery);

    @Async
    void asyncMethod();
}
