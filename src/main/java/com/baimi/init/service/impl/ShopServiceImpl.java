package com.baimi.init.service.impl;

import com.baimi.init.common.Asserts;
import com.baimi.init.entity.Employee;
import com.baimi.init.entity.Shop;
import com.baimi.init.mapper.EmployeeMapper;
import com.baimi.init.mapper.ShopMapper;
import com.baimi.init.dto.PageQuery;
import com.baimi.init.service.IShopService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangqi
 * @since 2024-06-21
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public List<Shop> getShopList(PageQuery pageQuery) {
        Asserts.isTrue(pageQuery.getPageNo()!=null&&pageQuery.getPageSize()!=null,"请输入分页参数！");
        Page<Shop> page = new Page<>(pageQuery.getPageNo(), pageQuery.getPageSize());
        return this.page(page).getRecords();
    }

    @Override
    public boolean addShop(Shop shop) {
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Shop::getName, shop.getName());
        Asserts.isTrue(this.getOne(wrapper)==null,"店铺已存在！");
        return this.save(shop);
    }

    @Override
    public List<Employee> getShopEmployee(Integer shopId) {
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getShopId, shopId);
        return employeeMapper.selectList(wrapper);
    }
}
