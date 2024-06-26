package com.baimi.init.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baimi.init.common.annotation.Log;
import com.baimi.init.common.enums.OperationType;
import com.baimi.init.entity.Employee;
import com.baimi.init.entity.Shop;
import com.baimi.init.query.PageQuery;
import com.baimi.init.result.Result;
import com.baimi.init.service.IShopService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhang
 * @since 2024-05-30
 */
@RestController
@RequestMapping("/shop")
public class ShopController {
    @Resource
    private IShopService shopService;
    /**
     * @since  2024/6/19
     * @param shop 店铺信息
     * @return com.baimi.init.result.Result
     **/
    @Log(remark = "新增店铺",operationType = OperationType.INSERT)
    @SaCheckPermission("shop.add")
    @PostMapping("/addShop")
    public Result addShop(@RequestBody Shop shop) {
        boolean result = shopService.addShop(shop);
        if (result) {
            return Result.ok().message("新增店铺成功");
        }
        return Result.error().message("新增店铺失败");
    }
    /**
     * 查询店铺列表
     * @since  2024/6/19
     * @return com.baimi.init.result.Result
     **/
    @Log(remark = "查询店铺列表",operationType = OperationType.LIST)
    @SaCheckPermission("shop.list")
    @GetMapping("/list")
    public Result list(PageQuery pageQuery) {
        List<Shop> list = shopService.getShopList(pageQuery);
        return Result.ok().data("list", list).data("total", list.size());
    }
    @Log(remark = "查询店员列表",operationType = OperationType.LIST)
    @SaCheckPermission("shop.list")
    @GetMapping("/{shopId}/employees")
    public Result shopEmployee(@PathVariable Integer shopId) {
        List<Employee> list = shopService.getShopEmployee(shopId);
        return Result.ok().data("list", list).data("total", list.size());
    }
}
