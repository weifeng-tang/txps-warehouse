package com.txps.bus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.txps.bus.entity.GoodsSpecConvert;
import com.txps.bus.service.IGoodsSpecConvertService;
import com.txps.bus.vo.GoodsSpecConvertVo;
import com.txps.sys.common.DataGridView;
import com.txps.sys.common.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wayne
 * @since 2021-05-03
 */
@RestController
@RequestMapping("/goodsSpecConvert")
public class GoodsSpecConvertController {

    @Autowired
    private IGoodsSpecConvertService goodsSpecConvertService;

    /**
     * 查询商品规格转换列表
     * @param gscv
     * @return
     */
    @RequestMapping("loadAllGoods")
    public DataGridView loadAllGoods(GoodsSpecConvertVo gscv){
        IPage<GoodsSpecConvert> page = goodsSpecConvertService.queryPageByCondition(gscv);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加商品规格转换率
     * @param gscv
     * @return
     */
    @RequestMapping("addGoodsSpecConvert")
    public ResultObj addGoodsSpecConvert(GoodsSpecConvertVo gscv){
        try {
            goodsSpecConvertService.save(gscv);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改一个商户商品
     * @param gscv
     * @return
     */
    @RequestMapping("updateGoodsSpecConvert")
    public ResultObj updateCustomer(GoodsSpecConvertVo gscv){
        try {
            goodsSpecConvertService.updateById(gscv);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
}

