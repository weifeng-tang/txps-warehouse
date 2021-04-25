package com.txps.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txps.bus.entity.Goods2;
import com.txps.bus.entity.Provider;
import com.txps.bus.mapper.GoodsMapper2;
import com.txps.bus.mapper.ProviderMapper;
import com.txps.bus.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * InnoDB free: 9216 kB 服务实现类
 * </p>
 *
 * @author luoyi-
 * @since 2019-12-05
 */
@Service
@Transactional
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements IProviderService {

    @Autowired
    private GoodsMapper2 goodsMapper;

    @Override
    public boolean save(Provider entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Provider entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }
    @Override
    public Provider getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    /**
     * 根据供应商id删除供应商
     * @param id    供应商id
     */
    @Override
    public void deleteProviderById(Integer id) {
        //根据供应商id查询出商品id
        QueryWrapper<Goods2> queryWrapper = new QueryWrapper<Goods2>();
        queryWrapper.eq("providerid",id);
        List<Goods2> goods = goodsMapper.selectList(queryWrapper);
        for (Goods2 good : goods) {
            //获取一个商品id
            Integer id1 = good.getId();
            //根据商品id删除商品销售信息
            goodsMapper.deleteSaleByGoodsId(id1);
            //根据商品id删除商品销售退货信息
            goodsMapper.deleteSaleBackByGoodsId(id1);
        }
        //根据供应商id删除商品退货信息
        this.getBaseMapper().deleteOutPortByProviderId(id);
        //根据供应商id删除商品进货信息
        this.getBaseMapper().deleteInportByProviderId(id);
        //根据供应商id删除商品
        this.getBaseMapper().deleteGoodsByProviderId(id);
        //删除供应商
        this.removeById(id);
    }
}
