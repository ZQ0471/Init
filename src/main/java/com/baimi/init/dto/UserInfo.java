package com.baimi.init.dto;

import com.baimi.init.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private int id;
    private String account;
    private String phone;
    private String avatar;
    private String username;
    private List<String> roles;
    private List<String> permissions;

    public UserInfo(User user) {
        this.id = user.getId();
        this.account = user.getAccount();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.username = user.getUsername();
    }
}
