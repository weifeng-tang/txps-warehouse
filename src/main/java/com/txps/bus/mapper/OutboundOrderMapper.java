package com.txps.bus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.txps.bus.entity.OutboundOrder;
import com.txps.bus.vo.OutboundOrderVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wayne
 * @since 2021-05-04
 */
public interface OutboundOrderMapper extends BaseMapper<OutboundOrder> {

    IPage<OutboundOrder> pageList(Page page, @Param("outboundOrderVo") OutboundOrderVo outboundOrderVo);
}
