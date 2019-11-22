package com.horse.sso.mapper.configure;

import com.horse.sso.mapper.BaseMapper;
import com.horse.sso.mapper.Constant;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MapperConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configure = new MapperScannerConfigurer();
        configure.setBasePackage(Constant.MAPPER_BASE_PACKAGE);
        configure.setMarkerInterface(BaseMapper.class);
        return configure;
    }
}