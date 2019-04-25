package com.zipcode.wilmington.zipzapzopblog.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TagModelTest {
    @Test
    public void testTagConstructor() {
        //given
        String expectedKeyword = "ABCDEF";
        Tag tag = new Tag(expectedKeyword);

        //when
        //then
        Assert.assertEquals(expectedKeyword, tag.getKeyWord());
    }

    @Test
    public void testTagKeyword() {
        //given
        Tag tag = new Tag("TESTTAG123");
        String expectedKeyword = "ABCDEF";

        //when
        tag.setKeyWord(expectedKeyword);

        //then
        Assert.assertEquals(expectedKeyword, tag.getKeyWord());
    }

    @Test
    public void testTagGetPosts() {
        //given
        Tag tag = new Tag("TESTTAG123");
        Post post = new Post();
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post);

        //when
        tag.setPosts(posts);

        //then
        Assert.assertEquals(posts, tag.getPosts());
    }

    @Test
    public void testTagToString() {
        //given
        Tag tag = new Tag("TESTTAG123");
        tag.setId(333L);
        Post post = new Post();
        post.setId(111L);
        post.setTitle("TESTTITLE");
        post.setBody("TESTBODY");
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post);
        String expected = "Tag{id=333, keyWord='TESTTAG123', " +
                            "posts=[Post{id=111, title='TESTTITLE', " +
                            "body='TESTBODY', createDate=null, user=null, tags=[]}]}";
        //when
        tag.setPosts(posts);

        //then
        Assert.assertEquals(expected, tag.toString());
    }


}
