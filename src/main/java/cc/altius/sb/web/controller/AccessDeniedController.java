/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.sb.model.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Akil Mahimwala
 */
@Controller
public class AccessDeniedController {

    private final Logger logger = LoggerFactory.getLogger(AccessDeniedController.class);
    
    @RequestMapping("/errors/accessDenied.htm")
    public String showPage() {
        logger.debug("Reached the AccessDeniedController");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            if (auth.isAuthenticated()) {

                CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                if (curUser.getBusinessFunction().contains(new SimpleGrantedAuthority("ROLE_BF_PASSWORD_EXPIRED"))) {
                    return "redirect:/home/updateExpiredPassword.htm";
                } else {
                    return "errors/accessDenied";
                }
            }
        }
        return "redirect:/home/login.htm";
    }
}
