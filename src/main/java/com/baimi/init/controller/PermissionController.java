package com.baimi.init.controller;

import com.baimi.init.query.PageQuery;
import com.baimi.init.result.Result;
import com.baimi.init.service.IPermissionService;
import com.baimi.init.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhang
 * @since 2024-05-30
 */
@Slf4j
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private IPermissionService permissionService;
    /**
     * 获取权限列表
     * @since  2024/6/19
     * @param pageQuery 分页数据
     * @return com.baimi.init.result.Result
     **/
    @GetMapping("/list")
    public Result getPermissionList(PageQuery pageQuery){
        List<PermissionVO> list = permissionService.getPermissionList(pageQuery);
        return Result.ok().data("list", list).data("total", list.size());
    }
}
