package com.kute.seata.account.service;

import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by bailong001 on 2019/10/01 17:11
 */
@Service
public class PureNacosConfigService {

    @Resource
    private ConfigService configService;

    /**
     * 发布配置
     *
     * @param dataId
     * @param group
     * @param content
     * @return
     * @throws NacosException
     */
    public boolean publishConfig(String dataId, String group, String content) throws NacosException {
        return configService.publishConfig(dataId, group, content);
    }

    /**
     * 获取配置
     *
     * @param dataId
     * @param group
     * @param timeoutMs
     * @return
     * @throws NacosException
     */
    public String publishConfig(String dataId, String group, long timeoutMs) throws NacosException {
        return configService.getConfig(dataId, group, timeoutMs);
    }

    /**
     * 获取配置 并 注册监听器
     *
     * @param dataId
     * @param group
     * @param timeoutMs
     * @param listener
     * @return
     * @throws NacosException
     */
    public String getConfigAndSignListener(String dataId, String group, long timeoutMs, Listener listener) throws NacosException {
        return configService.getConfigAndSignListener(dataId, group, timeoutMs, listener);
    }

    /**
     * 移除配置
     *
     * @param dataId
     * @param group
     * @return
     * @throws NacosException
     */
    public boolean removeConfig(String dataId, String group) throws NacosException {
        return configService.removeConfig(dataId, group);
    }

    /**
     * 添加监听
     *
     * @param dataId
     * @param group
     * @param listener
     * @throws NacosException
     */
    public void addListener(String dataId, String group, Listener listener) throws NacosException {
        configService.addListener(dataId, group, listener);
    }

    /**
     * 移除监听
     *
     * @param dataId
     * @param group
     * @param listener
     */
    public void removeListener(String dataId, String group, Listener listener) {
        configService.removeListener(dataId, group, listener);
    }

    /**
     * 获取配置服务器状态
     *
     * @return
     */
    public String getServerStatus() {
        return configService.getServerStatus();
    }

}
