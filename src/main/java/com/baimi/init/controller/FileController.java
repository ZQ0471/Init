package com.baimi.init.controller;

import com.baimi.init.common.Result;
import com.baimi.init.service.IFileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangqi
 * @since 2024-08-14
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private IFileService fileService;
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {
        String url = fileService.upload(file);
        return Result.ok().data("url", url);
    }
}
