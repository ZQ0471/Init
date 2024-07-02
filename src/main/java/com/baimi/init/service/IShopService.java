package com.baimi.init.service;

import com.baimi.init.entity.Employee;
import com.baimi.init.entity.Shop;
import com.baimi.init.query.PageQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhang
 * @since 2024-05-30
 */
public interface IShopService extends IService<Shop> {

    List<Shop> getShopList(PageQuery pageQuery);

    boolean addShop(Shop shop);

    List<Employee> getShopEmployee(Integer shopId);
}
