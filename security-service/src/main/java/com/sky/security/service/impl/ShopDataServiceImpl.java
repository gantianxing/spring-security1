package com.sky.security.service.impl;

import com.sky.security.service.ShopDataService;
import com.sky.security.service.vo.ShopData;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gantianxing on 2017/10/3.
 */
@Component
public class ShopDataServiceImpl implements ShopDataService {

    private static AtomicInteger shopId = new AtomicInteger(3);

    public static List<ShopData> shops = new ArrayList<>();

    //初始化数据，创建人分别为A、B、C
    {
        ShopData s1 = new ShopData();
        s1.setId(1);
        s1.setShopName("店铺1");
        s1.setShopUrl("www.shop1.com");
        s1.setState(true);
        s1.setUsername("A");

        ShopData s2 = new ShopData();
        s2.setId(2);
        s2.setShopName("店铺1");
        s2.setShopUrl("www.shop2.com");
        s2.setState(true);
        s2.setUsername("B");

        ShopData s3 = new ShopData();
        s3.setId(3);
        s3.setShopName("店铺3");
        s3.setShopUrl("www.shop3.com");
        s3.setState(true);
        s3.setUsername("C");

        shops.add(s1);
        shops.add(s2);
        shops.add(s3);
    }

    /**
     * 所有用户都新增店铺权限
     */
    @Override
    public void add() {
        Integer id = shopId.incrementAndGet();
        ShopData shopData = new ShopData();
        shopData.setId(id);
        shopData.setShopName("店铺" + id);
        shopData.setShopUrl("www.shop" + id + ".com");
        shopData.setState(true);

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        shopData.setUsername(userDetails.getUsername());

        shops.add(shopData);
    }

    /**
     * 普通管理员只能删除自己的店铺
     * 超级管理员没有此限制
     * @param id
     */
    @Override
    @PreAuthorize("hasPermission(#id,'delete')")
    public void delete(Integer id) {
        for(ShopData temp:shops){
            if(temp.getId()==id){
                shops.remove(temp);
                break;
            }
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PreFilter("hasRole('ROLE_ADMIN') || filterObject.shopdata.username == principal.username")
    public void deletexx(List<ShopData> list) {
        //省略代码
    }

    /**
     * 修改逻辑，如果是上线状态，修改为下线，反之亦然
     * 普通管理员只能修改自己的店铺
     * @param id
     */
    @Override
    @PreAuthorize("hasPermission(#id,'update')")
    public void update(Integer id) {
        for(ShopData temp:shops){
            if(temp.getId()==id){
                if(temp.isState()){
                    temp.setState(false);
                }else{
                    temp.setState(true);
                }
                break;
            }
        }
    }

    /**
     * 普通管理员 只能获取自己的店铺列表
     * @param username
     * @return
     */
    @Override
    @PreAuthorize("principal.username.equals(#username)")
    public List<ShopData> select(String username) {
        List<ShopData> ret = new ArrayList<>();
        for(ShopData temp:shops){
            if(temp.getUsername().equals(username)){
                ret.add(temp);
            }
        }
        return ret;
    }




    /**
     * 超级管理员可以获取所有店铺列表
     * @return
     */
    @Override
    @Secured("ROLE_ADMIN")
    public List<ShopData> selectAll() {
        return shops;
    }
}
