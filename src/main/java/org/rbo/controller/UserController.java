package org.rbo.controller;

import org.rbo.exception.Auth.impl.LoginExistsException;
import org.rbo.model.User;
import org.rbo.service.SecurityService;
import org.rbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author vitalii.levash
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserInfo(ModelMap model){
        User user = userService.getById(securityService.getUserId());
        model.addAttribute("info",user);
        return "user";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(ModelMap model){
        model.addAttribute(new User());
        return "userform";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String registerUser(ModelMap model, @Valid @ModelAttribute User user, BindingResult bindingResult) throws LoginExistsException{

        if (bindingResult.hasErrors()) {
            return "userform";
        }
        try {
            userService.addUser(user);
        }catch (LoginExistsException e){
            throw new LoginExistsException();
        }

        model.addAttribute("info", "Thanks for registration! You can login with your credentials!");
        return "login";
    }
}