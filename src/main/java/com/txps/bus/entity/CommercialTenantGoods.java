package com.txps.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_commercial_tenant_goods")
public class CommercialTenantGoods implements Serializable {

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
     * 关联商品id
     */
    private Long goodsId;

    /**
     * 商品别名
     */
    private String goodsAnotherName;

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
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 商品类型
     */
    @TableField(exist = false)
    private Integer type;

    /**
     * 商品描述
     */
    @TableField(exist = false)
    private String description;

    /**
     * 关联商户名称
     */
    private String commercialTenantName;

}
