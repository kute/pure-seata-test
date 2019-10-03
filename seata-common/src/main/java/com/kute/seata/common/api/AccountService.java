package com.kute.seata.common.api;

/**
 * created by bailong001 on 2019/09/30 11:21
 */
public interface AccountService {

    /**
     * 余额扣款
     *
     * @param userId 用户ID
     * @param money  扣款金额
     */
    int decreaseAccount(String userId, Double money);

    int testGlobalLock();

}
