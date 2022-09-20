package com.shestnut.user.internal.user.controller;

import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @author luoyongzhi
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/ha")
    public String getStr(){
        return "hahaha";
    }


}
