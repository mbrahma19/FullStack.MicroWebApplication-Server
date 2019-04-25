package com.zipcode.wilmington.zipzapzopblog.controller;

import com.zipcode.wilmington.zipzapzopblog.model.User;
import com.zipcode.wilmington.zipzapzopblog.service.UserService;
import org.junit.Assert;
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
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    public void testRegistrationSucessful() throws Exception{
        // Given
        User expectedUser = new User("cw03@gmail.com", "1235","cw03","Charles","Wilmer");

        BDDMockito
                .given(service.save(expectedUser))
                .willReturn(expectedUser);

        BDDMockito
                .given(service.findByEmail(expectedUser.getEmail()))
                .willReturn(Optional.empty());
        BDDMockito
                .given(service.findByUsername(expectedUser.getUsername()))
                .willReturn(Optional.empty());


        String expectedContent = "{\"id\":4,\"email\":\"cw03@gmail.com\",\"password\":\"1235\",\"username\":\"cw03\",\"firstName\":\"Charles\",\"lastName\":\"Wilmer\"}";


        this.mvc.perform(MockMvcRequestBuilders
                .post("/registration")
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void testRegistrationExistingEmail() throws Exception{
        // Given
        User expectedUser = new User("cw03@gmail.com", "1235","cw03","Charles","Wilmer");
        User existingUser = new User("cw03@gmail.com", "1235","cw0123","Charles","Wilmer");

        BDDMockito
                .given(service.save(expectedUser))
                .willReturn(expectedUser);

        BDDMockito
                .given(service.findByEmail(expectedUser.getEmail()))
                .willReturn(Optional.of(existingUser));
        BDDMockito
                .given(service.findByUsername(expectedUser.getUsername()))
                .willReturn(Optional.empty());


        String expectedContent = "";


        this.mvc.perform(MockMvcRequestBuilders
                .post("/registration")
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
//                .andExpect(MockMvcResultMatchers.model().hasNoErrors());
    }

    @Test
    public void testRegistrationExistingUsername() throws Exception{
        // Given
        User expectedUser = new User("cw03@gmail.com", "1235","cw03","Charles","Wilmer");
        User existingUser = new User("charles03@gmail.com", "1235","cw03","Charles","Wilmer");

        BDDMockito
                .given(service.save(expectedUser))
                .willReturn(expectedUser);

        BDDMockito
                .given(service.findByEmail(expectedUser.getEmail()))
                .willReturn(Optional.empty());
        BDDMockito
                .given(service.findByUsername(expectedUser.getUsername()))
                .willReturn(Optional.of(existingUser));


        String expectedContent = "";


        this.mvc.perform(MockMvcRequestBuilders
                .post("/registration")
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }


    @Test
    public void testRegistrationBindingResultExistingUsername(){
        // Given
        User expectedUser = new User("cw03@gmail.com", "1235","cw03","Charles","Wilmer");
        User existingUser = new User("charles03@gmail.com", "1235","cw03","Charles","Wilmer");
        BindingResult errors = new BeanPropertyBindingResult(expectedUser,"expectedUser");
        String error = "There is already a user registered with the username provided";

        when(service.findByEmail(expectedUser.getEmail()))
                .thenReturn(Optional.empty());
        when(service.findByUsername(expectedUser.getUsername()))
                .thenReturn(Optional.of(existingUser));

        RegistrationController rc = new RegistrationController(service);

        rc.createNewUser(expectedUser,errors);

        Assert.assertEquals(error,errors.getFieldError("username").getDefaultMessage());

    }

    @Test
    public void testRegistrationBindingResultExistingEmail(){
        // Given
        User expectedUser = new User("cw03@gmail.com", "1235","cw03","Charles","Wilmer");
        User existingUser = new User("cw03@gmail.com", "1235","cw0123","Charles","Wilmer");
        BindingResult errors = new BeanPropertyBindingResult(expectedUser,"expectedUser");
        String error = "There is already a user registered with the email provided";

        when(service.findByEmail(expectedUser.getEmail()))
                .thenReturn(Optional.of(existingUser));
        when(service.findByUsername(expectedUser.getUsername()))
                .thenReturn(Optional.empty());

        RegistrationController rc = new RegistrationController(service);

        rc.createNewUser(expectedUser,errors);

        Assert.assertEquals(error,errors.getFieldError("email").getDefaultMessage());

    }

}
