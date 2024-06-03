package com.baimi.init.service.impl;

import com.baimi.init.entity.Order;
import com.baimi.init.mapper.OrderMapper;
import com.baimi.init.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
}
