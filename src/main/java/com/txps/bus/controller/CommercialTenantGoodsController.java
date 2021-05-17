package com.txps.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.txps.bus.entity.CommercialTenantGoods;
import com.txps.bus.service.ICommercialTenantGoodsService;
import com.txps.bus.vo.CommercialTenantGoodsVo;
import com.txps.sys.common.DataGridView;
import com.txps.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wayne
 * @since 2021-04-24
 */
@RestController
@RequestMapping("/ctGoods")
public class CommercialTenantGoodsController {

    @Autowired
    private ICommercialTenantGoodsService commercialTenantGoodsService;

    /**
     * 查询商户商品
     * @param ctGoodsVo
     * @return
     */
    @RequestMapping("loadAllCtGoods")
    public DataGridView loadAllGoods(CommercialTenantGoodsVo ctGoodsVo){
        IPage<CommercialTenantGoods> page = commercialTenantGoodsService.queryPageByCondition(ctGoodsVo);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 查询商户商品
     * @param ctId 商户id
     * @param goodsType 商品类型
     * @return
     */
    @RequestMapping("loadAllGoodsForSelect")
    public DataGridView loadAllGoodsForSelect(Long ctId, Integer goodsType){
        List<CommercialTenantGoods> list = commercialTenantGoodsService.loadAllGoodsForSelect(ctId, goodsType);
        return new DataGridView(list);
    }

    /**
     * 添加商户商品
     * @param ctGoodsVo
     * @return
     */
    @RequestMapping("addCtGoods")
    public ResultObj addGoods(CommercialTenantGoodsVo ctGoodsVo){
        try {
            commercialTenantGoodsService.save(ctGoodsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改一个商户商品
     * @param ctGoodsVo
     * @return
     */
    @RequestMapping("updateCtGoods")
    public ResultObj updateCustomer(CommercialTenantGoodsVo ctGoodsVo){
        try {
            commercialTenantGoodsService.updateById(ctGoodsVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
}

