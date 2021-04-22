package com.txps.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txps.bus.entity.CommercialTenant;
import com.txps.bus.mapper.CommercialTenantMapper;
import com.txps.bus.service.ICommercialTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wayne
 * @since 2021-04-21
 */
@Service
@Transactional
public class CommercialTenantServiceImpl extends ServiceImpl<CommercialTenantMapper, CommercialTenant> implements ICommercialTenantService {

    @Autowired
    private CommercialTenantMapper commercialTenantMapper;

    @Override
    public boolean save(CommercialTenant entity) {
        entity.setCreateTime(new Date());
        return super.save(entity);
    }

    @Override
    public boolean updateById(CommercialTenant entity) {
        entity.setUpdateTime(new Date());
        return super.updateById(entity);
    }

    @Override
    public void deleteCommercialTenantById(Long id) {
        this.removeById(id);
    }

}
