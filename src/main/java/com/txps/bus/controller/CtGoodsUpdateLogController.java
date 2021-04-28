package com.txps.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.txps.bus.entity.CtGoodsUpdateLog;
import com.txps.bus.service.ICtGoodsUpdateLogService;
import com.txps.sys.common.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wayne
 * @since 2021-04-28
 */
@RestController
@RequestMapping("/ctGoodsUpdateLog")
public class CtGoodsUpdateLogController {

    @Autowired
    private ICtGoodsUpdateLogService ctGoodsUpdateLogService;

    /**
     * 查询商户商品
     * @param ctGoodsId
     * @return
     */
    @RequestMapping("queryUpdateLog")
    public DataGridView queryUpdateLog(Integer ctGoodsId){
        QueryWrapper<CtGoodsUpdateLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!ObjectUtils.isEmpty(ctGoodsId), "ct_goods_id", ctGoodsId);
        return new DataGridView(ctGoodsUpdateLogService.list(queryWrapper));
    }

}

