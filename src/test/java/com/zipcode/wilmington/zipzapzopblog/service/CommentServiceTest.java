package com.zipcode.wilmington.zipzapzopblog.service;

import com.zipcode.wilmington.zipzapzopblog.controller.CommentController;
import com.zipcode.wilmington.zipzapzopblog.model.Comment;
import com.zipcode.wilmington.zipzapzopblog.repository.CommentRepo;
import com.zipcode.wilmington.zipzapzopblog.repository.PostRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)

public class CommentServiceTest {


    @MockBean
    private CommentService commentservice;
    private PostService postservice;
    private UserService userservice;

    private CommentController controller;

    @Before
    public void setup() {
        this.controller = new CommentController(commentservice, userservice, postservice);
    }


    @Test
    public void createCommentTest() {

        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //given
        Comment comment = new Comment("I am so good", CommentDate);

        //mock
        CommentRepo commentRepo = mock(CommentRepo.class);

        //expected data
        Long expectedId = 5l;
        Comment commentToReturn = new Comment("I am so good", CommentDate);
        commentToReturn.setId(expectedId);

        // when the method save is called with the user
        // then return the created user
        when(commentRepo.saveAndFlush(comment)).thenReturn(commentToReturn);

        // create class to test
        CommentService commentService = new CommentService(commentRepo);

        // call the method to test
        Comment actual = commentService.createComment(comment);

        //verify the result
        Assert.assertEquals(expectedId, actual.getId());

    }


    @Test
    public void updateTest() {


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
        public void testShowbyDate() {
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
              CommentRepo commentRepo = mock(CommentRepo.class);

            // create class to test
            CommentService commentService = new CommentService(commentRepo);

            // when actions
            when(commentRepo.findCommentsByCreationDate(CommentDate)).thenReturn(commentList);


            // call the method to test
            List<Comment> actualList = commentService.findCommentbyCreationDate(CommentDate);




               Assert.assertEquals(commentList, actualList);
            }


    @Test
    public void getCommentTest(){

        // Given

        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long CommentId = 1L;
        HttpStatus expected = HttpStatus.OK;
        Comment expectedComment = new Comment("I am so good", CommentDate);
        expectedComment.setId(CommentId);

        //mock
        CommentRepo commentRepo = mock(CommentRepo.class);

        // when actions
        when(commentRepo.findById(1L)).thenReturn(Optional.of(expectedComment));

        // create class to test
        CommentService commentService = new CommentService(commentRepo);

        // call the method to test
        Comment actual = commentService.show(1L);


        Assert.assertEquals(expectedComment, actual);
    }






    @Test
    public void createnewCommentTest(){

        // Given

        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long CommentId = 1L;
        HttpStatus expected = HttpStatus.OK;
        Comment expectedComment = new Comment("I am so good", CommentDate);
        expectedComment.setId(CommentId);

       //mock
        CommentRepo commentRepo = mock(CommentRepo.class);

        //expected data
        Long expectedId = 1l;
        String expectedbody ="I am so good";
        Comment commentToReturn = new Comment("I am so good", CommentDate);
        commentToReturn.setId(expectedId);

        // when the method save is called with the comment
        // then return the created comment
        when(commentRepo.saveAndFlush(expectedComment)).thenReturn(commentToReturn);

        // create class to test
        CommentService commentService = new CommentService(commentRepo);

        // call the method to test
        Comment actual = commentService.createComment(expectedComment);

        //verify the result
        expectedComment.toString();
        actual.toString();
        Assert.assertEquals(expectedId, actual.getId());
        Assert.assertEquals(expectedbody,actual.getBody());
    }







    @Test
    public void DeleteCommentTest(){

        // Given for delete is whole create

        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long CommentId = 1L;
        HttpStatus expected = HttpStatus.OK;
        Comment expectedComment = new Comment("I am so good", CommentDate);
        expectedComment.setId(CommentId);

        //mock
        CommentRepo commentRepo = mock(CommentRepo.class);

        //expected data
        Long expectedId = 1l;
        String expectedbody ="I am so good";
        Comment commentToReturn = new Comment("I am so good", CommentDate);
        commentToReturn.setId(expectedId);

        // when the method save is called with the comment
        // then return the created comment
        when(commentRepo.saveAndFlush(expectedComment)).thenReturn(commentToReturn);

        // create class to test
        CommentService commentService = new CommentService(commentRepo);

        // call the method to test
        Comment actual = commentService.createComment(expectedComment);

        //verify the result for create
        expectedComment.toString();
        actual.toString();
        Assert.assertEquals(expectedId, actual.getId());
        Assert.assertEquals(expectedbody,actual.getBody());

        //Test for Delete After creating

        //Given

        Boolean expectedafterdelete = true;

        // when
        Boolean actualafterdelete = commentService.delete(expectedId);
        //then

        Assert.assertEquals(expectedafterdelete, actualafterdelete);

    }


    @Test
    public void testShowAllComments() {
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

        List<Comment> expectedCommentList = new ArrayList<>();


        expectedCommentList.add(expectedComment2);
        expectedCommentList.add(expectedComment2);

        Iterable<Comment> newList = new ArrayList<>();

        ((ArrayList<Comment>) newList).addAll(expectedCommentList);
        //mock
        CommentRepo commentRepo = mock(CommentRepo.class);

        // when actions
        when(commentRepo.findAll()).thenReturn(expectedCommentList);

        // create class to test
        CommentService commentService = new CommentService(commentRepo);


        // call the method to test
        Iterable<Comment> actual = commentService.index();

        Assert.assertEquals(newList, actual);
    }


    @Test
    public void testShowbypostId() {
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

        //mock
        CommentRepo commentRepo = mock(CommentRepo.class);


        // create class to test
        CommentService commentService = new CommentService(commentRepo);

        BDDMockito.
                given(commentRepo.findCommentsByPostId(1L)).willReturn(expectedCommentList);


        // When
     List<Comment> actualList = commentService.findCommentbyPostId(PostId);

        // Then

        Assert.assertEquals(expectedCommentList, actualList);
    }


}










