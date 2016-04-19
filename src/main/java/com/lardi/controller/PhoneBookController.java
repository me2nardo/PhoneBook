package com.lardi.controller;

import com.lardi.model.PhoneBook;
import com.lardi.service.PhoneBookService;
import com.lardi.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by vitalii.levash on 19.04.2016.
 */
@Controller
@RequestMapping(value ="/")
public class PhoneBookController {
    @Autowired
    private PhoneBookService phoneBookService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserPhoneList(ModelMap model){
        List<PhoneBook> list = phoneBookService.getUserPhoneList(securityService.getUserId());
        model.addAttribute("phones",list);
        return "phonelist";
    }
}
