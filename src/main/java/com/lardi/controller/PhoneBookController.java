package com.lardi.controller;

import com.lardi.model.PhoneBook;
import com.lardi.model.User;
import com.lardi.service.PhoneBookService;
import com.lardi.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
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

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPhoneItem(ModelMap model){
        model.addAttribute("phoneBook",new PhoneBook());
        return "phoneform";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String registerUser(ModelMap model, @Valid @ModelAttribute("phoneBook") PhoneBook phoneBook, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "phoneform";
        }
        User user = securityService.getUserDetails();
        phoneBook.setUser(user);
        phoneBookService.addPhoneItem(phoneBook);
        model.addAttribute("info", "Your phone add!");
        return "phonelist";
    }

}
