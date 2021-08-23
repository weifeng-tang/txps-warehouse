package com.txps.bus.service;

import com.txps.bus.entity.CommercialTenant;
import com.txps.bus.entity.CommercialTenantGoods;

import java.util.List;

/**
 * @description:
 * @author: Wayne
 * @date: 2021/6/20
 */
public interface IExcelService {

    Boolean generateCtOrderExcel(List<CommercialTenantGoods> ctGoods, CommercialTenant ct);
}
