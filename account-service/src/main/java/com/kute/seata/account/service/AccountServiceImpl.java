package com.kute.seata.account.service;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.kute.seata.account.mapper.AccountMapper;
import com.kute.seata.common.api.AccountService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by bailong001 on 2019/09/30 11:22
 */
@Slf4j
@Service(interfaceClass = AccountService.class)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @NacosValue(value = "${accountUserMoney:3}", autoRefreshed = true)
    private Double monety;

    @Override
    public int decreaseAccount(String userId, Double money) {
        if (null == money) {
            money = this.monety;
        }
        log.info("decreaseAccount param userId={}, money={}, xid={}",
                userId, money, RootContext.getXID());
//        if(money > 0) {
//            throw new RuntimeException("抛出异常");
//        }
        return accountMapper.decreaseAccount(userId, money);
    }

    @Override
    @GlobalLock
    @Transactional(rollbackFor = {Throwable.class})
    public int testGlobalLock() {
        return accountMapper.testGlobalLock("1");
    }
}
