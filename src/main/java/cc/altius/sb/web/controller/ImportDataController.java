/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.sb.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author akil
 */
//import php database to java databases 
@Controller
public class ImportDataController {

    @Autowired
    private ImportService importService;
    
    @RequestMapping(value = "/home/importData.htm", method = RequestMethod.GET)
    @ResponseBody
    public String importData() {
        this.importService.doImport();
        return "";
    }

}
