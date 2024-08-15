package com.baimi.init.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baimi.init.common.Constant;
import com.baimi.init.entity.File;
import com.baimi.init.mapper.FileMapper;
import com.baimi.init.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangqi
 * @since 2024-08-14
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Override
    public String upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        // 生成新文件名
        String fileName = createNewFileName(originalFilename);
        try {
            file.transferTo(new java.io.File(Constant.IMAGE_UPLOAD_DIR, fileName));
            return fileName;
        }catch (IOException e){
            throw new RuntimeException("文件上传失败", e);
        }
    }

    private String createNewFileName(String originalFilename) {
        // 获取后缀
        String suffix = StrUtil.subAfter(originalFilename, ".", true);
        // 生成目录
        String name = UUID.randomUUID().toString();
        int hash = name.hashCode();
        int d1 = hash & 0xF;
        int d2 = (hash >> 4) & 0xF;
        // 判断目录是否存在
        java.io.File dir = new java.io.File(Constant.IMAGE_UPLOAD_DIR, StrUtil.format("/file/{}/{}", d1, d2));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 生成文件名
        return StrUtil.format("/file/{}/{}/{}.{}", d1, d2, name, suffix);
    }

}
