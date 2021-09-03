package com.txps.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txps.bus.entity.*;
import com.txps.bus.exception.BusinessException;
import com.txps.bus.mapper.*;
import com.txps.bus.service.IOutboundOrderService;
import com.txps.bus.vo.OutboundOrderVo;
import com.txps.bus.vo.SalesReturnOrderVo;
import com.txps.sys.common.Constast;
import com.txps.sys.common.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wayne
 * @since 2021-05-04
 */
@Service
public class OutboundOrderServiceImpl extends ServiceImpl<OutboundOrderMapper, OutboundOrder> implements IOutboundOrderService {

    @Autowired
    private OutboundOrderMapper outboundOrderMapper;
    @Autowired
    private CommercialTenantGoodsMapper commercialTenantGoodsMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsSpecConvertMapper goodsSpecConvertMapper;
    @Autowired
    private SalesReturnOrderMapper salesReturnOrderMapper;


    @Override
    public DataGridView pageList(OutboundOrderVo outboundOrderVo) {
        if (outboundOrderVo.getTime() == null || outboundOrderVo.getTime() == 0){
            Calendar instance = Calendar.getInstance();
            instance.setTime(new Date());
            instance.set(Calendar.HOUR_OF_DAY, 0);
            instance.set(Calendar.MINUTE, 0);
            instance.set(Calendar.SECOND, 0);
            outboundOrderVo.setStartTime(instance.getTime());
            instance.add(Calendar.DATE, 1);
            outboundOrderVo.setEndTime(instance.getTime());
        }
        Page<OutboundOrder> page = new Page<>(outboundOrderVo.getPage(), outboundOrderVo.getLimit());
        IPage<OutboundOrder> outboundOrderIPage = outboundOrderMapper.pageList(page, outboundOrderVo);
        return new DataGridView(outboundOrderIPage.getPages(), outboundOrderIPage.getRecords());
    }

    @Override
    public Boolean addOutboundOrder(OutboundOrderVo outboundOrderVo) {
        //根据商户商品id找到上级商品id
        CommercialTenantGoods commercialTenantGoods = commercialTenantGoodsMapper.selectById(outboundOrderVo.getCtGoodsId());
        outboundOrderVo.setGoodsId(commercialTenantGoods.getGoodsId());
        //更加商品id获取商品实际出库规格
        Goods goods = goodsMapper.selectById(commercialTenantGoods.getGoodsId());
        if (StringUtils.isBlank(goods.getBasedSpecifications())){
            //订单失败, 无基础规格
            throw new BusinessException("ERROR", "订单失败, 无基础规格");
        }
        outboundOrderVo.setActualOutboundSpec(goods.getBasedSpecifications());
        if (StringUtils.isBlank((outboundOrderVo.getOrderSpec()))) {
            outboundOrderVo.setOrderSpec(commercialTenantGoods.getSpecification());
        }
        if (goods.getBasedSpecifications().equals(outboundOrderVo.getOrderSpec())){
            outboundOrderVo.setActualSpecOrderQuantity(outboundOrderVo.getOrderQuantity());
        }else {
            //下单规格与商品基础规格不一致,需要转换
            GoodsSpecConvert gsc = getGoodsSpecConvertObject(outboundOrderVo);
            if (ObjectUtils.isEmpty(gsc)){
                //订单失败,无对应的转换对象
                throw new BusinessException("ERROR", "订单失败,无对应的转换对象");
            }
            //转换, 下单数 * 转换率
            outboundOrderVo.setActualSpecOrderQuantity(outboundOrderVo.getOrderQuantity().multiply(gsc.getConvertRatio()));
        }
        //下单时间
        outboundOrderVo.setOrderTime(new Date());
        //商户商品当天单价
        outboundOrderVo.setCtGoodsUnitPriceThatDay(commercialTenantGoods.getUnitPrice());
        //状态, 1-下单成功
        outboundOrderVo.setStatus(1);
        //创建时间
        outboundOrderVo.setCreateTime(new Date());
        return super.save(outboundOrderVo);
    }

    @Override
    public Boolean salesReturn(SalesReturnOrderVo salesReturnOrderVo) {
        //1.改单
        Long outboundOrderId = salesReturnOrderVo.getOutboundOrderId();
        if (outboundOrderId == null){
            //todo 无此订单号
        }
        OutboundOrder outboundOrder = outboundOrderMapper.selectById(outboundOrderId);
        if (ObjectUtils.isEmpty(outboundOrder)){
            //todo 无此订单号
        }
        if (outboundOrder.getActualOutboundQuantity().compareTo(salesReturnOrderVo.getSalesReturnNum()) < 0) {
            //todo 退货数必须少于或等于实际出货数
        }
        outboundOrder.setActualOutboundQuantity(outboundOrder.getActualOutboundQuantity().subtract(salesReturnOrderVo.getSalesReturnNum()));
        outboundOrder.setUpdateTime(new Date());
        outboundOrderMapper.updateById(outboundOrder);
        //2.退货记录
        salesReturnOrderVo.setCtId(outboundOrder.getCtId());
        salesReturnOrderVo.setCtGoodsId(outboundOrder.getCtGoodsId());
        salesReturnOrderVo.setActualOutboundSpec(outboundOrder.getActualOutboundSpec());
        salesReturnOrderVo.setCreateTime(new Date());
        salesReturnOrderMapper.insert(salesReturnOrderVo);
        return true;
    }

    @Override
    public Boolean shipments(OutboundOrderVo outboundOrderVo) {
        OutboundOrder outboundOrder = outboundOrderMapper.selectById(outboundOrderVo.getId());
        outboundOrder.setActualOutboundQuantity(outboundOrderVo.getActualOutboundQuantity());
        outboundOrder.setAmount(outboundOrderVo.getActualOutboundQuantity().multiply(outboundOrder.getCtGoodsUnitPriceThatDay()));
        outboundOrder.setStatus(2);
        outboundOrder.setUpdateTime(new Date());
        return outboundOrderMapper.updateById(outboundOrder) > 0;
    }

    @Override
    public boolean updateById(OutboundOrder outboundOrder){
        //根据商户商品id找到上级商品id
        CommercialTenantGoods commercialTenantGoods = commercialTenantGoodsMapper.selectById(outboundOrder.getCtGoodsId());
        outboundOrder.setGoodsId(commercialTenantGoods.getGoodsId());
        //更加商品id获取商品实际出库规格
        Goods goods = goodsMapper.selectById(commercialTenantGoods.getGoodsId());
        if (StringUtils.isNotBlank(goods.getBasedSpecifications())){
            //todo 订单失败, 无基础规格
        }
        outboundOrder.setActualOutboundSpec(goods.getBasedSpecifications());
        if (goods.getBasedSpecifications().equals(outboundOrder.getOrderSpec())){
            outboundOrder.setActualSpecOrderQuantity(outboundOrder.getOrderQuantity());
        }else {
            //下单规格与商品基础规格不一致,需要转换
            GoodsSpecConvert gsc = getGoodsSpecConvertObject(outboundOrder);
            if (ObjectUtils.isEmpty(gsc)){
                //todo 订单失败,无对应的转换对象
            }
            //转换, 下单数 * 转换率
            outboundOrder.setActualSpecOrderQuantity(outboundOrder.getOrderQuantity().multiply(gsc.getConvertRatio()));
        }
        return super.updateById(outboundOrder);
    }

    /**
     * 获取商品规格转换对象
     * @param outboundOrder
     * @return
     */
    private GoodsSpecConvert getGoodsSpecConvertObject(OutboundOrder outboundOrder){
        QueryWrapper<GoodsSpecConvert> queryWrapper = new QueryWrapper<>();
        if (ObjectUtils.isEmpty(outboundOrder.getGoodsId()) && ObjectUtils.isEmpty(outboundOrder.getActualOutboundSpec())){
            //todo 订单失败,无对应的转换类型
        }
        queryWrapper.eq(!ObjectUtils.isEmpty(outboundOrder.getGoodsId()), "goods_id", outboundOrder.getGoodsId());
        queryWrapper.eq(!ObjectUtils.isEmpty(outboundOrder.getOrderSpec()), "convert_spec", outboundOrder.getOrderSpec());
        return goodsSpecConvertMapper.selectOne(queryWrapper);
    }


}
