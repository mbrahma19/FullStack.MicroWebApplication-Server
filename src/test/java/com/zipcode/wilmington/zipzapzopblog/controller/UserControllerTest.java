package com.zipcode.wilmington.zipzapzopblog.controller;

import com.zipcode.wilmington.zipzapzopblog.model.User;
import com.zipcode.wilmington.zipzapzopblog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    public void showUserTest() throws Exception{
        // given
        User expectedUser = new User("cw03@gmail.com", "1235","cw03","Charles","Wilmer");
        expectedUser.setId(4L);
        String expectedContent = "{\"id\":4,\"email\":\"cw03@gmail.com\",\"password\":\"1235\",\"username\":\"cw03\",\"firstName\":\"Charles\",\"lastName\":\"Wilmer\"}";

        // mock
        BDDMockito
                .given(service.show(4L))
                .willReturn(Optional.of(expectedUser));


        this.mvc.perform(MockMvcRequestBuilders
                .get("/user/" + 4L)
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void testShowUserNegative() throws Exception{
        // given
        String expectedContent = "";

        // mock
        BDDMockito
                .given(service.show(4L))
                .willReturn(Optional.empty());


        this.mvc.perform(MockMvcRequestBuilders
                .get("/user/" + 4L)
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void deleteUserPositiveTest() throws Exception, SQLException {

    }

    @Test
    public void deleteUserNegativeTest() throws Exception, SQLException {

    }

    @Test
    public void showAllUserTest() throws Exception{
        // given
        User expectedUser1 = new User("cw03@gmail.com", "1235","cw03","Charles","Wilmer");
        User expectedUser2 = new User("cw03@gmail.com", "1235","cw03","Charles","Wilmer");
        List<User> users = Arrays.asList(expectedUser1,expectedUser2);

        String expectedContent = "[{\"id\":0,\"email\":\"cw03@gmail.com\",\"password\":\"1235\",\"username\":\"cw03\",\"firstName\":\"Charles\",\"lastName\":\"Wilmer\"},"+
                "{\"id\":0,\"email\":\"cw03@gmail.com\",\"password\":\"1235\",\"username\":\"cw03\",\"firstName\":\"Charles\",\"lastName\":\"Wilmer\"}]";

        // mock
        BDDMockito
                .given(service.showAll())
                .willReturn(users);


        this.mvc.perform(MockMvcRequestBuilders
                .get("/users")
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }




}
