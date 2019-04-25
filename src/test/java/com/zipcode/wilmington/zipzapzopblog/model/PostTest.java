package com.zipcode.wilmington.zipzapzopblog.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PostTest {
    private Post post;

    @Before
    public void setup(){
        post = new Post();
    }


    @Test
    public void testSetId() {
        Long expectedId = 1L;
        post.setId(expectedId);
        Long actual = post.getId();

        Assert.assertEquals(expectedId,actual);

    }

    @Test
    public void testSetTitle() {
        String expectedTitle = "New Post";
        post.setTitle(expectedTitle);
        String actual = post.getTitle();

        Assert.assertEquals(expectedTitle, actual);
    }


    @Test
    public void testSetBody() {
        String expectedBody = "Postity Post Post";
        post.setBody(expectedBody);
        String actual = post.getBody();

        Assert.assertEquals(expectedBody, actual);
    }

    @Test
    public void testSetCreateDate() {
        Date date = new Date();
        post.setCreateDate(date);
        Date actual = post.getCreateDate();

        Assert.assertEquals(date,actual);
    }

    @Test
    public void testSetUser() {
        User user = new User();
        post.setUser(user);
        User actual = post.getUser();

        Assert.assertEquals(user, actual);
    }

    @Test
    public void testSetTags() {
        List<Tag> list = new ArrayList<>();
        Tag tag = new Tag();
        list.add(tag);

        post.setTags(list);
        Collection<Tag> actual = post.getTags();

        Assert.assertEquals(list, actual);

        }
    }

