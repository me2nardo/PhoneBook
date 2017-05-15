package org.rbo.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.rbo.dao.UserDao;
import org.rbo.exception.Auth.UserExistsException;
import org.rbo.model.User;
import org.rbo.service.UserService;
import org.rbo.util.Steps;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * UserService using JB
 */
@Steps
public class UserServiceSteps {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ObjectMapper objectMapper;
    private Throwable throwable = null;


    @When("Try to register $value")
    public void tryToSave(String value) throws IOException {
        User user = objectMapper.readValue(value,User.class);
        System.out.println(user.toString());
        try {
            userService.registerUser(user);
        } catch (UserExistsException e){
            throwable = e;
            System.out.println("User Exists");
        }
    }

    @Then("Check registered user $user")
    public void validate(String user){
        System.out.println("validating user");
        assertTrue(userDao.findOneByUsername(user).isPresent());
    }

    @Then("Validate UserExists $message")
    public void validateUserExists(String message){
        assertTrue(throwable.getMessage().equals(message));
    }

}
