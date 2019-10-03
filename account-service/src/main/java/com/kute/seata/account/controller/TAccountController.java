package com.kute.seata.account.controller;

import com.kute.seata.common.api.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * created by bailong001 on 2019/09/30 16:29
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class TAccountController {

    @Resource
    private AccountService accountService;

    @GetMapping("/dec_account")
    public int decreaseAccount(@RequestParam String ucId, @RequestParam Double amount) {
        log.info("请求账户微服务：ucid={}, amout={}", ucId, amount);
        return accountService.decreaseAccount(ucId, amount);
    }

    @GetMapping("/test_global_lock")
    public int testGlobalLock() {
        log.info("testGlobalLock");
        return accountService.testGlobalLock();
    }
}
