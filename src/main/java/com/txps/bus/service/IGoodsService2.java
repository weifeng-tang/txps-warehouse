package com.txps.bus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.txps.bus.entity.Goods2;

import java.util.List;

/**
 * <p>
 * InnoDB free: 9216 kB; (`providerid`) REFER `warehouse/bus_provider`(`id`) 服务类
 * </p>
 *
 * @author luoyi-
 * @since 2019-12-06
 */
public interface IGoodsService2 extends IService<Goods2> {

    /**
     * 根据商品id删除商品
     * @param id
     */
    void deleteGoodsById(Integer id);

    /**
     * 加载所有的库存预警商品
     * @return
     */
    List<Goods2> loadAllWarning();
}
