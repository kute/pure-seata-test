package com.kute.seata.storage;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"com.kute.seata.storage.mapper"})
@EnableTransactionManagement
@EnableDubbo(scanBasePackages = {"com.kute"})
@SpringBootApplication
//加载配置源
@NacosPropertySource(dataId = "pure.seata.storage.config.yaml", groupId = "pure-nacos-config-group", autoRefreshed = true)
// 配置数据
@NacosConfigurationProperties(prefix = "storage.nacos.config",
        dataId = "pure.seata.storage.config.yaml",
        groupId = "pure-nacos-config-group",
        type = ConfigType.YAML,
        autoRefreshed = true)
public class StorageServiceApplication {

    public static void main(String[] args) {

        System.setProperty("seata.config.name", "pure-seata-registry");
        System.setProperty("seataEnv", "test");

        SpringApplication.run(StorageServiceApplication.class, args);

    }

}
