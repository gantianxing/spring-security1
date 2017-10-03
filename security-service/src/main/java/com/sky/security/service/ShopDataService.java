package com.sky.security.service;

import com.sky.security.service.vo.ShopData;

import java.util.List;

/**
 * Created by gantianxing on 2017/10/3.
 */
public interface ShopDataService {

    void add();

    void delete(Integer id);

    void update(Integer id);

    List<ShopData> select (String username);

    List<ShopData> selectAll();
}
