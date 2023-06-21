package com.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:test.properties")
@ConfigurationProperties(prefix = "test")
@Component
@Data
public class TestProperties {
    private String name;

    private String nickname;
}
