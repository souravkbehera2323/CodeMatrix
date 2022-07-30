/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.sb.framework.ApplicationSession;
import cc.altius.sb.model.CustomUserDetails;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author akil
 */
@Controller
public class ReloadApplicationLayerController {

    @RequestMapping("/admin/reloadApplicationLayer.htm")
    public String reloadApplicationLayer(ModelMap modelMap,HttpSession session) {
        String path = (String)session.getAttribute("path");
        ApplicationSession as = ApplicationSession.getCurrent();
        as.reloadAll();
        modelMap.addAttribute("msg", "Application layer reloaded");
        CustomUserDetails curUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelMap.addAttribute("roleList", as.getRoleList());
        modelMap.addAttribute("bfList", as.getBusinessFunctionList());
        modelMap.addAttribute("imagePath", path);
        return "home/reloadApplicationLayer";
    }
}
