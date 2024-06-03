package com.baimi.init.dto;

import com.baimi.init.entity.User;
import lombok.Data;

@Data
public class UserInfo {
    private String account;
    private String phone;
    private String avatar;
    private String roles;
    private Integer shop;

    public UserInfo(User user) {
        this.account = user.getAccount();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.roles = user.getRoles();
    }
}
