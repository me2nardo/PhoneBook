package org.rbo.controller;
import org.rbo.model.PhoneBook;
import org.rbo.service.PhoneBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author vitalii.levash
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class PhoneBookControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private PhoneBookService phoneBookService;



    @Test
    public void testPhoneAddForm() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("phoneform"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("phoneBook"));

    }

    @Test
    public void testMainPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("phonelist"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("filterCriteria"));

    }
    @Test
    public void phoneAddFailed() throws Exception{
        mockMvc.perform(post("/add")
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
        mockMvc.perform(post("/add")
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
        mockMvc.perform(post("/add")
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
        mockMvc.perform(post("/search")
                .param("name", "mi")
                .param("mobPhone", "38"))
                .andExpect(MockMvcResultMatchers.view().name("phonelist"));
    }


}
