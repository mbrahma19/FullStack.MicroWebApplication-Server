package com.zipcode.wilmington.zipzapzopblog.controller;

import com.zipcode.wilmington.zipzapzopblog.model.Post;
import com.zipcode.wilmington.zipzapzopblog.repository.PostRepo;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)

public class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostRepo repo;

    @Test
    public void testIndex() throws Exception {
        List<Post> list = new ArrayList<>();
        Post post= new Post("New Post",null,null);
        list.add(post);
        String expectedContent = "[{\"id\":null,\"title\":\"New Post\",\"body\":null,\"createDate\":null,\"user\":null}]";
        BDDMockito
                .given(repo.findByOrderByCreateDateDesc())
                .willReturn(list);

        this.mvc.perform(MockMvcRequestBuilders
                .get("/posts/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void testShow() throws Exception{
        Long givenId = 1L;
        BDDMockito
                .given(repo.findById(givenId))
                .willReturn(Optional.of(new Post("New Post",null,null)));

        String expectedContent = "{\"id\":null,\"title\":\"New Post\",\"body\":null,\"createDate\":null,\"user\":null}";
        this.mvc.perform(MockMvcRequestBuilders
                .get("/posts/"+ givenId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void testcreate() throws Exception {
        Post post = new Post("New Post",null,null);
        BDDMockito
                .given(repo.save(post))
                .willReturn(post);

        String expectedContent="{\"id\":null,\"title\":\"New Post\",\"body\":null,\"createDate\":null,\"user\":null}";
        this.mvc.perform(MockMvcRequestBuilders
                .post("/posts/")
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }

    @Test
    public void update() throws Exception {
            Post post = new Post("New Post",null,null);
            BDDMockito
                    .given(repo.save(post))
                    .willReturn(post);
            String expectedContent="{\"id\":null,\"title\":\"New Post\",\"body\":null,\"createDate\":null,\"user\":null}";
            this.mvc.perform(MockMvcRequestBuilders
                    .put("/posts")
                    .content(expectedContent)
                    //what type of data I want to get back
                    .accept(MediaType.APPLICATION_JSON)
                    //what type of data I'm saving
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    //isOK is the httpStatus code
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    //verifying the server content
                    .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }


    @Test
    public void testDestroy() throws Exception {
        Long givenId = 101L;

        this.mvc.perform(MockMvcRequestBuilders
                .delete("/posts/" + givenId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));

        verify(repo).deleteById(givenId);

    }
}