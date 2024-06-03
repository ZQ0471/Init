package com.baimi.init.controller;

import com.baimi.init.service.IGoodService;
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
@RequestMapping("/good")
public class GoodController {
    @Resource
    private IGoodService goodService;


}
