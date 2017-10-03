package com.sky.security.service.vo;

/**
 * 店铺信息表
 * Created by gantianxing on 2017/10/3.
 */
public class ShopData {

    //店铺id
    private Integer id;

    //店铺名称
    private String shopName;

    //店铺链接
    private String shopUrl;

    //店铺状态 true 上线
    private boolean state;

    //店铺创建人
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
