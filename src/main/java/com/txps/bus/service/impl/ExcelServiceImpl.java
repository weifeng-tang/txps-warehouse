package com.txps.bus.service.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.txps.bus.entity.CommercialTenant;
import com.txps.bus.entity.CommercialTenantGoods;
import com.txps.bus.service.IExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Wayne
 * @date: 2021/6/20
 */
@Service
@Slf4j
public class ExcelServiceImpl implements IExcelService {

    @Override
    public Boolean generateCtOrderExcel(List<CommercialTenantGoods> ctGoods, CommercialTenant ct) {
        List<Map> rows = new ArrayList<>();
        ctGoods.stream().forEach(ctGood -> {
            Map<String, String> row = new HashMap<>();
            row.put("id", ctGood.getId().toString());
            row.put("goodsName", ctGood.getGoodsName());
            row.put("specification", ctGood.getSpecification());
            row.put("unitPrice", ctGood.getUnitPrice().toPlainString());
            row.put("number", null);
            rows.add(row);
        });

        String path = System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/static/resources/download/";
        File file = new File(path + ct.getName() + ".xlsx");
        if (file.exists()) {
            file.delete();
        }
        ExcelWriter writer = ExcelUtil.getWriter(path + ct.getName() + ".xlsx");
        //跳过当前行，既第一行，非必须，在此演示用
        //writer.passCurrentRow();

        writer.merge(4, ct.getName());
        //合并单元格后的标题行，使用默认标题样式
        writer.addHeaderAlias("id", "主键");
        writer.addHeaderAlias("goodsName", "名称");
        writer.addHeaderAlias("specification", "规格");
        writer.addHeaderAlias("unitPrice", "单价");
        writer.addHeaderAlias("number", "下单数");
        //目的隐藏主键
        writer.setColumnWidth(0, 0);

        //一次性写出内容，强制输出标题
        writer.write(rows, true);
        //关闭writer，释放内存
        writer.close();
        return true;
    }

    public static void main(String[] args) {
        new ExcelServiceImpl().generateCtOrderExcel(null, null);
    }
}
