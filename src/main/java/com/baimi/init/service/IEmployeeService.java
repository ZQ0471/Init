package com.baimi.init.service;

import com.baimi.init.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author zhang
 * @since 2024-05-31
 */
public interface IEmployeeService extends IService<Employee> {

    Employee selectByUserId(Integer id);
}
