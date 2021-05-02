package com.txps.bus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txps.bus.entity.GoodsSpecConvert;
import com.txps.bus.vo.GoodsSpecConvertVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wayne
 * @since 2021-05-03
 */
public interface IGoodsSpecConvertService extends IService<GoodsSpecConvert> {

    IPage<GoodsSpecConvert> queryPageByCondition(GoodsSpecConvertVo goodsSpecConvertVo);
}
