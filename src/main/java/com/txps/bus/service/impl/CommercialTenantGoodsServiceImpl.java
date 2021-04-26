package com.txps.bus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txps.bus.entity.CommercialTenant;
import com.txps.bus.entity.CommercialTenantGoods;
import com.txps.bus.mapper.CommercialTenantGoodsMapper;
import com.txps.bus.mapper.CommercialTenantMapper;
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
    public boolean save(CommercialTenantGoods entity) {
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    @Override
    public boolean updateById(CommercialTenantGoods entity) {
        entity.setUpdateTime(new Date());
        return super.updateById(entity);
    }
}
