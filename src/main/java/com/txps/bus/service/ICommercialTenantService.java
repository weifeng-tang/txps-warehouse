package com.txps.bus.service;

import com.txps.bus.entity.CommercialTenant;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wayne
 * @since 2021-04-21
 */
public interface ICommercialTenantService extends IService<CommercialTenant> {

    /**
     * 根据客户id删除商户
     * @param id    商户id
     */
    void deleteCommercialTenantById(Long id);
}
