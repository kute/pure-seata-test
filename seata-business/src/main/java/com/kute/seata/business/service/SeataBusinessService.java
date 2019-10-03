package com.kute.seata.business.service;

import com.kute.seata.common.api.OrderService;
import com.kute.seata.common.api.StorageService;
import com.kute.seata.common.entity.BusinessDTO;
import com.kute.seata.common.entity.CommodityDTO;
import com.kute.seata.common.entity.OrderDTO;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * created by bailong001 on 2019/09/30 11:22
 */
@Slf4j
@Service
public class SeataBusinessService {

    @Reference
    private StorageService storageService;
    @Reference
    private OrderService orderService;

    @GlobalTransactional(timeoutMills = 300000, name = "pure-business-service")
    public Object handleBusiness(BusinessDTO businessDTO) {

        log.info("开始全局事务，XID = {}", RootContext.getXID());
        //1、扣减库存
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode(businessDTO.getCommodityCode());
        commodityDTO.setCount(businessDTO.getCount());
        Object storageResponse = storageService.decreaseStorage(commodityDTO);
        //2、创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(businessDTO.getUserId());
        orderDTO.setCommodityCode(businessDTO.getCommodityCode());
        orderDTO.setOrderCount(businessDTO.getCount());
        orderDTO.setOrderAmount(businessDTO.getAmount());
        Object response = orderService.createOrder(orderDTO);

        //打开注释测试事务发生异常后，全局回滚功能
//        if (!flag) {
//            throw new RuntimeException("测试抛异常后，分布式事务回滚！");
//        }

        return true;
    }
}
