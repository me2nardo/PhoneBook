package org.rbo.controller;

import org.rbo.model.User;
import org.rbo.security.SecurityUtils;
import org.rbo.service.UserService;
import org.rbo.service.dto.UserDto;
import org.rbo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for managing current user account
 * @author vitalii.levash
 */
@RestController
@RequestMapping(value = "/api")
public class AccountController {

    @Autowired
    private UserService userService;

    /**
     * POST  /register : register the user.
     *
     * @param userDto the DTO presentation for user entity
     * @return the ResponseEntity with status 201 (Created) if the user is registered or 400 (Bad Request)
     */
    @PostMapping(path = "/register",
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> registerUser(@Validated @RequestBody UserDto userDto){

        User user = UserMapper.INSTANCE.toUser(userDto);
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    /**
     * PUT  /account : update the current user information. Allowed only for authorized users.
     *
     * @param userDto the current user information
     * @return the ResponseEntity with status 200 (OK), or status 400 (Bad Request) or 500 (Internal Server Error) if the user couldn't be updated
     */
    @PutMapping(path = "/account",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@Validated @RequestBody UserDto userDto){
/*
        userService.updateUser(SecurityUtils.getCurrentUserLogin(),
                userDto.getLastName(),userDto.getFirstName(),userDto.getName());
                */


        return new ResponseEntity<>(HttpStatus.OK);
    }
}