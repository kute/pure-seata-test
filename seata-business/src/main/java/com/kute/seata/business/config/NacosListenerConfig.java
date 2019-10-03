package com.kute.seata.business.config;

import com.alibaba.boot.nacos.config.properties.NacosConfigProperties;
import com.alibaba.boot.nacos.discovery.properties.NacosDiscoveryProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.ListView;
import com.google.common.base.Strings;
import com.kute.seata.business.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * created by bailong001 on 2019/10/01 16:58
 */
@Slf4j
@Configuration
public class NacosListenerConfig {

    @Autowired
    private NacosConfigProperties nacosConfigProperties;
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    /**
     * 监听配置变更
     *
     * @return
     * @throws Exception
     */
    @Bean
    public ConfigService configService() throws Exception {
        if (Strings.isNullOrEmpty(nacosConfigProperties.getServerAddr())
                || Strings.isNullOrEmpty(nacosConfigProperties.getGroup())
                || Strings.isNullOrEmpty(nacosConfigProperties.getDataId())) {
            throw new RuntimeException("Nacos配置中心初始化失败：配置不完整");
        }
        String dataId = nacosConfigProperties.getDataId();
        String group = nacosConfigProperties.getGroup();
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosConfigProperties.getServerAddr());
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        log.info("获取到全部配置如下：{}", content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("接收配置变更如下：dataId={}, groupId={}, configInfo={}", dataId, group, configInfo);
            }

            @Override
            public Executor getExecutor() {
                return ThreadUtil.CORETHREADPOOLEXECUTOR;
            }
        });
        return configService;
    }

    @Bean
    public NamingService namingService() throws NacosException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosDiscoveryProperties.getServerAddr());
        properties.put(PropertyKeyConst.NAMESPACE, nacosDiscoveryProperties.getNamespace());
        NamingService namingService = NacosFactory.createNamingService(properties);
        // 对所有的 service进行监听
        ListView<String> serviceViewList = namingService.getServicesOfServer(1, 100);
        if (null != serviceViewList && serviceViewList.getCount() > 0) {
            for (String serviceName : serviceViewList.getData()) {
                namingService.subscribe(serviceName, event -> {
                    NamingEvent namingEvent = (NamingEvent) event;
                    log.info("receive service event for serviceName={}, instances={}",
                            namingEvent.getServiceName(), namingEvent.getInstances());
                });
            }
        }
        return namingService;
    }

}
