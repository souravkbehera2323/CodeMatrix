/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.sb.framework.ApplicationSession;
import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.utils.LogUtils;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author akil
 */
@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String redirect() {
        return "redirect:/home/index.htm";
    }

    @RequestMapping("")
    public String redirectRoot() {
        return redirect();
    }

    @RequestMapping(value = "/home/index.htm")
    public String showIndex(ModelMap map, HttpServletRequest request) throws Exception {
        CustomUserDetails curUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        map.addAttribute("bfList", curUser.getBusinessFunction());
        ApplicationSession as = ApplicationSession.getCurrent();
        map.addAttribute("roleList", as.getRoleList());
        logger.info("Inside the IndexController IP:{} User:{}", LogUtils.getIpAddress(), LogUtils.getUsername());
        return "redirect:/testResults/view.htm";
    }
}
