package com.baimi.init.dto;

import com.baimi.init.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String account;
    private String phone;
    private String avatar;
    private String username;

    public UserInfo(User user) {
        this.account = user.getAccount();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.username = user.getUserName();
    }
}
