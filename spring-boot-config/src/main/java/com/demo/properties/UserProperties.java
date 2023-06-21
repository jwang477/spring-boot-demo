package com.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "xiwen")
@Data
public class UserProperties {
    private String name;

    private Integer age;
}
