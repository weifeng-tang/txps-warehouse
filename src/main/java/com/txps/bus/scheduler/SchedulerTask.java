package com.txps.bus.scheduler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.txps.bus.entity.CommercialTenant;
import com.txps.bus.entity.CommercialTenantGoods;
import com.txps.bus.service.ICommercialTenantGoodsService;
import com.txps.bus.service.ICommercialTenantService;
import com.txps.bus.service.IExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: Wayne
 * @date: 2021/6/15
 */
@Slf4j
@Component
public class SchedulerTask {

    @Autowired
    private ICommercialTenantService commercialTenantService;
    @Autowired
    private ICommercialTenantGoodsService commercialTenantGoodsService;
    @Autowired
    private IExcelService excelService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void ctGoodsExcelHandleTask() {
        //1. 获取所有商户
        QueryWrapper<CommercialTenant> queryCtWrapper = new QueryWrapper<>();
        queryCtWrapper.eq( "status", 1);
        List<CommercialTenant> list = commercialTenantService.list(queryCtWrapper);
        //2. 根据商户id获取商户商品更新excel
        if (ObjectUtils.isNotEmpty(list)){
            list.forEach(ct -> {
                List<CommercialTenantGoods> ctGoods = commercialTenantGoodsService.queryAllGoodsByCtId(ct.getId());
                 //生成商户批量下单Excel表
                excelService.generateCtOrderExcel(ctGoods, ct);
            });
        }

    }

}
