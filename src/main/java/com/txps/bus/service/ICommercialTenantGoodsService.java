package com.txps.bus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txps.bus.entity.CommercialTenantGoods;
import com.txps.bus.vo.CommercialTenantGoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wayne
 * @since 2021-04-24
 */
public interface ICommercialTenantGoodsService extends IService<CommercialTenantGoods> {

    IPage<CommercialTenantGoods> queryPageByCondition(CommercialTenantGoodsVo commercialTenantGoodsVo);

    List<CommercialTenantGoods> loadAllGoodsForSelect(Long ctId, Integer goodsType);

    List<CommercialTenantGoods> queryAllGoodsByCtId(Long ctId);
}
