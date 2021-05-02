package com.txps.bus.vo;

import com.txps.bus.entity.GoodsSpecConvert;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: Wayne
 * @date: 2021/5/3
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsSpecConvertVo extends GoodsSpecConvert {

    /**
     * 分页参数，当前是第一页，每页10条数据
     */
    private Integer page=1;
    private Integer limit=10;
}
