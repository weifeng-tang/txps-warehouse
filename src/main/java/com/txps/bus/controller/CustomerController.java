package com.txps.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.txps.bus.entity.CommercialTenant;
import com.txps.bus.service.ICommercialTenantService;
import com.txps.bus.service.ICustomerService;
import com.txps.bus.vo.CommercialTenantVo;
import com.txps.sys.common.Constast;
import com.txps.sys.common.DataGridView;
import com.txps.sys.common.ResultObj;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * InnoDB free: 9216 kB 前端控制器
 * </p>
 *
 * @author luoyi-
 * @since 2019-12-05
 */
@RestController
@RequestMapping("/customer")
public class   CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICommercialTenantService commercialTenantService;

    /**
     * 查询所有的客户
     * @param commercialTenantVo
     * @return
     */
    @RequestMapping("loadAllCustomer")
    public DataGridView loadAllCustomer(CommercialTenantVo commercialTenantVo){
        //1.声明一个分页page对象
        IPage<CommercialTenant> page = new Page<>(commercialTenantVo.getPage(),commercialTenantVo.getLimit());
        //2.声明一个queryWrapper
        QueryWrapper<CommercialTenant> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(commercialTenantVo.getName()),"name",commercialTenantVo.getName());
        queryWrapper.like(StringUtils.isNotBlank(commercialTenantVo.getConsigneeName()),"consignee_name",commercialTenantVo.getConsigneeName());
        queryWrapper.like(StringUtils.isNotBlank(commercialTenantVo.getConsigneePhone()),"consignee_phone",commercialTenantVo.getConsigneePhone());
        commercialTenantService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加一个客户
     * @param commercialTenantVo
     * @return
     */
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(CommercialTenantVo commercialTenantVo){
        try {
            commercialTenantService.save(commercialTenantVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改一个客户
     * @param commercialTenantVo
     * @return
     */
    @RequestMapping("updateCustomer")
    public ResultObj updateCustomer(CommercialTenantVo commercialTenantVo){
        try {
            commercialTenantService.updateById(commercialTenantVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除一个客户
     * @param id 客户的ID
     * @return
     */
    @ApiOperation(value = "删除一个客户",notes = "删除一个客户")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "客户ID",required = true,paramType = "query",dataType = "Integer")})
    @RequestMapping(value = "deleteCustomer",method = RequestMethod.DELETE)
    public ResultObj deleteCustomer(Long id){
        try {
            commercialTenantService.deleteCommercialTenantById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /**
     * 加载所有客户的下拉列表
     * @return
     */
    @RequestMapping("loadAllCustomerForSelect")
    public DataGridView loadAllCustomerForSelect(){
        QueryWrapper<CommercialTenant> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", Constast.AVAILABLE_TRUE);
        List<CommercialTenant> list = commercialTenantService.list(queryWrapper);
        return new DataGridView(list);
    }

}

