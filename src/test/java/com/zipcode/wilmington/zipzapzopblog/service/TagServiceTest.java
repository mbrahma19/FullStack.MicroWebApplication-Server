package com.zipcode.wilmington.zipzapzopblog.service;

import com.zipcode.wilmington.zipzapzopblog.model.Post;
import com.zipcode.wilmington.zipzapzopblog.model.Tag;
import com.zipcode.wilmington.zipzapzopblog.repository.PostRepo;
import com.zipcode.wilmington.zipzapzopblog.repository.TagRepo;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TagServiceTest {

    @Test
    public void createTagTest(){

        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        TagRepo tagRepo = mock(TagRepo.class);
        PostRepo postRepo = mock(PostRepo.class);

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(expectedId);

        // when the method save is called with the tag
        // then return the created tag
        when(tagRepo.save(tag)).thenReturn(tagToReturn);

        // create class to test
        TagService tagService = new TagService(tagRepo,postRepo);

        // call the method to test
        Tag actual = tagService.createTag(tag);

        //verify the result
        Assert.assertEquals(expectedId, actual.getId());
    }

    @Test
    public void updateTest(){
        //given
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(3L);

        //mock
        TagRepo tagRepo = mock(TagRepo.class);
        PostRepo postRepo = mock(PostRepo.class);

        // when actions
        when(tagRepo.findById(3L)).thenReturn(Optional.of(tagToReturn));
        // create class to test
        TagService tagService = new TagService(tagRepo,postRepo);
        Tag tag = tagRepo.findById(3L).get();

        // call the method to test
        Tag actual = tagService.update(tag);

        // Make sure the method save got called with the tag
        verify(tagRepo).save(tag);
    }

    @Test
    public void getTagTest(){
        //given
        Tag tagToReturn = new Tag("TEST TAG");
        Long expected = 3L;
        tagToReturn.setId(3L);

        //mock
        TagRepo tagRepo = mock(TagRepo.class);
        PostRepo postRepo = mock(PostRepo.class);

        // when actions
        when(tagRepo.findById(3L)).thenReturn(Optional.of(tagToReturn));
        // create class to test
        TagService tagService = new TagService(tagRepo,postRepo);

        // call the method to test
        Tag actual = tagService.getTag(3L);

        Assert.assertEquals(expected, actual.getId());
    }

    @Test
    public void findAllTest(){
        //given
        Tag tagToReturn1 = new Tag("TEST TAG");
        Long id1 = 3L;
        tagToReturn1.setId(id1);
        Tag tagToReturn2 = new Tag("TEST TAG2");
        Long id2 = 3L;
        tagToReturn2.setId(id2);
        List<Tag> listOfTags = new ArrayList<>();
        listOfTags.add(tagToReturn1);
        listOfTags.add(tagToReturn2);

        //mock
        TagRepo tagRepo = mock(TagRepo.class);
        PostRepo postRepo = mock(PostRepo.class);

        // when actions
        when(tagRepo.findAll()).thenReturn(listOfTags);

        // create class to test
        TagService tagService = new TagService(tagRepo,postRepo);


        // call the method to test
        List actual = tagService.findAll();

        Assert.assertEquals(listOfTags, actual);
    }

    @Test
    public void findPostTest(){
        //given
        Tag tagToReturn1 = new Tag("TEST TAG");
        Long id1 = 3L;
        tagToReturn1.setId(id1);
        Post post = new Post();
        post.setId(111L);
        post.setTitle("TESTTITLE");
        post.setBody("TESTBODY");
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(post);

        //mock
        TagRepo tagRepo = mock(TagRepo.class);
        PostRepo postRepo = mock(PostRepo.class);

        // when actions
        when(postRepo.findById(111L)).thenReturn(Optional.of(post));

        // create class to test
        TagService tagService = new TagService(tagRepo,postRepo);

        // call the method to test
        Optional<Post> actual = tagService.findPost(111L);

        Assert.assertEquals(Optional.of(post), actual);
    }
    @Test
    public void deleteTagTest(){
        //given
        Tag tagToReturn1 = new Tag("TEST TAG");
        Long id1 = 3L;
        tagToReturn1.setId(id1);

        //mock
        TagRepo tagRepo = mock(TagRepo.class);
        PostRepo postRepo = mock(PostRepo.class);

        // create class to test
        TagService tagService = new TagService(tagRepo,postRepo);

        // call the method to test
        tagService.delete(id1);

        verify(tagRepo).deleteById(id1);
    }
}
