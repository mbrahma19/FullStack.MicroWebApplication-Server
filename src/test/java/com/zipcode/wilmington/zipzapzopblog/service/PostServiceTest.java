package com.zipcode.wilmington.zipzapzopblog.service;

import com.zipcode.wilmington.zipzapzopblog.model.Post;
import com.zipcode.wilmington.zipzapzopblog.model.User;
import com.zipcode.wilmington.zipzapzopblog.repository.PostRepo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class PostServiceTest {

    @Test
    public void testShow(){

        Post post = new Post("New Post", null, null);

        PostRepo mockRepo = mock(PostRepo.class);
        long expectedId = 1;
        Optional<Post> optional = Optional.of(post);

        when(mockRepo.findById(expectedId)).thenReturn(optional);

        PostService service = new PostService(mockRepo);

        Optional<Post> actual = service.show(expectedId);

        Assert.assertEquals(optional, actual);
    }

    @Test
    public void testCreate(){
        //Given
        Post post = new Post("New Post", null, null);

        PostRepo mockRepo = mock(PostRepo.class);

        String expectedPostTitle = "Post post post";
        Post postToReturn = new Post("Post",null,null);
        postToReturn.setTitle(expectedPostTitle);

        when(mockRepo.save(post)).thenReturn(postToReturn);

        PostService service = new PostService(mockRepo);

        Post actual = service.create(post);

        Assert.assertEquals(expectedPostTitle, actual.getTitle());


    }


    @Test
    public void testUpdate(){

        Post post = new Post("New Post",null,null);

        PostRepo mockRepo = mock(PostRepo.class);

        PostService service = new PostService(mockRepo);

        service.update(post);

        verify(mockRepo).save(post);
    }

    @Test
    public void testDelete(){
        Post post = new Post("New Post", null,null);
        PostRepo mockRepo = mock(PostRepo.class);
        long expectedId = 1;

        PostService service = new PostService(mockRepo);

        Boolean actual = service.delete(expectedId);

        Assert.assertTrue(actual);

        verify(mockRepo).deleteById(expectedId);


     }

    @Test
    public void testFindByUserOrderedByDate(){
         User user = new User(null,null,"BaileyBear","Bailey",null);

         PostRepo mockRepo = mock(PostRepo.class);
         PostService service = new PostService(mockRepo);
         service.findByUserOrderedByDate(user,1 );

         verify(mockRepo).findByUserOrderByCreateDateDesc(user, PageRequest.of(service.subtractPageByOne(1), 5));
        }

    @Test
    public void testFindAllOrderByDate(){
        Post post = new Post("Post",null,null);
        List<Post> list= new ArrayList<>();
        list.add(post);

        PostRepo mockRepo = mock(PostRepo.class);

        when(mockRepo.findByOrderByCreateDateDesc()).thenReturn(list);

        PostService service = new PostService(mockRepo);

        List<Post> actual = service.findAllOrderByDate();

        Assert.assertEquals(list, actual);

    }




}
