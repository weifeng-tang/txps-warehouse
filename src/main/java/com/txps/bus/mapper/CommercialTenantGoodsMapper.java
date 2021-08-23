package com.txps.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.txps.bus.entity.CommercialTenantGoods;
import com.txps.bus.vo.CommercialTenantGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wayne
 * @since 2021-04-24
 */
public interface CommercialTenantGoodsMapper extends BaseMapper<CommercialTenantGoods> {

    /**
     * 根据条件查询商户商品信息
     * @param page
     * @param ctGoodsVo
     * @return
     */
    IPage<CommercialTenantGoods> queryPageByCondition(Page page, @Param("ctGoods")CommercialTenantGoodsVo ctGoodsVo);

    /**
     * 根据条件查询商户商品信息
     * @param ctId
     * @param goodsType
     * @return
     */
    List<CommercialTenantGoods> loadAllGoodsForSelect(@Param("ctId")Long ctId, @Param("goodsType")Integer goodsType);

    /**
     * 根据商户ID查询商户有效商品信息
     * @param ctId
     * @return
     */
    List<CommercialTenantGoods> queryAllGoodsByCtId(Long ctId);
}
