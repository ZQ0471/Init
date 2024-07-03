package com.baimi.init.dto;

import lombok.Data;

@Data
/*分页查询实体*/
public class PageQuery {
    private Long pageNo;

    private Long pageSize;

    private String sortBy;

    private Boolean isAsc;
}