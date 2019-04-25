package com.zipcode.wilmington.zipzapzopblog.controller;

import com.zipcode.wilmington.zipzapzopblog.model.Post;
import com.zipcode.wilmington.zipzapzopblog.model.Tag;
import com.zipcode.wilmington.zipzapzopblog.repository.PostRepo;
import com.zipcode.wilmington.zipzapzopblog.service.PostService;
import com.zipcode.wilmington.zipzapzopblog.service.TagService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.Mockito.*;

public class PostTagControllerTest {

    @Test
    public void testGetAllTags() {
        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        TagService tagService = mock(TagService.class);
        PostService postService = mock(PostService.class);

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(expectedId);
        List<Tag> listOfTags = new ArrayList<>();
        listOfTags.add(tagToReturn);
        Long postId = 111L;
        Post post = new Post();
        post.setId(postId);
        post.setTitle("TESTTITLE");
        post.setBody("TESTBODY");

        // when the method save is called with the tag
        // then return the created tag
        when(tagService.findPost(postId)).thenReturn(java.util.Optional.of(post));

        // create class to test
        PostTagController postTagController = new PostTagController(tagService, postService);

        // call the method to test
        ResponseEntity<Collection<Tag>> actual = postTagController.getAllTagsOnPost(postId);

        //verify the result
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(tagService).findPost(postId);
    }

    @Test
    public void testGetAllPostsByTag() {
        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        TagService tagService = mock(TagService.class);
        PostService postService = mock(PostService.class);

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(expectedId);
        List<Tag> listOfTags = new ArrayList<>();
        listOfTags.add(tagToReturn);
        Long postId = 111L;
        Post post = new Post();
        post.setId(postId);
        post.setTitle("TESTTITLE");
        post.setBody("TESTBODY");

        // when the method save is called with the tag
        // then return the created tag
        when(tagService.getTag(expectedId)).thenReturn(tagToReturn);

        // create class to test
        PostTagController postTagController = new PostTagController(tagService, postService);

        // call the method to test
        ResponseEntity<Collection<Post>> actual = postTagController.getAllPostsByTag(expectedId);

        //verify the result
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(tagService).getTag(expectedId);
    }

    @Test
    public void testUpdatePostTag() {
        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        TagService tagService = mock(TagService.class);
        PostService postService = mock(PostService.class);
        Post post = new Post();

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(expectedId);
        Long postId = 1L;
        post.setId(postId);
        post.setTitle("TESTTITLE");
        post.setBody("TESTBODY");

        // when the method save is called with the tag
        // then return the created tag
        when(tagService.findPost(postId)).thenReturn(Optional.of(post));
        when(postService.update(post)).thenReturn(post);
        when(tagService.getTag(expectedId)).thenReturn(tagToReturn);

        // create class to test
        PostTagController postTagController = new PostTagController(tagService,postService);

        // call the method to test
        ResponseEntity<Boolean> actual = postTagController.updatePostTag(postId,expectedId);

        //verify the result
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(tagService).getTag(expectedId);
        Assert.assertEquals(true, actual.getBody());
    }

    @Test
    public void testUpdatePostTagBad() {
        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        PostRepo postRepo = mock(PostRepo.class);
        TagService tagService = mock(TagService.class);
        PostService postService = new PostService(postRepo);
        Post post = new Post();

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(expectedId);
        List<Tag> listOfTags = Arrays.asList(tagToReturn);
        Long postId = 111L;

        post.setId(postId);
        post.setTitle("TESTTITLE");
        post.setBody("TESTBODY");

        // when the method save is called with the tag
        // then return the created tag
        when(tagService.findPost(expectedId)).thenReturn(java.util.Optional.of(post));
        when(postService.update(post)).thenReturn(post);
        when(postRepo.save(post)).thenReturn(post);


        // create class to test
        PostTagController postTagController = new PostTagController(tagService,postService);

        // call the method to test
        ResponseEntity<Boolean> actual = postTagController.updatePostTag(postId,expectedId);

        //verify the result
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        verify(tagService).getTag(expectedId);
        Assert.assertEquals(false, actual.getBody());
    }



    @Test
    public void testDeletePostTag() {
        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        TagService tagService = mock(TagService.class);
        PostService postService = mock(PostService.class);
        Post post = new Post();

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(expectedId);
        Long postId = 1L;
        post.setId(postId);
        post.setTitle("TESTTITLE");
        post.setBody("TESTBODY");

        // when the method save is called with the tag
        // then return the created tag
        when(tagService.findPost(postId)).thenReturn(Optional.of(post));
        when(postService.update(post)).thenReturn(post);
        when(tagService.getTag(expectedId)).thenReturn(tagToReturn);

        // create class to test
        PostTagController postTagController = new PostTagController(tagService,postService);

        // call the method to test
        ResponseEntity<Boolean> actual = postTagController.deletePostTag(postId,expectedId);

        //verify the result
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(tagService).getTag(expectedId);
        Assert.assertEquals(true, actual.getBody());
    }

    @Test
    public void testDeletePostTagBad() {
        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        PostRepo postRepo = mock(PostRepo.class);
        TagService tagService = mock(TagService.class);
        PostService postService = new PostService(postRepo);
        Post post = new Post();

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(expectedId);
        List<Tag> listOfTags = Arrays.asList(tagToReturn);
        Long postId = 111L;

        post.setId(postId);
        post.setTitle("TESTTITLE");
        post.setBody("TESTBODY");

        // when the method save is called with the tag
        // then return the created tag
        when(tagService.findPost(expectedId)).thenReturn(java.util.Optional.of(post));
        when(postService.update(post)).thenReturn(post);
        when(postRepo.save(post)).thenReturn(post);


        // create class to test
        PostTagController postTagController = new PostTagController(tagService,postService);

        // call the method to test
        ResponseEntity<Boolean> actual = postTagController.deletePostTag(postId,expectedId);

        //verify the result
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        verify(tagService).getTag(expectedId);
        Assert.assertEquals(false, actual.getBody());
    }
}
