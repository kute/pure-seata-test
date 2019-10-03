package com.kute.seata.common.api;


import com.kute.seata.common.entity.OrderDTO;

/**
 * created by bailong001 on 2019/09/30 11:21
 */
public interface OrderService {

    Object createOrder(OrderDTO orderDTO);

}
