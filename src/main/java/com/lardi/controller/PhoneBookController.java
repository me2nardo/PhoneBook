package com.lardi.controller;

import com.lardi.dto.FilterCriteria;
import com.lardi.exception.web.NotFoundException;
import com.lardi.model.PhoneBook;
import com.lardi.model.User;
import com.lardi.service.PhoneBookService;
import com.lardi.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.Optional.ofNullable;

/**
 * @author vitalii.levash
 */
@Controller
@RequestMapping(value = {"/"})
public class PhoneBookController {
    @Autowired
    private PhoneBookService phoneBookService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserPhoneList(ModelMap model) {
        List<PhoneBook> list = phoneBookService.getUserPhoneList(securityService.getUserId());
        model.addAttribute("filterCriteria", new FilterCriteria());
        model.addAttribute("phones", list);
        return "phonelist";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPhoneItem(ModelMap model) {
        model.addAttribute("phoneBook", new PhoneBook());
        return "phoneform";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPhoneItemPost(ModelMap model, @Valid @ModelAttribute("phoneBook") PhoneBook phoneBook,
                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "phoneform";
        }
        User user = securityService.getUserDetails();
        phoneBook.setUser(user);
        phoneBookService.addPhoneItem(phoneBook);
        //model.addAttribute("info", "Your phone add!");
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePhoneItem(@PathVariable("id") final int phone_id, ModelMap model) {
        Integer user = securityService.getUserId();
        PhoneBook phoneBook = ofNullable(phoneBookService.getById(phone_id))
                .orElseThrow(() -> new NotFoundException(phone_id));
        if (user.equals(phoneBook.getUser().getId())) {
            phoneBookService.deletePhoneItem(phone_id);
            model.addAttribute("info", "Your phone deleted!");
            return "phonelist";
        } else {
            throw new AccessDeniedException("Forbidden delete not yours items");
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPhoneForm(@PathVariable("id") final int phone_id, ModelMap model) {
        Integer user = securityService.getUserId();
        PhoneBook phoneBook = ofNullable(phoneBookService.getById(phone_id))
                .orElseThrow(() -> new NotFoundException(phone_id));
        if (user.equals(phoneBook.getUser().getId())) {
            model.addAttribute("phoneBook", phoneBook);
            return "phoneform";
        } else {
            throw new AccessDeniedException("Forbidden edit not yours items");
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String findBook(@ModelAttribute("filterCriteria") FilterCriteria filterCriteria,
                           ModelMap model) {
        Integer user = securityService.getUserId();
        filterCriteria.setUser_id(user);
        model.addAttribute("phones", phoneBookService.findPhones(filterCriteria));
        return "phonelist";
    }

}
