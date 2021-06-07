package com.txps.bus.controller;


import com.txps.bus.service.IOutboundOrderService;
import com.txps.bus.vo.OutboundOrderVo;
import com.txps.bus.vo.SalesReturnOrderVo;
import com.txps.sys.common.DataGridView;
import com.txps.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wayne
 * @since 2021-05-04
 */
@RestController
@RequestMapping("/outboundOrder")
public class OutboundOrderController {

    @Autowired
    private IOutboundOrderService outboundOrderService;

    /**
     * 查询所有商品销售信息
     * @param outboundOrderVo
     * @return
     */
    @RequestMapping("loadAllSales")
    public DataGridView loadAllSales(OutboundOrderVo outboundOrderVo){
        return outboundOrderService.pageList(outboundOrderVo);
    }

    /**
     * 添加商户商品订单
     * @param outboundOrderVo
     * @return
     */
    @RequestMapping("addOutboundOrder")
    public ResultObj addOutboundOrder(OutboundOrderVo outboundOrderVo){
        try {
            return outboundOrderService.addOutboundOrder(outboundOrderVo) ? ResultObj.ADD_SUCCESS : ResultObj.ADD_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改一个商户商品订单
     * @param outboundOrderVo
     * @return
     */
    @RequestMapping("updateOutboundOrder")
    public ResultObj updateOutboundOrder(OutboundOrderVo outboundOrderVo){
        try {
            outboundOrderService.updateById(outboundOrderVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 退货
     * @param salesReturnOrderVo
     * @return
     */
    @RequestMapping("salesReturn")
    public ResultObj salesReturn(SalesReturnOrderVo salesReturnOrderVo){
        try {
            outboundOrderService.salesReturn(salesReturnOrderVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 发货
     * @param outboundOrderVo
     * @return
     */
    @RequestMapping("shipments")
    public ResultObj shipments(OutboundOrderVo outboundOrderVo){
        try {
            outboundOrderService.shipments(outboundOrderVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

}

