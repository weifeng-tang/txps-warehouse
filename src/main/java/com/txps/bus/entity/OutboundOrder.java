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
 * @since 2021-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_outbound_order")
public class OutboundOrder implements Serializable {

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
     * 商品id
     */
    private Long goodsId;

    /**
     * 下单规格
     */
    private String orderSpec;

    /**
     * 下单数
     */
    private BigDecimal orderQuantity;

    /**
     * 实际出库规格
     */
    private String actualOutboundSpec;

    /**
     * 实际出库规格下单数
     */
    private BigDecimal actualSpecOrderQuantity;

    /**
     * 实际出库数
     */
    private BigDecimal actualOutboundQuantity;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 商户当天商品单价(对应时间出库规格)
     */
    private BigDecimal ctGoodsUnitPriceThatDay;

    /**
     * 金额(实际出库数 * 商户当天商品单价)
     */
    private BigDecimal amount;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 商户订单类型: 1-客餐, 2-员工餐, 3-消耗品, 4-补单, 5-退货单
     */
    private Integer ctOrderType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private String ctName;

    private String goodsType;

    private String goodsAnotherName;
}
