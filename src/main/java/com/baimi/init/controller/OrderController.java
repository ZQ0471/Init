package com.baimi.init.controller;

import com.example.demo.service.IOrderService;
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
@RequestMapping("/order")
public class OrderController {
    @Resource
    private IOrderService orderService;
}
