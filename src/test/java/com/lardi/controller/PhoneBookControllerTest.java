package com.lardi.controller;

import com.lardi.Application;
import com.lardi.SecurityRequestPostProcessors;
import com.lardi.model.PhoneBook;
import com.lardi.service.PhoneBookService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author vitalii.levash
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class PhoneBookControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private PhoneBookService phoneBookService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).dispatchOptions(true).addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void testPhoneAddForm() throws Exception {
        mockMvc.perform(get("/add").with(SecurityRequestPostProcessors.userDeatilsService("leonard")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("phoneform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("phoneBook"));

    }

    @Test
    public void testMainPage() throws Exception {
        mockMvc.perform(get("/").with(SecurityRequestPostProcessors.userDeatilsService("leonard")))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("phonelist"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("filterCriteria"));

    }
    @Test
    public void phoneAddFailed() throws Exception{
        mockMvc.perform(post("/add").with(SecurityRequestPostProcessors.userDeatilsService("leonard"))
                .param("surname", "Stevenson")
                .param("name", "Mike")
                .param("lastname", "Steev")
                .param("address", "London")
                .param("mobPhone", "+39090-12")
                .param("phone", "+39090-12")
                .param("email", "mikegmail.com"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void phoneAddOk() throws Exception{
        mockMvc.perform(post("/add").with(SecurityRequestPostProcessors.userDeatilsService("leonard"))
                .param("surname","Stevenson")
                .param("name","Mike")
                .param("lastname","Steev")
                .param("address","London")
                .param("mobPhone","+38(095)0472067")
                .param("phone","+38(095)0472067")
                .param("email","mike@gmail.com"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(print());

    }
    @Test
    public void editPhoneItemForm() throws Exception {
        PhoneBook phoneBook = phoneBookService.getByEmail("mike@gmail.com");
        mockMvc.perform(post("/add").with(SecurityRequestPostProcessors.userDeatilsService("leonard"))
                .param("id", phoneBook.getId().toString())
                .param("surname", "Tayson")
                .param("name", "Mike")
                .param("lastname", "Steev")
                .param("address", "London")
                .param("mobPhone", "+38(095)0472067")
                .param("phone", "+38(095)0472067")
                .param("email", "mike@gmail.com"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(print());

        PhoneBook ubook = phoneBookService.getByEmail("mike@gmail.com");
        phoneBookService.deletePhoneItem(ubook.getId());
    }

    @Test
    public void testSearch() throws Exception{
        mockMvc.perform(post("/search").with(SecurityRequestPostProcessors.userDeatilsService("leonard"))
                .param("name", "mi")
                .param("mobPhone", "38"))
                .andExpect(MockMvcResultMatchers.view().name("phonelist"));
    }


}
