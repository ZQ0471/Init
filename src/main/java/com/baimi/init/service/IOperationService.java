package com.baimi.init.service;

import com.baimi.init.dto.PageQuery;
import com.baimi.init.entity.Operation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangqi
 * @since 2024-06-21
 */
public interface IOperationService extends IService<Operation> {

    List<Operation> getOperationList(PageQuery query);
}
