/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.sb.model.Taker;
import cc.altius.sb.service.RegisterService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

/**
 *
 * @author altius
 */
@Controller
public class RegisterController {

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    private RegisterService registerService;
    //check taker emailid exist or not in database
    @RequestMapping(value = "takers/checkEmailId.htm", method = RequestMethod.GET)
    public @ResponseBody
    String getEmployeeData(@RequestParam String emailId) {
        boolean flag = registerService.existTakerByEmailId(emailId);
        Gson gson = new Gson();
        String json = gson.toJson(flag);
        return json.toString();
    }
    //taker register 
    @RequestMapping(value = "/takers/register.htm", method = RequestMethod.GET)
    public String showRegister(ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String lastNameId = ServletRequestUtils.getStringParameter(request, "lastNameId", "");
        Taker taker = new Taker();
        model.addAttribute("taker", taker);
        model.addAttribute("lastNameId", lastNameId);
        session.setAttribute("lastNameId", lastNameId);
        return "/takers/register";
    }

    @RequestMapping(value = "/takers/register.htm", method = RequestMethod.POST)
    public String addRegister(@ModelAttribute("taker") Taker taker, Errors errors, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String lastNameId = ServletRequestUtils.getStringParameter(request, "lastNameId", "");
        int takerId = this.registerService.addTaker(taker, lastNameId);
        model.addAttribute("taker", taker);
        session.setAttribute("lastName", taker.getLastName());
        session.setAttribute("emailId", taker.getEmail());
        registerService.addContactsInInfusionsoft(taker);
        return "redirect:/testSession/take.htm";
    }
   //taker login

    @RequestMapping(value = "/takers/resume.htm", method = RequestMethod.GET)
    public String showLogin(@ModelAttribute("taker") Taker taker, Errors errors, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("taker", taker);
        return "/takers/resume";
    }

    @RequestMapping(value = "/takers/resume.htm", method = RequestMethod.POST)
    public String variabyLogin(@ModelAttribute("taker") Taker taker, Errors errors, ModelMap model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        model.addAttribute("taker", taker);
        if (this.registerService.existTakerByTakerDetails(taker.getLastName(), taker.getEmail())) {
            session.setAttribute("lastName", taker.getLastName());
            session.setAttribute("emailId", taker.getEmail());
            return "redirect:/testSession/take.htm";

        } else {
            errors.rejectValue("lastName", "msg.taker");
            return "redirect:/takers/resume.htm?msg=msg.takerNotExist";
        }
    }

    //codeoflife project using this url check taker email id is exist or not in databases.if exist show the next question for taker.  
    @RequestMapping(value = "/takers/securitypassurl.htm", method = RequestMethod.GET)
    public String postTakerData(@RequestHeader(value = "email", required = false) String email, @RequestHeader(value = "lastName", required = false) String lastName, HttpSession session) {
        if (this.registerService.existTakerByTakerDetails(lastName, email)) {
            session.setAttribute("lastName", lastName);
            session.setAttribute("emailId", email);
            return "redirect:/testSession/take.htm";
        } else {
            return "redirect:/takers/resume.htm?msg=msg.takerNotExist";
        }
    }
}
