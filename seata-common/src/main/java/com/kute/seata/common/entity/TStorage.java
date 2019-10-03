package com.kute.seata.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * created by bailong001 on 2019/10/03 14:32
 */
@Data
@Accessors(chain = true)
public class TStorage implements Serializable {

    private Integer id;
    private String commodityCode;
    private String name;
    private Integer count;

}
