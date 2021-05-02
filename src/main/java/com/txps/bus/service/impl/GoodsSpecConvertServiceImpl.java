package com.txps.bus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txps.bus.entity.GoodsSpecConvert;
import com.txps.bus.mapper.GoodsSpecConvertMapper;
import com.txps.bus.service.IGoodsSpecConvertService;
import com.txps.bus.vo.GoodsSpecConvertVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wayne
 * @since 2021-05-03
 */
@Service
public class GoodsSpecConvertServiceImpl extends ServiceImpl<GoodsSpecConvertMapper, GoodsSpecConvert> implements IGoodsSpecConvertService {

    @Autowired
    private GoodsSpecConvertMapper goodsSpecConvertMapper;

    @Override
    public IPage<GoodsSpecConvert> queryPageByCondition(GoodsSpecConvertVo goodsSpecConvertVo) {
        Page<GoodsSpecConvert> page = new Page<>(goodsSpecConvertVo.getPage(), goodsSpecConvertVo.getLimit());
        return goodsSpecConvertMapper.queryPageByCondition(page, goodsSpecConvertVo);
    }

    @Override
    public boolean save(GoodsSpecConvert entity) {
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    @Override
    public boolean updateById(GoodsSpecConvert entity) {
        entity.setUpdateTime(new Date());
        return super.updateById(entity);
    }

}
