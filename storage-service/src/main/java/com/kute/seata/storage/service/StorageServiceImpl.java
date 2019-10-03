package com.kute.seata.storage.service;

import com.kute.seata.common.api.StorageService;
import com.kute.seata.common.entity.CommodityDTO;
import com.kute.seata.storage.mapper.StorageMapper;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by bailong001 on 2019/09/30 11:22
 */
@Slf4j
@Service(interfaceClass = StorageService.class)
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageMapper storageMapper;

    @Override
    public Object decreaseStorage(CommodityDTO commodityDTO) {
        log.info("全局事务ID：{}， commodityDTO={}", RootContext.getXID(), commodityDTO);
        return storageMapper.decreaseStorage(commodityDTO.getCommodityCode(), commodityDTO.getCount());
    }
}
