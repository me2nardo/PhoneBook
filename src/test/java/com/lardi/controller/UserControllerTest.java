package com.lardi.controller;

import com.lardi.Application;
import com.lardi.model.User;
import com.lardi.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author vitalii.levash
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).dispatchOptions(true).addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(view().name("login"));
    }

    @Test
    public void testLoginProcces() throws Exception {
        mockMvc.perform(post("/j_spring_security_check").param("j_login", "leonard")
                .param("j_password", "12345"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(print());
    }

    @Test
    public void testLoginFailed() throws Exception {
        mockMvc.perform(post("/j_spring_security_check").param("j_login", "sctot")
                .param("j_password", "tiger"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?error"))
                .andDo(print());
    }

    @Test
    public void testRegisterForm() throws Exception {
        mockMvc.perform(get("/user/register"))
                .andExpect(view().name("userform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterUserFailed() throws Exception {
        mockMvc.perform(post("/user/register")
                .param("login", "Sem")
                .param("password", "SomePassword$#@")
                .param("fio", "Sem Smiths"))
                .andExpect(view().name("userform"))
                .andDo(print());
    }

    @Test
    public void testRegisterUserOk() throws Exception {
        mockMvc.perform(post("/user/register")
                .param("login", "Amadeus")
                .param("password", "SomePassword$#@")
                .param("fio", "Sem Amadeus"))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andDo(print());
        User user = userService.findByLogin("Amadeus");
        Assert.assertEquals(user.getLogin(), "Amadeus");
        userService.delete(user.getId());
        User user2 = userService.findByLogin("Amadeus");
        Assert.assertTrue(user2 == null);
    }

}
