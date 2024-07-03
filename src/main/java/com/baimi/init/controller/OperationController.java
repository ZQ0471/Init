package com.baimi.init.controller;

import com.baimi.init.dto.OperationQuery;
import com.baimi.init.result.Result;
import com.baimi.init.service.IOperationService;
import com.baimi.init.vo.OperationVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangqi
 * @since 2024-06-21
 */
@RestController
@RequestMapping("/operation")
public class OperationController {

    @Resource
    private IOperationService operationService;
    @GetMapping("/list")
    public Result list(OperationQuery query) {
        List<OperationVO> list = operationService.getOperationList(query);
        return Result.ok().data("list", list).data("total", list.size());
    }

}
