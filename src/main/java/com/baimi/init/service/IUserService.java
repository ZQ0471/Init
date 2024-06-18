package com.baimi.init.service;

import com.baimi.init.dto.UserInfo;
import com.baimi.init.entity.User;
import com.baimi.init.query.UserQuery;
import com.baomidou.mybatisplus.extension.service.IService;

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

    Integer getUserIdByPhone(String phone);
}
