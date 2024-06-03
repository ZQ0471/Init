package com.baimi.init.common.stp;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.baimi.init.dto.UserInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        //例如店员权限设置：1.店铺id 2.订单备注 3.菜单查询
        List<String> list = new ArrayList<>();
        UserInfo userInfo = (UserInfo) StpUtil.getSession().get((String) loginId);
        switch (userInfo.getRoles()) {
            case "admin":
                list.add("order.*");
                list.add("employee.*");
                break;
            case "manager":
                list.add("shop:" + userInfo.getShop());
                list.add("order.add");
                list.add("order.update");
                list.add("order.list");
                list.add("employee.*");
                list.add("shop.*");
                break;
            case "employee":
                list.add("shop:" + userInfo.getShop());
                list.add("order.add");
                list.add("order.update");
                list.add("order.list");
                list.add("employee.list");
                break;
            default:
                list.add("order.complete");
                list.add("order.refund");
                list.add("order.cancel");
                break;
        }
        return list;
    }
    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        UserInfo userInfo = (UserInfo) StpUtil.getSession().get((String) loginId);
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        // 设置角色为 超级管理员 员工 用户
        List<String> list = new ArrayList<>();
        list.add(userInfo.getRoles());
        return list;
    }

}

