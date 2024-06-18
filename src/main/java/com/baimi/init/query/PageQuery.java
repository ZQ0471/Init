package com.baimi.init.query;

import lombok.Data;

@Data
/*分页查询实体*/
public class PageQuery {
    private Long pageNo;

    private Long pageSize;

    private String sortBy;

    private Boolean isAsc;
}