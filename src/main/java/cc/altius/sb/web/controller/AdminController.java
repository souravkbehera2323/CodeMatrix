/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.sb.framework.ApplicationSession;
import cc.altius.sb.model.BusinessFunction;
import cc.altius.sb.model.CanCreateRole;
import cc.altius.sb.model.CustomUserDetails;
import cc.altius.sb.model.Role;
import cc.altius.sb.service.AdminService;
import cc.altius.sb.service.UserService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author akil
 */
@Controller
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin/roleAdd.htm", method = RequestMethod.GET)
    public String showAddRoleForm(ModelMap model) {
        ApplicationSession as = ApplicationSession.getCurrent();
        Role role = new Role();
        model.addAttribute("role", role);
        model.addAttribute("availableBusinessFunctionList", as.getBusinessFunctionList());
        return "admin/roleAdd";
    }

    @RequestMapping(value = "/admin/roleAdd.htm", method = RequestMethod.POST)
    public String onAddRoleSubmit(@ModelAttribute("role") Role role, Errors errors, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            role = null;
            return "redirect:/admin/roleList.htm?msg=" + URLEncoder.encode("Action cancelled", "UTF-8");
        } else {
            String[] businessFunctionArray = ServletRequestUtils.getStringParameters(request, "businessFunctionList");
            role.setBusinessFunctions(new LinkedList<>());
            for (String bf : businessFunctionArray) {
                BusinessFunction b = new BusinessFunction();
                b.setBusinessFunctionId(bf);
                role.getBusinessFunctions().add(b);
            }
            ApplicationSession as = ApplicationSession.getCurrent();
            if (as.getRoleById(role.getRoleId()) != null) {
                errors.rejectValue("roleId", "role.alreadyExists", "Role already exists");
                model.addAttribute("availableBusinessFunctionList", as.getBusinessFunctionList());
                return "admin/roleAdd";
            }
            int cnt = this.adminService.addRole(role);
            if (cnt == 0) {
                errors.rejectValue("roleId", "role.failed", "Failed to add Role");
                model.addAttribute("availableBusinessFunctionList", as.getBusinessFunctionList());
                return "admin/roleAdd";
            } else {
                return "redirect:/admin/roleList.htm?msg=" + URLEncoder.encode("Role addedd successfully", "UTF-8");
            }
        }
    }

    @RequestMapping(value = "/admin/showRoleEdit.htm", method = RequestMethod.POST)
    public String showEditRoleForm(@RequestParam(value = "roleId", required = true) String roleId, ModelMap model, HttpServletResponse response) throws UnsupportedEncodingException {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ApplicationSession as = ApplicationSession.getCurrent();
        List<Role> roleList = new LinkedList<>();
        Role role = as.getRoleById(roleId);
        roleList.add(role);
        if (!this.userService.canCreateRoles(curUser.getRoles(), roleList)) {
            return "redirect:/admin/roleList.htm?msg=" + URLEncoder.encode("You do not have priviliges to edit this Role", "UTF-8");
        } else {
            model.addAttribute("role", role);
            model.addAttribute("availableBusinessFunctionList", as.getBusinessFunctionList());
            return "admin/roleEdit";
        }
    }

    @RequestMapping(value = "/admin/roleEdit.htm", method = RequestMethod.POST, params = "_cancel")
    public String onEditRoleCancel(ModelMap model, HttpServletResponse response) throws UnsupportedEncodingException {
        return "redirect:/admin/roleList.htm?msg=" + URLEncoder.encode("Action cancelled", "UTF-8");
    }

    @RequestMapping(value = "/admin/roleList.htm", method = RequestMethod.GET)
    public String showRoleListPage(ModelMap model) {
        ApplicationSession as = ApplicationSession.getCurrent();
        model.addAttribute("roleList", as.getRoleList());
        return "admin/roleList";
    }

    @RequestMapping(value = "/admin/roleEdit.htm", method = RequestMethod.POST)
    public String onEditRoleSubmit(@ModelAttribute("role") Role role, Errors errors, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        CustomUserDetails curUser = (CustomUserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ApplicationSession as = ApplicationSession.getCurrent();
        List<Role> roleList = new LinkedList<>();
        roleList.add(role);
        if (!this.userService.canCreateRoles(curUser.getRoles(), roleList)) {
            return "redirect:/admin/roleList.htm?msg=" + URLEncoder.encode("You do not have priviliges to edit this Role", "UTF-8");
        } else {
            // Passed the can create check so go ahead 
            try {
                String[] businessFunctionArray = ServletRequestUtils.getStringParameters(request, "businessFunctionList");
                role.setBusinessFunctions(new LinkedList<>());
                for (String bf : businessFunctionArray) {
                    BusinessFunction b = new BusinessFunction();
                    b.setBusinessFunctionId(bf);
                    role.getBusinessFunctions().add(b);
                }
                this.adminService.updateRole(role);
                return "redirect:roleList.htm?msg=" + URLEncoder.encode("Role updated successfully", "UTF-8");
            } catch (Exception e) {
                logger.error("Error occurred", e);
                errors.reject("roleId", "Failed to update Role");
                model.addAttribute("availableBusinessFunctionList", as.getBusinessFunctionList());
                return "admin/roleEdit";
            }
        }
    }

    @RequestMapping(value = "/admin/showCanCreateRoles.htm", method = RequestMethod.POST)
    public String showCanCreateRolesForm(@RequestParam(value = "roleId", required = true) String roleId, ModelMap model) {
        ApplicationSession applicationSession = ApplicationSession.getCurrent();
        model.addAttribute("allRolesList", applicationSession.getRoleList());
        CanCreateRole canCreateRole = new CanCreateRole();
        canCreateRole.setRole(applicationSession.getRoleById(roleId));
        canCreateRole.setCanCreateRoles(this.userService.getCreateRolesList(roleId));
        model.addAttribute("canCreateRole", canCreateRole);
        model.addAttribute("allRoleList", applicationSession.getRoleList());
        return "admin/canCreateRoles";
    }

    @RequestMapping(value = "/admin/canCreateRoles.htm", method = RequestMethod.POST)
    public String canCreateRolesOnSubmit(@ModelAttribute("canCreateRole") CanCreateRole canCreateRole, HttpServletRequest request, ModelMap model, HttpServletResponse response) throws UnsupportedEncodingException {
        ApplicationSession applicationSession = ApplicationSession.getCurrent();
        String cancel = ServletRequestUtils.getStringParameter(request, "_cancel", null);
        if (cancel != null) {
            return "redirect:/admin/roleList.htm?msg=" + URLEncoder.encode("Action cancelled", "UTF-8");
        } else {
            String[] canCreateRoles = ServletRequestUtils.getStringParameters(request, "canCreateRoleList");
            this.userService.updateCanCreateRoles(canCreateRole.getRole().getRoleId(), canCreateRoles);
            return "redirect:/admin/roleList.htm?msg=" + URLEncoder.encode("Role updated", "UTF-8");
        }
    }
}
