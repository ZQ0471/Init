package com.baimi.init.dto;

import com.baimi.init.entity.Shop;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class ShopInfo {
    private String name;

    private String descrip;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyTime;

    private String phone;

    private Byte status;
    /*
    *  "店铺类型(1：普通店铺 2：收发中心 3：干洗中心)"
    * */
    private Integer cate;

    ShopInfo(Shop shop){
        this.name = shop.getName();
        this.descrip = shop.getDescrip();
        this.createTime = shop.getCreateTime();
        this.modifyTime = shop.getModifyTime();
        this.phone = shop.getPhone();
        this.status = shop.getStatus();
        this.cate = shop.getCate();
    }
}
