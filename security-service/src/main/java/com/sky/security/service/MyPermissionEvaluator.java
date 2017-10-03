package com.sky.security.service;

import com.sky.security.service.impl.ShopDataHelper;
import com.sky.security.service.vo.ShopData;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by gantianxing on 2017/10/3.
 */
@Component("myPermissionEvaluator")
public class MyPermissionEvaluator implements PermissionEvaluator {

    private static final GrantedAuthority ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");

    @Resource
    private ShopDataHelper shopDataHelper;

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        if(o instanceof Integer){
            //管理员拥有所有权限
            if(isAdmin(authentication)){
                return true;
            }
            Integer id = (Integer) o;
            ShopData shopData = shopDataHelper.selectById(id);
            String username = shopData.getUsername();

            //判断普通管理员是否有删除、修改权限
            if("delete".equals(o1) || "update".equals(o1)){
                if(authentication.getName().equals(username)){
                    return true;
                }else{
                    return false;
                }
            }
        }

        throw new UnsupportedOperationException("hasPermission 第一个参数必须是Integer型,第二个参数必须为delete或者update");
    }

    /**
     * 暂不实现
     */
    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        throw new UnsupportedOperationException();
    }

    private boolean isAdmin(Authentication authentication){
        return authentication.getAuthorities().contains(ADMIN);
    }
}
