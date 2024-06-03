package com.baimi.init.service.impl;

import com.baimi.init.entity.Employee;
import com.baimi.init.mapper.EmployeeMapper;
import com.baimi.init.service.IEmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2024-05-31
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Override
    public Employee selectByUserId(Integer userId) {
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUserId, userId);
        return this.baseMapper.selectOne(wrapper);
    }
}
