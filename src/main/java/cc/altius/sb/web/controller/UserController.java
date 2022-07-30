/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.maven.AllAltiusUtil;
import cc.altius.sb.exception.DataStoreException;
import cc.altius.sb.framework.ApplicationSession;
import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.model.Password;
import cc.altius.sb.model.Role;
import cc.altius.sb.model.User;
import cc.altius.sb.service.UserService;
//import cc.altius.utils.DateUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Akil Mahimwala
 */
 //showing User crud
@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
 
    @RequestMapping(value = "/admin/userAdd.htm", method = RequestMethod.GET)
    public String showAddUserForm(ModelMap model) {
        ApplicationSession applicationSession = ApplicationSession.getCurrent();
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("roleList", applicationSession.getCanCreateRoleList(curUser.getRoles()));
        return "admin/userAdd";
    }

    @RequestMapping(value = "/admin/userAdd.htm", method = RequestMethod.POST)
    public String onAddUserSubmit(@ModelAttribute("user") User user, Errors errors, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            user = null;
            return "redirect:/admin/userList.htm?msg=" + URLEncoder.encode("Action cancelled", "UTF-8");
        } else {
             String[] roles = ServletRequestUtils.getStringParameters(request, "roles");
            List<Role> roleList = new LinkedList<>();
            for (String r : roles) {
                Role rObj = new Role();
                rObj.setRoleId(r);
                roleList.add(rObj);
            }
           user.setRoles(roleList);
            ApplicationSession applicationSession = ApplicationSession.getCurrent();
            if (this.userService.existUserByUsername(user.getUsername(),user.getEmailId())) {
                errors.rejectValue("username", "username.alreadyExists", "Username already exists");
                model.addAttribute("roleList", applicationSession.getCanCreateRoleList(curUser.getRoles()));
                return "admin/userAdd";
            }
            int userId = 0;
            try {
                userId = this.userService.addUser(user);
            } catch (DataStoreException d) {
            }
            if (userId == 0) {
                errors.rejectValue("username", "username.failed", "Failed to add User");
                model.addAttribute("roleList", applicationSession.getCanCreateRoleList(curUser.getRoles()));
                return "admin/userAdd";
            } else {
                return "redirect:/admin/userList.htm?msg=" + URLEncoder.encode("User addedd successfully", "UTF-8");
            }
        }
    }

    @RequestMapping(value = "/admin/showUserEdit.htm")
    public String showEditUserForm(@RequestParam(value = "userId", required = true) int userId, ModelMap model, HttpServletResponse response) throws UnsupportedEncodingException {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ApplicationSession applicationSession = ApplicationSession.getCurrent();
        User user = this.userService.getUserByUserId(userId);
        if (!this.userService.canCreateRoles(curUser.getRoles(), user.getRoles())) {
            return "redirect:/admin/userList.htm?msg=" + URLEncoder.encode("You do not have priviliges to edit this Role", "UTF-8");
        } else {
            model.addAttribute("user", user);
            model.addAttribute("roleList", applicationSession.getCanCreateRoleList(curUser.getRoles()));
            return "admin/userEdit";
        }
    }

    @RequestMapping(value = "/admin/userEdit.htm", method = RequestMethod.POST)
    public String onEditUserSubmit(@ModelAttribute("user") User user, Errors errors, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, ServletRequestBindingException {
        String[] roles = ServletRequestUtils.getStringParameters(request, "roles");
        String password = ServletRequestUtils.getStringParameter(request, "password");
        List<Role> roleList = new LinkedList<>();
        for (String r : roles) {
            Role rObj = new Role();
            rObj.setRoleId(r);
            roleList.add(rObj);
        }
        user.setRoles(roleList);
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ApplicationSession applicationSession = ApplicationSession.getCurrent();
        if (!this.userService.canCreateRoles(curUser.getRoles(), user.getRoles())) {
            return "redirect:/admin/userList.htm?msg=" + URLEncoder.encode("You do not have priviliges to edit this Role", "UTF-8");
        } else {
            // Passed the can create check so go ahead 
            User newUser = this.userService.getUserByUsername(user.getUsername(),user.getEmailId());
            if (newUser != null && newUser.getUserId() != user.getUserId()) {
                errors.reject("username", "Username already exists");
                model.addAttribute("roleList", applicationSession.getCanCreateRoleList(curUser.getRoles()));
                return "admin/userEdit";
            }
            try {
                user.setPassword(password);
                this.userService.updateUser(user);
                return "redirect:userList.htm?msg=" + URLEncoder.encode("User Edit successfully", "UTF-8");
            } catch (Exception e) {
                logger.error("Error occurred", e);
                errors.reject("username", "Failed to add User");
                model.addAttribute("roleList", applicationSession.getCanCreateRoleList(curUser.getRoles()));
                return "admin/userEdit";
            }
        }
    }

    @RequestMapping(value = "/admin/userEdit.htm", method = RequestMethod.POST, params = "_cancel")
    public String onEditUserCancel(ModelMap model, HttpServletResponse response) throws UnsupportedEncodingException {
        return "redirect:/admin/userList.htm?msg=" + URLEncoder.encode("Action cancelled", "UTF-8");
    }

    @RequestMapping(value = "/admin/userList.htm", method = RequestMethod.GET)
    public String showUserListPage(ModelMap model) {
        model.addAttribute("userList", this.userService.getUserList());
        return "admin/userList";
    }
   //when user enter wrong password  3 time account is lock using this method reset lock for user
    @RequestMapping(value = "/admin/failedAttemptsReset.htm", method = RequestMethod.POST)
    @ResponseBody
    public String userFailedAttemptsReset(@RequestParam(value = "userId", required = true) int userId) {
        this.userService.resetFailedAttempts(userId);
        return "User account unlocked";
    }
  
    @RequestMapping(value = "/home/changePassword.htm", method = RequestMethod.GET)
    public String showChangePasswordPage(ModelMap model) {
        Password password = new Password();
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        password.setUserId(curUser.getUserId());
        password.setUsername(curUser.getUsername());
        model.addAttribute("password", password);
        return "home/changePassword";
    }

    @RequestMapping(value = "/home/changePassword.htm", method = RequestMethod.POST)
    public String onChangePasswordSubmit(@ModelAttribute("password") Password password, Errors errors, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            password = null;
            logger.info("Reached the Post for ChangePassword with Cancel going to redirect now");
            return "redirect:/home/index.htm?msg=" + URLEncoder.encode("Action cancelled", "UTF-8");
        } else {
            if (!this.userService.confirmPassword(password)) {
                errors.rejectValue("oldPassword", "msg.oldPasswordNotMatch", "The Current password entered was incorrect");
                return "home/changePassword";
            } else {
                this.userService.updatePassword(password, 90);
                return "redirect:/home/index.htm?msg=" + URLEncoder.encode("Password updated successfully", "UTF-8");
            }
        }
    }

    @RequestMapping(value = "/home/updateExpiredPassword.htm", method = RequestMethod.GET)
    public String showUpdatePasswordExpiredForm(ModelMap model) {
        Password password = new Password();
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        password.setUserId(curUser.getUserId());
        password.setUsername(curUser.getUsername());
        model.addAttribute("password", password);
        return "home/updateExpiredPassword";
    }

    @RequestMapping(value = "/home/updateExpiredPassword.htm", method = RequestMethod.POST)
    public String updatePasswordExpiredOnSubmit(final @ModelAttribute("password") Password password, Errors errors, HttpServletResponse response) throws UnsupportedEncodingException {
        if (!this.userService.confirmPassword(password)) {
            errors.rejectValue("oldPassword", "msg.oldPasswordNotMatch", "The Current password entered was incorrect");
            CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            password.setUserId(curUser.getUserId());
            password.setUsername(curUser.getUsername());
            return "home/updateExpiredPassword";
        } else {
            this.userService.updatePassword(password, 90);
            // all you need to do now is load the correct Authorities
            Authentication curAuthentication = SecurityContextHolder.getContext().getAuthentication();
            CustomUserDetails curUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            curUser.setExpiresOn(AllAltiusUtil.getOffsetFromCurrentDateObject(AllAltiusUtil.EST, 90));
            curUser.setRoles(this.userService.getRolesForUserId(curUser.getUserId()));
            curUser.setBusinessFunction(this.userService.getBusinessFunctionsForUserId(curUser.getUserId()));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(curUser, curAuthentication.getCredentials(), curUser.getAuthorities());
            auth.setDetails(curAuthentication.getDetails());
            SecurityContextHolder.getContext().setAuthentication(auth);
            return "redirect:/home/index.htm?msg=" + URLEncoder.encode("Password updated successfully", "UTF-8");
        }
    }

    @RequestMapping(value = "/admin/resetPassword.htm", method = RequestMethod.POST)
    public @ResponseBody
    String userResetPassword(@RequestParam(value = "userId", required = true) int userId) throws DataStoreException {
        try {
            String pass = this.userService.resetPassword(userId);
            if (pass.equalsIgnoreCase("Could not reset password")) {
                return pass;
            } else {
                return "Password for user reset to \"" + pass + "\"";
            }
        } catch (Exception e) {
            throw new DataStoreException("Could not reset user password");
        }
    }
}
