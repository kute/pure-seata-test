package com.kute.seata.order.controller;

import com.kute.seata.common.api.OrderService;
import com.kute.seata.common.entity.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * created by bailong001 on 2019/09/30 16:29
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class TOrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create_order")
    Object createOrder(@RequestBody OrderDTO orderDTO) {
        log.info("请求订单微服务：{}", orderDTO.toString());
        return orderService.createOrder(orderDTO);
    }

}
