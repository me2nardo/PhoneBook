package org.rbo.controller;

import org.rbo.model.User;
import org.rbo.service.UserService;
import org.rbo.service.dto.UserDto;
import org.rbo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for managing users
 * <p>Allowed only with ROLE_ADMIN</p>
 * @author vitalii.levash
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST  /user  : Creates a new user.
     * <p>It's create if username and email does not exists in database</p>
     * @param userDto : the user to create
     * @return ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) user exists
     */
    @PostMapping(value = "/user")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User> createUser(@Validated @RequestBody UserDto userDto){
        User user = UserMapper.INSTANCE.toUser(userDto);
        user.setAuthority(UserMapper.INSTANCE.authoritiesFromStrings(userDto.getAuthorities()));
        //TODO :: send notification
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    /**
     * GET  /user/{username}  : Get information about custom user.
     * @param username : the user to find
     * @return ResponseEntity with status 200 (OK) and with body the new user, or with status 400 (Bad Request) user does not exists
     */
    @GetMapping(value = "/user/{username}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserDto> getUser(@PathVariable String username){
        User user = userService.findUserByName(username);
        return new ResponseEntity<>(UserMapper.INSTANCE.toUserDto(user), HttpStatus.OK);
    }


    /**
     * DELETE  /user/{username}  : Delete user.
     * @param username : the user to delete
     * @return ResponseEntity with status 200 (DELETED) and with body the new user, or with status 400 (Bad Request) user does not exists
     */
    @DeleteMapping(value = "/user/{username}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity deleteUser(@PathVariable String username){
        userService.deleteUser(username);
        return new ResponseEntity(HttpStatus.OK);
     }

    /**
     * PUT  /user  : Update user. Can update any user.
     * @param userDto : the user to update
     * @return ResponseEntity with status 200 (OK), or with status 400 (Bad Request) user does not exists
     */
     @PutMapping(value = "/user")
     @Secured("ROLE_ADMIN")
     public ResponseEntity updateUser(@Validated @RequestBody UserDto userDto){
         User user = UserMapper.INSTANCE.toUser(userDto);
         user.setAuthority(UserMapper.INSTANCE.authoritiesFromStrings(userDto.getAuthorities()));
         userService.updateUser(user);
         return new ResponseEntity(HttpStatus.OK);
     }

}
