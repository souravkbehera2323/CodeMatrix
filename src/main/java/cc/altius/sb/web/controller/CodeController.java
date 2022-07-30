/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.sb.model.ResponseFormat;
import cc.altius.sb.model.Taker;
import cc.altius.sb.service.RegisterService;
import cc.altius.sb.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yogesh
 */
@RestController

public class CodeController {

    @Autowired
    private RegisterService registerService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String apiToken = "Y@g4007e$h";

    @PostMapping(value = "/getTaker", produces = "application/json;charset=UTF-8")
    public ResponseEntity TakerSavelogin(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "mobileNumber") String mobileNumber,
            @RequestParam(value = "age") int age,
            @RequestParam(value = "gender") int gender,
            @RequestParam(value = "phpToken") String phpToken) {
        logger.info(LogUtils.buildStringForSystemLog("firstName " + firstName + "/lastName :" + lastName + "/email :" + email + "/mobileNumber :" + mobileNumber + "/age :" + age + "/gender :" + gender));
        logger.info(LogUtils.buildStringForSystemLog("firstName " + firstName + "/lastName :" + lastName + "/email :" + email + "/mobileNumber :" + mobileNumber + "/age :" + age + "/gender :" + gender));
        ResponseFormat responseFormat = new ResponseFormat();
        try {
            if (phpToken.equals(this.apiToken)) {
                Taker taker = new Taker();
                if (firstName != null && lastName != null && email != null && mobileNumber != null && age != 0 && gender != 0) {

                    taker.setFirstName(firstName);
                    taker.setLastName(lastName);
                    taker.setEmail(email);
                    taker.setPhone(mobileNumber);
                    taker.setGender(gender);
                    taker.setAge(age);
                } else {
                    responseFormat.setStatus("failed");
                    responseFormat.setFailedReason("Required fields not found");
                    responseFormat.setParameter("firstName " + firstName + "/lastName :" + lastName + "/email :" + email + "/mobileNumber :" + mobileNumber + "/age :" + age + "/gender :" + gender);
                    return new ResponseEntity(responseFormat, HttpStatus.UNAUTHORIZED);
                }
                
                int taker1 = this.registerService.addTaker(taker,"1");
                if (taker1 != 0) {
                    return new ResponseEntity(taker1, HttpStatus.OK);
                } else {
                    responseFormat.setStatus("failed");
                    responseFormat.setFailedReason("error occured while insertion");
                    responseFormat.setParameter("firstName " + firstName + "/lastName :" + lastName + "/email :" + email + "/mobileNumber :" + mobileNumber + "/age :" + age + "/gender :" + gender);
                    return new ResponseEntity(responseFormat, HttpStatus.UNAUTHORIZED);
                }

            } else {
                responseFormat.setStatus("failed");
                responseFormat.setFailedReason("INVALID_APP_TOKEN");
                responseFormat.setFailedValue(99);
                return new ResponseEntity(responseFormat, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseFormat.setStatus("failed");
            responseFormat.setFailedReason("Invalid information try");
            return new ResponseEntity(responseFormat, HttpStatus.UNAUTHORIZED);
        }
    }

}
