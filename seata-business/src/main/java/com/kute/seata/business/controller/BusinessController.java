package com.kute.seata.business.controller;

import com.kute.seata.business.service.SeataBusinessService;
import com.kute.seata.common.entity.BusinessDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by bailong001 on 2019/09/30 16:29
 */
@RestController
@RequestMapping("/business/dubbo")
@Slf4j
public class BusinessController {

    @Resource
    private SeataBusinessService seataBusinessService;

    /**
     * 模拟用户购买商品下单业务逻辑流程
     *
     * @Param:
     * @Return:
     */
    @PostMapping("/buy")
    Object handleBusiness(@RequestBody BusinessDTO businessDTO) {
        log.info("请求参数：{}", businessDTO.toString());
        return seataBusinessService.handleBusiness(businessDTO);
    }

}
