package com.sky.security.service.impl;

import com.sky.security.service.TestService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

/**
 * Created by gantianxing on 2017/10/1.
 */

@Component
public class TestServiceImpl implements TestService{

    @Override
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public void userPermit() {
        System.out.println("允许普通角色和管理员角色访问");
    }

    @Override
    @Secured("hasRole('ROLE_USER')")
    public void adminPermit() {
        System.out.println("允许管理员角色访问");
    }
}
