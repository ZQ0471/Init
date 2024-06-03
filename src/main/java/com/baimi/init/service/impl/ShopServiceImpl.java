package com.baimi.init.service.impl;

import com.baimi.init.entity.Shop;
import com.baimi.init.mapper.ShopMapper;
import com.baimi.init.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
}
