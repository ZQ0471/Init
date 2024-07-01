package com.baimi.init.common.stp;

import cn.dev33.satoken.stp.StpInterface;
import com.baimi.init.common.UserState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Slf4j
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserState userState;
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return userState.getPermissionList();
    }
    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return userState.getRoleList();
    }
}