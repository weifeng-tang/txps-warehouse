package com.txps.bus.service;

import com.txps.bus.entity.OutboundOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.txps.bus.vo.OutboundOrderVo;
import com.txps.sys.common.DataGridView;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wayne
 * @since 2021-05-04
 */
public interface IOutboundOrderService extends IService<OutboundOrder> {
    /**
     * 获取下单列表
     * @param outboundOrderVo
     * @return
     */
    DataGridView pageList(OutboundOrderVo outboundOrderVo);

    /**
     * 商户下单
     * @param ctGoodsVo
     * @return
     */
    Boolean addOutboundOrder(OutboundOrderVo ctGoodsVo);
}
