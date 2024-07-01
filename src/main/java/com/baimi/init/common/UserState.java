package com.baimi.init.common;

import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.dto.UserInfo;
import com.baimi.init.entity.User;
import com.baimi.init.service.IRolePermissionService;
import com.baimi.init.service.IRoleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName UserState
 * Author zhang
 * DATE 2024/6/18 下午5:03
 */
@Component
public class UserState {
    @Resource
    private IRolePermissionService rolePermissionService;
    @Resource
    private IRoleService roleService;
    /**
     * 更新用户权限状态
     * 最好每次操作权限或角色都删除一下session
     */
    public void updateUserStatement(User user){
        //更新用户角色信息
        UserInfo userInfo = new UserInfo(user);
        String role = roleService.getRoleById(user.getRoleId());
        List<String> roles =  new ArrayList<>();
        roles.add(role);
        userInfo.setRoles(roles);
        //使用roleId列表获取permission列表
        List<String> permissions = rolePermissionService.getPermissionListByRoleId(user.getRoleId());
        //转为权限名称
        userInfo.setPermissions(permissions);
        StpUtil.getSession().set("userInfo", userInfo);
    }
    public UserInfo getUserInfo(){
        return (UserInfo) StpUtil.getSession().get("userInfo");
    }
    public Integer getUserId(){
        return getUserInfo().getId();
    }
    public List<String> getRoleList(){
        return getUserInfo().getRoles();
    }
    public List<String> getPermissionList(){
        return getUserInfo().getPermissions();
    }
}