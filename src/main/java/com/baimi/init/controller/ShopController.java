package com.baimi.init.controller;

import com.example.demo.service.IShopService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
