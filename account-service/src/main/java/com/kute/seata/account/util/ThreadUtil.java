package com.kute.seata.account.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * created by bailong001 on 2018/10/12 16:21
 */
public final class ThreadUtil {

    public static final ExecutorService CORETHREADPOOLEXECUTOR =
            new ThreadPoolExecutor(100,
                    100,
                    30,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<>(Integer.MAX_VALUE),
                    new ThreadFactoryBuilder().setNameFormat("nacos-config-thread-%d").build(),
                    new ThreadPoolExecutor.AbortPolicy());
}
