package com.baimi.init.controller;

import com.baimi.init.entity.Employee;
import com.baimi.init.query.PageQuery;
import com.baimi.init.result.Result;
import com.baimi.init.service.IEmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 员工表 前端控制器
 * </p>
 *
 * @author zhang
 * @since 2024-05-31
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private IEmployeeService employeeService;


    @GetMapping("/list")
    public Result list(PageQuery pageQuery) {
        List<Employee> list = employeeService.getEmployeeList(pageQuery);
        return Result.ok().data("list",list).data("total",list.size());
    }

}
