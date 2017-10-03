package com.sky.security.web;

import com.sky.security.service.ShopDataService;
import com.sky.security.service.vo.ShopData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gantianxing on 2017/10/3.
 */

@Controller
@RequestMapping("/shop")
public class ShopController {

    private static final GrantedAuthority ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

    @Resource
    private ShopDataService shopDataService;

    @RequestMapping("/select")
    public String select(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        List<ShopData> shopList = null;
        if(userDetails.getAuthorities().contains(ADMIN)){//超级管理员查看全部数据
            shopList = shopDataService.selectAll();
        }else{
            shopList=shopDataService.select(userDetails.getUsername());
        }
        model.addAttribute("shopList",shopList);
        return "/shop/list";
    }

    @RequestMapping("/add")
    public String add(Model model){
        shopDataService.add();
        model.addAttribute("info","店铺添加成功");
        return "/shop/info";
    }

    @RequestMapping("/update")
    public String update(Model model,Integer id){
        try {
            shopDataService.update(id);
            model.addAttribute("info","店铺修改成功");
        }catch (Exception e){
            model.addAttribute("info","店铺修改失败，没有修改该店铺权限");
        }
        return "/shop/info";
    }

    @RequestMapping("/delete")
    public String delete(Model model,Integer id){
        try {
            shopDataService.delete(id);
            model.addAttribute("info","店铺删除成功");
        }catch (Exception e){
            model.addAttribute("info","店铺删除失败，没有删除该店铺权限");
        }
        return "/shop/info";
    }
}
