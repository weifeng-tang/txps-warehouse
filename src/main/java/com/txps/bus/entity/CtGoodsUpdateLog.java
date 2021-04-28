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
 * @since 2021-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_ct_goods_update_log")
public class CtGoodsUpdateLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联商户id
     */
    private Long commercialTenantId;

    /**
     * 商户名称
     */
    private String commercialTenantName;

    /**
     * 关联商户商品id
     */
    private Long ctGoodsId;

    /**
     * 关联商户商品别名
     */
    private String ctGoodsName;

    /**
     * 商品单价
     */
    private BigDecimal unitPrice;

    /**
     * 商品规格
     */
    private String specification;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
