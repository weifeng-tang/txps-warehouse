package com.txps.bus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.txps.bus.entity.CommercialTenant;
import com.txps.bus.mapper.CommercialTenantMapper;
import com.txps.bus.service.ICommercialTenantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
