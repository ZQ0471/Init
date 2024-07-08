package com.baimi.init.dto;

import com.baimi.init.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo {
    private int id;
    private String account;
    private String phone;
    private String username;
    private Integer roleId;

    public UserInfo(User user) {
        this.id = user.getId();
        this.account = user.getAccount();
        this.roleId = user.getRoleId();
        this.phone = user.getPhone();
        this.username = user.getUsername();
    }
}
