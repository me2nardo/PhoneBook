package org.rbo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rbo.Application;
import org.rbo.dao.UserDao;
import org.rbo.model.User;
import org.rbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for UserController
 * @author vitalii.levash
 * @see org.rbo.controller.UserController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserControllerTest {

    private MockMvc restMock;

    @Autowired private UserService userService;
    @Autowired private UserDao userDao;

    @Before
    public void setup() {
        UserController userController = new UserController(userService);
        this.restMock = MockMvcBuilders.standaloneSetup(userController).build();
    }

    /**
     * Test not existing user. Expect return 404.
     */
    @Test
    public void getUserUnknownTest() throws Exception {

        restMock.perform(get("/api/user/unknown")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    /**
     * Test return existing user. Expect return 200 and UserDto.
     */
    @Test
    public void getUserTest() throws Exception {

        restMock.perform(get("/api/user/sJohn")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    /**
     * Test delete existing user. Expect return 200 OK.
     */
    @Test
    public void deleteUserTest() throws Exception {
        restMock.perform(delete("/api/user/sJohn2"))
                .andExpect(status().isOk())
                .andDo(print());
       Optional<User> user = userDao.findOneByUsername("sJohn2");
       assertFalse(user.isPresent());
    }

    //TODO:: add update user test
}
