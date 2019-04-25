package com.zipcode.wilmington.zipzapzopblog.controller;


import com.zipcode.wilmington.zipzapzopblog.model.Comment;
import com.zipcode.wilmington.zipzapzopblog.repository.CommentRepo;
import com.zipcode.wilmington.zipzapzopblog.service.CommentService;
import com.zipcode.wilmington.zipzapzopblog.service.PostService;
import com.zipcode.wilmington.zipzapzopblog.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)



public class CommentControllerTest {

    @Autowired
    private MockMvc mvc;


    @MockBean
    private CommentRepo repository;



    @Test
    public void getCommentTest() throws Exception {

        //given
        String expected = "This is my comment";
        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Comment newComment = new Comment(1L,"This is my comment",CommentDate,null,null);
        Long givenId = 1L;

        BDDMockito
                .given(repository.findById(givenId))
                .willReturn(Optional.of(newComment));

        String expectedContent = "{\"id\":1,\"body\":\"This is my comment\",\"creationDate\":\"2019-04-09T04:00:00.000+0000\",\"post\":null,\"user\":null}";
        this.mvc.perform(MockMvcRequestBuilders
                .get("/comments/" + givenId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }





    @Test
    public void CreateCommentbyPostTest() throws Exception {

       //given
        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Comment comment = new Comment(1L,"This is my comment",CommentDate,null,null);
        Long givenId = 1L;

        BDDMockito
                .given(repository.save(comment))
                .willReturn(comment);


        String expectedContent = "{\"body\":\"This is my comment\",\"creationDate\":\"2019-04-09T04:00:00.000+0000\",\"post\":null,\"user\":null}";

        this.mvc.perform(MockMvcRequestBuilders
                .post("/commentMyPost/" + givenId)
                .content(expectedContent)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated());
                //.andExpect(MockMvcResultMatchers.content().string(expectedContent));
    }




    @Test
    public void CreateCommentTest() {
        //given
        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Comment comment = new Comment(1L,"This is my comment",CommentDate,null,null);
        Long givenId = 1L;

        //mock
        CommentService commentService = mock(CommentService.class);
        UserService userService = mock(UserService.class);
        PostService postService = mock(PostService.class);

        //expected data
        Long expectedId = 1L;
        Comment commentToReturn = new Comment(1L,"This is my comment",CommentDate,null,null);


        // when the method save is called with the comment
        // then return the created comment
        when(commentService.createComment(comment)).thenReturn(commentToReturn);

        // create class to test
       CommentController commentController = new CommentController(commentService,userService,postService);

        // call the method to test
        ResponseEntity<Comment> actual = commentController.createComment(comment);

        //verify the result
        Assert.assertEquals(HttpStatus.CREATED, actual.getStatusCode());
        verify(commentService).createComment(comment);
    }






    @Test
    public void GetAllCommentsTest() {
        //given
        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Comment expectedComment1 = new Comment(1L,"This is my comment",CommentDate,null,null);
        Comment expectedComment2 = new Comment(2L,"This is good comment",CommentDate,null,null);


        //expected data

        HttpStatus expected = HttpStatus.OK;
        Iterable<Comment> expectedCommentList= new ArrayList<>();


        ((ArrayList<Comment>) expectedCommentList).add(expectedComment1);
        ((ArrayList<Comment>) expectedCommentList).add(expectedComment2);
        //mock

        CommentService commentService = mock(CommentService.class);
        UserService userService = mock(UserService.class);
        PostService postService = mock(PostService.class);


        // when the method index is called
        // then return the list of all comments
        BDDMockito.
                given(commentService.index())
                .willReturn(expectedCommentList);

        // create class to test
      CommentController commentController = new CommentController(commentService,userService,postService);

        ResponseEntity<Iterable<Comment>> response = commentController.getAllComments();
        HttpStatus actual = response.getStatusCode();
        Iterable<Comment> actualComment = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedCommentList, actualComment);
    }




    @Test
    public void deleteCommentTest() {

        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Given

        HttpStatus expected = HttpStatus.CREATED;
        Long expectedId = 5l;
        Comment expectedComment= new Comment("I am so good", CommentDate);

        //mock

        CommentService commentService = mock(CommentService.class);
        UserService userService = mock(UserService.class);
        PostService postService = mock(PostService.class);


        expectedComment.setId(expectedId);
        BDDMockito
                .given(commentService.createComment(expectedComment))
                .willReturn(expectedComment);


        // create class to test
        CommentController commentController = new CommentController(commentService,userService,postService);

        // When
        ResponseEntity<Comment> response = commentController.createComment(expectedComment);
        HttpStatus actual = response.getStatusCode();
        Comment actualComment = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedComment, actualComment);

        BDDMockito
                .given(commentService.delete(5L))
                .willReturn(true);

        ResponseEntity<Boolean> deleteComment = commentController.deleteComment(5L);
        Boolean afterDelete = deleteComment.getBody();

        Assert.assertEquals( true,afterDelete);


    }


    @Test
    public void updateCommentTest() {


        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //given
        Comment commentToReturn = new Comment("I am so good", CommentDate);
        commentToReturn.setId(1L);

        //mock
        CommentRepo commentRepo = mock(CommentRepo.class);

        // when actions
        when(commentRepo.findById(1L)).thenReturn(Optional.of(commentToReturn));
        // create class to test
        CommentService commentService = new CommentService(commentRepo);
        Comment comment = commentRepo.findById(1L).get();


        // call the method to test
        Comment actual = commentService.edit(1l, comment);

        // Make sure the method save got call with the comment
        verify(commentRepo).save(comment);
    }


    @Test
    public void getAllCommentsByPostTest() {
        // Given

        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long CommentId = 1L;
        Long PostId = 1L;


        HttpStatus expected = HttpStatus.OK;
        Comment expectedComment1 = new Comment("I am so good", CommentDate);
        Comment expectedComment2 = new Comment("I am very good", CommentDate);

        List<Comment> expectedCommentList= new ArrayList<>();

        expectedCommentList.add(expectedComment1);
        expectedCommentList.add(expectedComment2);


        //mock

        CommentService commentService = mock(CommentService.class);
        UserService userService = mock(UserService.class);
        PostService postService = mock(PostService.class);


        // create class to test
        CommentController commentController = new CommentController(commentService,userService,postService);

        BDDMockito.
                given(commentService.findCommentbyPostId(1L))
                .willReturn(expectedCommentList);

        // When
        ResponseEntity<List<Comment>> response = commentController.getAllCommentsByPost(PostId);
        HttpStatus actual = response.getStatusCode();
        List<Comment> actualComment = response.getBody();

        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedCommentList, actualComment);
    }



    @Test
    public void getAllCommentsByDateTest() {
        // Given

        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long CommentId = 1L;
        Long PostId =1L;


        HttpStatus expected = HttpStatus.OK;
        Comment expectedComment1 = new Comment("I am so good", CommentDate);
        Comment expectedComment2 = new Comment("I am very good", CommentDate);

        List<Comment> commentList = new ArrayList<>();
        commentList.add(expectedComment1);
        commentList.add(expectedComment2);


        //mock

        CommentService commentService = mock(CommentService.class);
        UserService userService = mock(UserService.class);
        PostService postService = mock(PostService.class);
        BDDMockito.
                given(commentService.findCommentbyCreationDate(CommentDate)).willReturn(commentList);

        // When

        // create class to test
        CommentController commentController = new CommentController(commentService,userService,postService);


        ResponseEntity<List<Comment>> response = commentController.getAllCommentsByDate(CommentDate);
        HttpStatus actual = response.getStatusCode();
        List<Comment> actualComment = response.getBody();
        System.out.println(expected.toString());
        System.out.println(actual.toString());
        System.out.println(commentList.toString());
        System.out.println(actualComment.toString());


        // Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expectedComment1.toString(), actualComment.get(0).toString());
        Assert.assertEquals(commentList, actualComment);
    }










}
