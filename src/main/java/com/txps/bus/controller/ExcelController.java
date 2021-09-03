package com.txps.bus.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.txps.bus.entity.CommercialTenant;
import com.txps.bus.exception.BusinessException;
import com.txps.bus.service.ICommercialTenantService;
import com.txps.bus.service.IOutboundOrderService;
import com.txps.bus.vo.OutboundOrderVo;
import com.txps.sys.common.Constast;
import com.txps.sys.common.DataGridView;
import com.txps.sys.common.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: Wayne
 * @date: 2021/8/25
 */
@RestController
@Slf4j
public class ExcelController {

    @Autowired
    private ICommercialTenantService commercialTenantService;
    @Autowired
    private IOutboundOrderService outboundOrderService;


    @RequestMapping(value = "/batchOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResultObj batchOrder(MultipartFile file, String ctId, String orderType){
        try {
            try (InputStream inputStream = file.getInputStream()){
                ExcelReader excelReader = ExcelUtil.getReader(inputStream);
                List<List<Object>> excel = excelReader.read(0, excelReader.getRowCount());
                //1.校验店铺名称
                CommercialTenant ct = commercialTenantService.getById(ctId);
                if (!excel.get(0).get(0).equals(ct.getName())) {
                    return new ResultObj(Constast.ERROR,"请验证所属商户");
                }
                //2.校验格式
                List<Object> header = excel.get(1);
                if (!"主键".equals(header.get(0))
                        || !"名称".equals(header.get(1))
                        || !"商品规格".equals(header.get(2))
                        || !"单价".equals(header.get(3))
                        || !"下单数(不填默认不下单)".equals(header.get(4))
                        || !"订单规格(不填默认商品规格)".equals(header.get(5))
                        || !"备注(可不填)".equals(header.get(6))) {
                    return new ResultObj(Constast.ERROR,"Excel文件格式不正确");
                }
                //3.获取商品Id以及下单数
                for (int i = 2; i < excel.size(); i++) {
                    List<Object> orderParams = excel.get(i);
                    //判断是否需要下单, 为空跳过
                    if (StringUtils.isBlank(orderParams.get(4).toString())) {
                        continue;
                    }
                    //封装下单参数列表
                    OutboundOrderVo outboundOrderVo = new OutboundOrderVo();
                    outboundOrderVo.setCtGoodsId(Long.valueOf(orderParams.get(0).toString()));
                    outboundOrderVo.setOrderQuantity(new BigDecimal(orderParams.get(4).toString()));
                    outboundOrderVo.setOrderSpec(orderParams.get(5).toString());
                    outboundOrderVo.setCtId(Long.valueOf(ctId));
                    outboundOrderVo.setCtOrderType(Integer.valueOf(orderType));
                    outboundOrderVo.setRemark(orderParams.get(6).toString());
                    try {
                        outboundOrderService.addOutboundOrder(outboundOrderVo);
                    } catch (BusinessException e) {
                        //todo 下单失败, 记录下单失败数据
                        log.info("【批量下单失败】, 商品ID: {}, 原因: {}", orderParams.get(0), e.getMessage());

                    }
                }
                return ResultObj.ORDER_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                return ResultObj.ORDER_FAIL;
            }
        } catch (Exception e) {
            return ResultObj.ORDER_FAIL;
        }
    }

    @RequestMapping("/export/ctOrder/excelTemplate")
    public void exportCtOrderExcelTemplate(String ctId, HttpServletResponse response){
        try {
            log.info("【商户订单导出】商户号: {}, 时间:{}", ctId, DateTime.now());
            if (StringUtils.isBlank(ctId)) {
                return;
            }
            //1.查询商户当天订单数据

            //2.封装excel


        } catch (Exception e) {

        }
    }
}
