package com.sky.security.service.impl;

import com.sky.security.service.vo.ShopData;
import org.springframework.stereotype.Component;

/**
 * Created by gantianxing on 2017/10/3.
 */
@Component
public class ShopDataHelper {

    /**
     * 根据id查询不做限制
     * @param id
     * @return
     */
    public ShopData selectById(Integer id) {
        ShopData ret = null;
        for(ShopData temp:ShopDataServiceImpl.shops){
            if(temp.getId()==id){
                ret = temp;
                break;
            }
        }
        return ret;
    }
}
