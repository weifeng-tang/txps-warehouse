package com.txps.sys.cache;

import com.txps.bus.entity.Customer;
import com.txps.bus.entity.Goods2;
import com.txps.bus.entity.Provider;
import com.txps.bus.mapper.CustomerMapper;
import com.txps.bus.mapper.GoodsMapper2;
import com.txps.bus.mapper.ProviderMapper;
import com.txps.sys.common.SpringUtil;
import com.txps.sys.entity.Dept;
import com.txps.sys.entity.User;
import com.txps.sys.mapper.DeptMapper;
import com.txps.sys.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 落亦-
 * @Date: 2019/12/20 18:05
 */
public class CachePool {

    /**
     * 所有的缓存数据放到这个CACHE_CONTAINER  类似于redis
     */
    public static volatile Map<String,Object> CACHE_CONTAINER = new HashMap<>();

    /**
     * 根据KEY删除缓存
     * @param key   键
     */
    public static void removeCacheByKey(String key){
        if (CACHE_CONTAINER.containsKey(key)){
            CACHE_CONTAINER.remove(key);
        }
    }

    /**
     * 清空所有缓存
     */
    public static void removeAll(){
        CACHE_CONTAINER.clear();
    }

    /**
     * 同步缓存
     */
    public static void syncData(){
        //同步部门数据
        DeptMapper deptMapper = SpringUtil.getBean(DeptMapper.class);
        List<Dept> deptList = deptMapper.selectList(null);
        for (Dept dept : deptList) {
            CACHE_CONTAINER.put("dept:"+dept.getId(),dept);
        }
        //同步用户数据
        UserMapper userMapper = SpringUtil.getBean(UserMapper.class);
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            CACHE_CONTAINER.put("user:"+user.getId(),user);
        }

        //同步客户数据
        CustomerMapper customerMapper = SpringUtil.getBean(CustomerMapper.class);
        List<Customer> customerList = customerMapper.selectList(null);
        for (Customer customer : customerList) {
            CACHE_CONTAINER.put("customer:"+customer.getId(),customer);
        }

        //同步供应商数据
        ProviderMapper providerMapper = SpringUtil.getBean(ProviderMapper.class);
        List<Provider> providerList = providerMapper.selectList(null);
        for (Provider provider : providerList) {
            CACHE_CONTAINER.put("provider:"+provider.getId(),provider);
        }

        //同步商品数据
        GoodsMapper2 goodsMapper = SpringUtil.getBean(GoodsMapper2.class);
        List<Goods2> goodsList = goodsMapper.selectList(null);
        for (Goods2 goods : goodsList) {
            CACHE_CONTAINER.put("goods:"+goods.getId(),goods);
        }

    }


}
