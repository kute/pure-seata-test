package com.kute.seata.account.service;

import com.alibaba.boot.nacos.discovery.properties.NacosDiscoveryProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by bailong001 on 2019/10/02 09:38
 */
@Slf4j
@Service
public class PureNacosNamingService {

    // 使用nacos生成的namingService实例
    //    @NacosInjected
    // 使用自定义生成的namingService
    @Resource
    private NamingService namingService;
    @Autowired
    private NacosDiscoveryProperties discoveryProperties;

    public List<Instance> getAllInstances(String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    /**
     * 监听指定 服务
     *
     * @param serviceName
     * @throws NacosException
     */
    public void subscribe(String serviceName) throws NacosException {
        namingService.subscribe(serviceName, event -> {
            NamingEvent namingEvent = (NamingEvent) event;
            log.info("receive service event for serviceName={}, instances={}",
                    namingEvent.getServiceName(), namingEvent.getInstances());
        });
    }
}
