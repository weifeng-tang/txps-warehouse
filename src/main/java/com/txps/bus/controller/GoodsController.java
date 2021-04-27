package com.txps.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.txps.bus.entity.Goods;
import com.txps.bus.entity.Goods2;
import com.txps.bus.entity.Provider;
import com.txps.bus.service.IGoodsService;
import com.txps.bus.service.IProviderService;
import com.txps.bus.vo.GoodsVo;
import com.txps.sys.common.Constast;
import com.txps.sys.common.DataGridView;
import com.txps.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * InnoDB free: 9216 kB; (`providerid`) REFER `warehouse/bus_provider`(`id`) 前端控制器
 * </p>
 *
 * @author luoyi-
 * @since 2019-12-06
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IProviderService providerService;

    /**
     * 查询商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("loadAllGoods")
    public DataGridView loadAllGoods(GoodsVo goodsVo){
        IPage<Goods> page = new Page<>(goodsVo.getPage(),goodsVo.getLimit());
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(goodsVo.getGoodsName()), "goods_name", goodsVo.getGoodsName());
        queryWrapper.eq(goodsVo.getType() != null, "type", goodsVo.getType());
        queryWrapper.orderByDesc("id");
        goodsService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("addGoods")
    public ResultObj addGoods(GoodsVo goodsVo){
        try {
            goodsService.save(goodsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("updateGoods")
    public ResultObj updateGoods(GoodsVo goodsVo){
        try {
            goodsService.updateById(goodsVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除商品
     * @param id 商品id
     * @return
     */
    @RequestMapping("deleteGoods")
    public ResultObj deleteGoods(Integer id,String goodsimg){
        try {
            /*//删除商品的图片
            AppFileUtils.removeFileByPath(goodsimg);
//            goodsService.removeById(id);
            goodsService.deleteGoodsById(id);*/
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据商品类型加载所有可用的商品
     * @return
     */
    @RequestMapping("loadAllGoodsForSelect")
    public DataGridView loadAllGoodsForSelect(Integer goodsType){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(goodsType != null, "type", goodsType);
        return new DataGridView(goodsService.list(queryWrapper));
    }

    /**
     * 根据供应商ID查询商品信息
     * @param providerid    供应商ID
     * @return
     */
    @RequestMapping("loadGoodsByProviderId")
    public DataGridView loadGoodsByProviderId(Integer providerid){
        QueryWrapper<Goods2> queryWrapper = new QueryWrapper<Goods2>();
        queryWrapper.eq("available",Constast.AVAILABLE_TRUE);
        queryWrapper.eq(providerid!=null,"providerid",providerid);
        List<Goods2> list = null;/*goodsService.list(queryWrapper);*/
        for (Goods2 goods : list) {
            Provider provider = providerService.getById(goods.getProviderid());
            if (null!=provider){
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(list);
    }

    @RequestMapping("loadAllWarningGoods")
    public DataGridView loadAllWarningGoods(){
        List<Goods2> goods = null;/*goodsService.loadAllWarning();*/
        return new DataGridView((long) goods.size(),goods);
    }



}

