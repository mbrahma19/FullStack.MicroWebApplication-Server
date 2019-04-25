package com.zipcode.wilmington.zipzapzopblog.model;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentModelTest {
    @Test
    public void CommentSetBodyTest() {
        //given
        String expected = "This is my comment";
        Comment newComment = new Comment();
        //when
        newComment.setBody(expected);
        String actual = newComment.getBody();
        //then
        Assert.assertEquals(expected,actual);
    }




    @Test
    public void CommentConstructorTest() {
        //given
        String expected = "This is my comment";
        Date CommentDate = null;
        try {

            CommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Comment newComment = new Comment("This is my comment",CommentDate);
        //when
        String actualComment = newComment.getBody();
        Date actualCommentDate = newComment.getCreationDate();
        //then
        Assert.assertEquals(expected,actualComment);
        Assert.assertEquals(CommentDate,actualCommentDate);
    }



    @Test
    public void CommentGetCreationDateTest() {
        //given
        Date ExpectedCommentDate = null;
        try {

            ExpectedCommentDate = new SimpleDateFormat("yyyy-MM-dd").parse("2019-04-09");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Comment newComment = new Comment();
        //when
        newComment.setCreationDate(ExpectedCommentDate);
       Date actualDate = newComment.getCreationDate();
        //then
        Assert.assertEquals(ExpectedCommentDate,actualDate);
    }


    @Test
    public void CommentGetIdTest() {
        //given

        Long expected = 3L;
        Comment newComment = new Comment();
        //when
        newComment.setId(3L);
        Long actual = newComment.getId();
        //then
        Assert.assertEquals(expected,actual);
    }




    @Test
    public void CommentGetPostTest() {
        //given

       Post post = new Post();
        Comment newComment = new Comment();
        //when
        newComment.setPost(post);
        Post actual = newComment.getPost();
        //then
        Assert.assertEquals(post,actual);
    }


    @Test
    public void CommentGetUserTest() {
        //given

        User user = new User("shuchi@yahoo.com","ABCD123", "ShuchiSharma","Shuchi","Sharma");
        Comment newComment = new Comment();
        //when
        newComment.setUser(user);
       User actual = newComment.getUser();
        //then
        Assert.assertEquals(user,actual);

        Assert.assertEquals("shuchi@yahoo.com",user.getEmail());
        Assert.assertEquals("ShuchiSharma",user.getUsername());
    }

}
