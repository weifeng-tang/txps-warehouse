package com.txps.bus.controller;

import com.txps.sys.common.DataGridView;
import com.txps.sys.common.ResultObj;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: Wayne
 * @date: 2021/8/25
 */
@RestController
public class ExcelController {


    @RequestMapping(value = "/batchOrder",method = RequestMethod.POST)
    @ResponseBody
    public ResultObj batchOrder(MultipartFile file, String ctId, String orderType){
        try {

            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_SUCCESS;
        }
    }
}
