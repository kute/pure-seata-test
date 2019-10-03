package com.kute.seata.account.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.kute.seata.account.service.PureNacosNamingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * created by bailong001 on 2019/10/01 19:31
 */
@Controller
@RequestMapping("discovery")
public class DiscoveryController {

    @Resource
    private PureNacosNamingService pureNacosNamingService;

    @GetMapping(value = "/get")
    @ResponseBody
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return pureNacosNamingService.getAllInstances(serviceName);
    }

}
