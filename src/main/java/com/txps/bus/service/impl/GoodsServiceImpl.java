package com.txps.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txps.bus.entity.Goods;
import com.txps.bus.mapper.GoodsMapper;
import com.txps.bus.service.IGoodsService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wayne
 * @since 2021-04-24
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public boolean save(Goods entity) {
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    @Override
    public boolean updateById(Goods entity) {
        entity.setUpdateTime(new Date());
        return super.updateById(entity);
    }

}
