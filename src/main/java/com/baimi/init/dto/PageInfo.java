package com.baimi.init.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo<T> {

    private Long total;

    private Long pages;

    private List<T> list;
}
