package com.baimi.init.common;

import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.dto.UserInfo;
import com.baimi.init.entity.User;
import com.baimi.init.service.IRolePermissionService;
import com.baimi.init.service.IRoleService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        List<String> permissions = rolePermissionService.getPermissionsByRoleId(user.getRoleId());
        StpUtil.getSession().set("roles", roles);
        StpUtil.getSession().set("permissions", permissions);
        StpUtil.getSession().set("userInfo", userInfo);
    }
    public UserInfo getUserInfo(){
        return (UserInfo) StpUtil.getSession().get("userInfo");
    }

    public List<String> getRoleList(){
        return (List<String>) StpUtil.getSession().get("roles");
    }
    public List<String> getPermissionList(){
        return (List<String>) StpUtil.getSession().get("permissions");
    }
    public String getCurrentToken() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        return request.getHeader("satoken");
    }
    public String getPath() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest().getServletPath();
    }

}