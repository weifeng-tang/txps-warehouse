package com.txps.bus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.txps.bus.entity.CommercialTenant;
import com.txps.bus.entity.CommercialTenantGoods;
import com.txps.bus.service.IExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");

        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);

        String path = System.getProperty("user.dir").replace("\\", "/") + "/src/main/resources/download/";

        ExcelWriter writer = ExcelUtil.getWriter(path + "writeBeanTest.xlsx");
        //跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();

        //合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() - 1, "测试标题");
        //一次性写出内容，强制输出标题
        writer.write(rows, true);
        //关闭writer，释放内存
        writer.close();
        return true;
    }
}
