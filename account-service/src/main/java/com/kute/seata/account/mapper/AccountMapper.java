package com.kute.seata.account.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * created by bailong001 on 2019/09/30 12:03
 */
public interface AccountMapper {

    int decreaseAccount(@Param("userId") String userId, @Param("amount") Double amount);

    int testGlobalLock(@Param("userId") String userId);
}
