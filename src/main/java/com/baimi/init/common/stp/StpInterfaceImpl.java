package com.baimi.init.common.stp;

import cn.dev33.satoken.stp.StpInterface;
import com.baimi.init.common.UserState;
import com.baimi.init.dto.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Slf4j
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserState userState;
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        UserInfo userInfo = userState.getUserInfo();
        log.error("获取权限{}",userInfo.getPermissions());
        return userInfo.getPermissions();
    }
    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        UserInfo userInfo = userState.getUserInfo();
        log.error("获取角色{}", userInfo.getRoles());
        return userInfo.getRoles();
    }
}