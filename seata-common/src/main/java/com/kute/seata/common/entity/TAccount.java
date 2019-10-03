package com.kute.seata.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * created by bailong001 on 2019/09/30 12:20
 */
@Data
@Accessors(chain = true)
public class TAccount implements Serializable {

    private Integer id;
    private String userId;
    private Double amount;
}
