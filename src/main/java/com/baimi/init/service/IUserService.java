package com.example.demo.service;

import com.baimi.init.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
