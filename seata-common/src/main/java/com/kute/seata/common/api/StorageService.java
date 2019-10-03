package com.kute.seata.common.api;

import com.kute.seata.common.entity.CommodityDTO;

/**
 * created by bailong001 on 2019/10/03 14:23
 */
public interface StorageService {

    /**
     * 扣减库存
     */
    Object decreaseStorage(CommodityDTO commodityDTO);
}
