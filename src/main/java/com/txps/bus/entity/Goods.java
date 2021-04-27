package com.txps.bus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@TableName("bus_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 供应商id
     */
    private Integer providerId;

    /**
     * 商品图片
     */
    private String goodsImg;

    /**
     * 商品类型
     */
    private Integer type;

    /**
     * 商品库存
     */
    private Integer number;

    /**
     * 商品库存预警值
     */
    private Integer dangerNum;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 状态
     */
    private Integer isCollectTotal;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
