package com.kute.seata.order.mapper;

import com.kute.seata.common.entity.TOrder;
import org.apache.ibatis.annotations.Param;

/**
 * created by bailong001 on 2019/09/30 12:03
 */
public interface OrderMapper {

    /**
     * 创建订单
     *
     * @Param: order 订单信息
     * @Return:
     */
    void createOrder(@Param("order") TOrder order);
}
