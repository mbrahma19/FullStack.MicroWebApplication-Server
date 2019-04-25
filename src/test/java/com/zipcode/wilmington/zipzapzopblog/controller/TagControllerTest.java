package com.zipcode.wilmington.zipzapzopblog.controller;

import com.zipcode.wilmington.zipzapzopblog.model.Tag;
import com.zipcode.wilmington.zipzapzopblog.service.TagService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TagControllerTest {

    @Test
    public void testCreateTag() {
        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        TagService tagService = mock(TagService.class);

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(expectedId);

        // when the method save is called with the tag
        // then return the created tag
        when(tagService.createTag(tag)).thenReturn(tagToReturn);

        // create class to test
        TagController tagController = new TagController(tagService);

        // call the method to test
        ResponseEntity<Tag> actual = tagController.createTag(tag);

        //verify the result
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(tagService).createTag(tag);
    }

    @Test
    public void testGetTag() {
        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        TagService tagService = mock(TagService.class);

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(expectedId);

        // when the method save is called with the tag
        // then return the created tag
        when(tagService.getTag(expectedId)).thenReturn(tagToReturn);

        // create class to test
        TagController tagController = new TagController(tagService);

        // call the method to test
        ResponseEntity<Tag> actual = tagController.getTag(expectedId);

        //verify the result
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(tagService).getTag(expectedId);
    }

    @Test
    public void testGetAllTags() {
        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        TagService tagService = mock(TagService.class);

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(expectedId);
        List<Tag> listOfTags = new ArrayList<>();
        listOfTags.add(tagToReturn);

        // when the method save is called with the tag
        // then return the created tag
        when(tagService.findAll()).thenReturn(listOfTags);

        // create class to test
        TagController tagController = new TagController(tagService);

        // call the method to test
        ResponseEntity<List<Tag>> actual = tagController.getAllTags();

        //verify the result
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(tagService).findAll();
    }

    @Test
    public void testUpdateTag() {
        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        TagService tagService = mock(TagService.class);

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG2");
        tagToReturn.setId(expectedId);

        // when the method save is called with the tag
        // then return the created tag
        when(tagService.update(tag)).thenReturn(tagToReturn);

        // create class to test
        TagController tagController = new TagController(tagService);

        // call the method to test
        ResponseEntity<Tag> actual = tagController.updateTag(expectedId,tag);

        //verify the result
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(tagService).update(tag);
    }

    @Test
    public void testDeleteTagTest() {
        //given
        Tag tag = new Tag("TEST TAG");

        //mock
        TagService tagService = mock(TagService.class);

        //expected data
        Long expectedId = 3l;
        Tag tagToReturn = new Tag("TEST TAG");
        tagToReturn.setId(expectedId);

        // when the method save is called with the tag
        // then return the created tag

        // create class to test
        TagController tagController = new TagController(tagService);

        // call the method to test
        ResponseEntity<Boolean> actual = tagController.deleteTag(expectedId);

        //verify the result
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        verify(tagService).delete(expectedId);
    }
}
