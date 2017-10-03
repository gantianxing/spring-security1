package com.sky.security.web;

import com.sky.security.service.TestService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gantianxing on 2017/9/26.
 */

@Controller
@RequestMapping("/")
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/user")
    public String user(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            testService.userPermit();
            testService.adminPermit();
        }catch (Exception e){
            e.printStackTrace();
        }

        model.addAttribute("name",userDetails.getUsername());
        return "user";
    }

    @RequestMapping("/admin")
    public String admin(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        model.addAttribute("name",userDetails.getUsername());
        return "admin";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model){

        if (error != null) {
            model.addAttribute("error", "用户名和密码错误");
        }

        if (logout != null) {
            model.addAttribute("error", "你已经成功退出");
        }

        return "/login";
    }

    @RequestMapping("/welcome")
    public String welcome(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        model.addAttribute("name",userDetails.getUsername());
        return "welcome";
    }


    @RequestMapping("/nopermit")
    public String nopermit(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        model.addAttribute("name",userDetails.getUsername());
        return "nopermit";
    }
}
