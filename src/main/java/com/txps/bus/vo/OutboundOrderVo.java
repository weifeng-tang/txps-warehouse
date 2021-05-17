package com.txps.bus.vo;

import com.txps.bus.entity.OutboundOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @description:
 * @author: Wayne
 * @date: 2021/5/4
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OutboundOrderVo extends OutboundOrder {
    private Integer page=1;
    private Integer limit=10;

    private Integer time;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    /**
     * 商品类型
     */
    private Integer type;

    /**
     * 商户商品名称
     */
    private String ctGoodsName;
}
