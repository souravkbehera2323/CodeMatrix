/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.sb.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author akil
 */
@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/home/login.htm")
    public String showLogin(Model map) {
        logger.info("Inside the LoginController" + LogUtils.getArgsString(), LogUtils.getIpAddress(), LogUtils.getUsername());

        return "home/login";
    }
}
