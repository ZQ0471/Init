package com.baimi.init.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhang
 * DATE 2024/6/21 下午1:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperationQuery extends PageQuery {
    private String type;
}