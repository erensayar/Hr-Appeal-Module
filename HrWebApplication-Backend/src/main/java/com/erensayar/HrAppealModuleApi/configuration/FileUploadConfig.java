package com.erensayar.HrWebApplicationApi.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "file-upload")
public class FileUploadConfig {

    private String storagePath;

}
