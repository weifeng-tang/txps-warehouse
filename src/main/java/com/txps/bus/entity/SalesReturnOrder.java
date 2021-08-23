package com.txps.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wayne
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_sales_return_order")
public class SalesReturnOrder implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商户id
     */
    private Long ctId;

    /**
     * 商户商品id
     */
    private Long ctGoodsId;

    /**
     * 出库订单id
     */
    private Long outboundOrderId;

    /**
     * 实际出库规格
     */
    private String actualOutboundSpec;

    /**
     * 实际出库数
     */
    private BigDecimal actualOutboundQuantity;

    /**
     * 退货数
     */
    private BigDecimal salesReturnNum;

    /**
     * 退货说明
     */
    private String salesReturnExplain;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
