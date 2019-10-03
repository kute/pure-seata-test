package com.kute.seata.storage.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * created by bailong001 on 2019/09/30 12:03
 */
public interface StorageMapper {

    /**
     * 扣减商品库存
     * @Param: commodityCode 商品code  count扣减数量
     * @Return:
     */
    int decreaseStorage(@Param("commodityCode") String commodityCode, @Param("count") Integer count);
}
