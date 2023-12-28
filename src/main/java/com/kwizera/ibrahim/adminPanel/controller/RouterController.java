package com.kwizera.ibrahim.adminPanel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RouterController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/permission/index")
    public String permission(){
        return "/pages/permission/index";
    }

    @GetMapping("/role/index")
    public String role(){
        return "/pages/role/index";
    }

    @GetMapping("/user/index")
    public String user(){
        return "/pages/user/index";
    }
}

