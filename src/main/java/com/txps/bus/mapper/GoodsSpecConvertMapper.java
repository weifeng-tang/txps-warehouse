package com.txps.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.txps.bus.entity.GoodsSpecConvert;
import com.txps.bus.vo.GoodsSpecConvertVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wayne
 * @since 2021-05-03
 */
public interface GoodsSpecConvertMapper extends BaseMapper<GoodsSpecConvert> {

    /**
     * 根据条件查询商品规格转换信息
     * @param page
     * @param gscv
     * @return
     */
    IPage<GoodsSpecConvert> queryPageByCondition(Page page, @Param("gscv") GoodsSpecConvertVo gscv);
}
