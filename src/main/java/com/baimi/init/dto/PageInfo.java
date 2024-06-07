package com.baimi.init.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageInfo<T> {

    private Long total;

    private Long pages;

    private List<T> list;
}
