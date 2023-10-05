package com.itheima.utils;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "tengxunyun.cos")
public class CosProperties {
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucketName;
}
