package com.kute.seata.order.service;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.kute.seata.common.api.AccountService;
import com.kute.seata.common.api.OrderService;
import com.kute.seata.common.entity.OrderDTO;
import com.kute.seata.common.entity.TOrder;
import com.kute.seata.order.mapper.OrderMapper;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * created by bailong001 on 2019/09/30 11:22
 */
@Slf4j
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Reference
    private AccountService accountService;

    @Override
    public Object createOrder(OrderDTO orderDTO) {
        log.info("全局事务ID：{}， orderDTO={}", RootContext.getXID(), orderDTO);

        int lines = accountService.decreaseAccount(orderDTO.getUserId(), orderDTO.getOrderAmount().doubleValue());

        orderDTO.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        //生成订单
        TOrder tOrder = new TOrder();
        BeanUtils.copyProperties(orderDTO, tOrder);
        tOrder.setCount(orderDTO.getOrderCount());
        tOrder.setAmount(orderDTO.getOrderAmount().doubleValue());

        orderMapper.createOrder(tOrder);

        return true;
    }
}
