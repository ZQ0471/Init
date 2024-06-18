package com.baimi.init.common.stp;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.common.UserState;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserState userState;
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        //从Session获取
        Object o = StpUtil.getSession().get("permissions");
        if (o == null) {
            userState.refreshUserStatement();
            o = StpUtil.getSession().get("permissions");
        }
        return (List<String>) o;
    }
    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        //从Session获取
        Object o = StpUtil.getSession().get("roles");
        if (o == null) {
            userState.refreshUserStatement();
            o = StpUtil.getSession().get("roles");
        }
        return (List<String>) o;
    }
}