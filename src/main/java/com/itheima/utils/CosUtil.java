package com.itheima.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Component
public class CosUtil {
    @Autowired
    private CosProperties cosProperties;
    String region;
    String bucketName;
    COSClient cosClient;


    public void initialize(){
        String accessKey = cosProperties.getAccessKey();
        String secretKey = cosProperties.getSecretKey();
        // 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        region= cosProperties.getRegion();
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        bucketName = cosProperties.getBucketName();
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        // 2 生成cos客户端
        cosClient = new COSClient(cred, clientConfig);
    }


    // 指定要上传到 COS 上对象键
    // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-chengdu.myqcloud.com/mydemo.jpg` 中，对象键为 mydemo.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
    public String localUpload(String file, String key){
        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        //file里面填写本地图片的位置 我这里是相对项目的位置，在项目下有src/test/demo.jpg这张图片
        File localFile = new File(file);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        return "https://" + bucketName + ".cos." + region + ".myqcloud.com" + key;
    }

    public String multipartUpload(MultipartFile multipartFile, ObjectMetadata metadata, String folder) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = multipartFile.getInputStream();
        // 避免文件覆盖
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = folder + UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        //上传到cos
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream, metadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        return "https://" + bucketName + ".cos." + region + ".myqcloud.com" + fileName;
    }

    public void download(String file, String key) {
        // 设置要下载到的本地路径
        File downFile = new File(file);
        // 设置要下载的文件所在的 对象桶的名称 和对象键
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
    }

    public void del(String key) {
        // 指定要删除的 bucket 和对象键
        cosClient.deleteObject(bucketName, key);
    }

}
