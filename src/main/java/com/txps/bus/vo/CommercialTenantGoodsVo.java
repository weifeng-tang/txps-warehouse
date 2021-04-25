package com.txps.bus.vo;

import com.txps.bus.entity.CommercialTenantGoods;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: Wayne
 * @date: 2021/4/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommercialTenantGoodsVo extends CommercialTenantGoods {
    /**
     * 分页参数，当前是第一页，每页10条数据
     */
    private Integer page=1;
    private Integer limit=10;
}
