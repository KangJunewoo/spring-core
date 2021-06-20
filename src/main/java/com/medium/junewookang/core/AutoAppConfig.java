package com.medium.junewookang.core;

import static org.springframework.context.annotation.ComponentScan.*;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(
        excludeFilters = @Filter(type= FilterType.ANNOTATION, classes=Configuration.class)
) // 그때 만들어뒀던거 안지우기 위해 기존 config 파일들 exclude
public class AutoAppConfig {
    // 텅-텅 비었음.
}
