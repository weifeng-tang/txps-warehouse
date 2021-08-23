package com.txps.bus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txps.bus.entity.CommercialTenant;
import com.txps.bus.entity.CommercialTenantGoods;
import com.txps.bus.entity.CtGoodsUpdateLog;
import com.txps.bus.mapper.CommercialTenantGoodsMapper;
import com.txps.bus.mapper.CommercialTenantMapper;
import com.txps.bus.mapper.CtGoodsUpdateLogMapper;
import com.txps.bus.service.ICommercialTenantGoodsService;
import com.txps.bus.vo.CommercialTenantGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wayne
 * @since 2021-04-24
 */
@Service
public class CommercialTenantGoodsServiceImpl extends ServiceImpl<CommercialTenantGoodsMapper, CommercialTenantGoods> implements ICommercialTenantGoodsService {

    @Autowired
    private CommercialTenantGoodsMapper commercialTenantGoodsMapper;
    @Autowired
    private CommercialTenantMapper commercialTenantMapper;
    @Autowired
    private CtGoodsUpdateLogMapper ctGoodsUpdateLogMapper;


    @Override
    public IPage<CommercialTenantGoods> queryPageByCondition(CommercialTenantGoodsVo ctGoodsVo) {
        Page<CommercialTenantGoods> page = new Page<>(ctGoodsVo.getPage(), ctGoodsVo.getLimit());
        IPage<CommercialTenantGoods> ctgPage = commercialTenantGoodsMapper.queryPageByCondition(page, ctGoodsVo);
        List<CommercialTenantGoods> ctgs = ctgPage.getRecords();
        if (!ObjectUtils.isEmpty(ctgs)){
            for (CommercialTenantGoods ctg : ctgs) {
                CommercialTenant ct = commercialTenantMapper.selectById(ctg.getCommercialTenantId());
                if (!ObjectUtils.isEmpty(ct)){
                    ctg.setCommercialTenantName(ct.getName());
                }

            }
        }
        return ctgPage;
    }

    @Override
    public List<CommercialTenantGoods> loadAllGoodsForSelect(Long ctId, Integer goodsType) {
        return commercialTenantGoodsMapper.loadAllGoodsForSelect(ctId, goodsType);
    }

    @Override
    public List<CommercialTenantGoods> queryAllGoodsByCtId(Long ctId) {
        return commercialTenantGoodsMapper.queryAllGoodsByCtId(ctId);
    }

    @Override
    public boolean save(CommercialTenantGoods entity) {
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    @Override
    public boolean updateById(CommercialTenantGoods entity) {
        entity.setUpdateTime(new Date());
        //添加修改记录
        CtGoodsUpdateLog ctGoodsUpdateLog = buildParam(entity);
        ctGoodsUpdateLogMapper.insert(ctGoodsUpdateLog);
        return super.updateById(entity);
    }

    /**
     * 构建商户商品修改记录参数
     * @param commercialTenantGoods
     * @return
     */
    private CtGoodsUpdateLog buildParam(CommercialTenantGoods commercialTenantGoods){
        CtGoodsUpdateLog ctGoodsUpdateLog = new CtGoodsUpdateLog();
        ctGoodsUpdateLog.setCommercialTenantId(commercialTenantGoods.getCommercialTenantId());
        CommercialTenant ct = commercialTenantMapper.selectById(commercialTenantGoods.getCommercialTenantId());
        ctGoodsUpdateLog.setCommercialTenantName(ct.getName());
        ctGoodsUpdateLog.setCtGoodsId(commercialTenantGoods.getId());
        ctGoodsUpdateLog.setCtGoodsName(commercialTenantGoods.getGoodsAnotherName());
        ctGoodsUpdateLog.setUnitPrice(commercialTenantGoods.getUnitPrice());
        ctGoodsUpdateLog.setSpecification(commercialTenantGoods.getSpecification());
        ctGoodsUpdateLog.setStatus(commercialTenantGoods.getStatus() == 0 ? "不可用" : "可用");
        ctGoodsUpdateLog.setCreateTime(new Date());
        ctGoodsUpdateLog.setUpdateTime(new Date());
        return ctGoodsUpdateLog;
    }
}
