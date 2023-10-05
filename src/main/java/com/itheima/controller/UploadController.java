package com.itheima.controller;


import com.itheima.pojo.Result;
import com.itheima.utils.CosUtil;
import com.qcloud.cos.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@RestController
public class UploadController {
    @Autowired
    private CosUtil cosUtil;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        cosUtil.initialize();
        return Result.success(cosUtil.multipartUpload(image,metadata,"/cejpg/"));
    }
}
