package com.atguigu.product.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.atguigu.product.generator.mapper")
@EnableTransactionManagement
public class MybatisConfig {
}
