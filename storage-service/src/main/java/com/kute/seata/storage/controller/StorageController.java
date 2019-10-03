package com.kute.seata.storage.controller;

import com.kute.seata.common.api.StorageService;
import com.kute.seata.common.entity.CommodityDTO;
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
@RequestMapping("/storage")
@Slf4j
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping("dec_storage")
    Object decreaseStorage(@RequestBody CommodityDTO commodityDTO) {
        log.info("请求库存微服务：{}", commodityDTO.toString());
        return storageService.decreaseStorage(commodityDTO);
    }

}
