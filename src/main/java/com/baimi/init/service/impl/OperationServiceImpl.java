package com.baimi.init.service.impl;

import com.baimi.init.common.Asserts;
import com.baimi.init.dto.OperationQuery;
import com.baimi.init.entity.Operation;
import com.baimi.init.mapper.OperationMapper;
import com.baimi.init.service.IOperationService;
import com.baimi.init.service.IUserService;
import com.baimi.init.vo.OperationVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangqi
 * @since 2024-06-21
 */
@Service
public class OperationServiceImpl extends ServiceImpl<OperationMapper, Operation> implements IOperationService {
    @Resource
    private IUserService userService;
    @Override
    public List<OperationVO> getOperationList(OperationQuery pageQuery) {
        Asserts.isTrue(pageQuery.getPageNo()!=null&&pageQuery.getPageSize()!=null,"请输入分页参数！");
        pageQuery.setOffset((pageQuery.getPageNo()-1)*pageQuery.getPageSize());
        return this.baseMapper.getOperationList(pageQuery);
    }
}
