package com.baimi.init.service;

import com.baimi.init.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangqi
 * @since 2024-08-14
 */
public interface IFileService extends IService<File> {

    String upload(MultipartFile file);
}
