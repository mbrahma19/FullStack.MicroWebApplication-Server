package com.zipcode.wilmington.zipzapzopblog.service;

import com.zipcode.wilmington.zipzapzopblog.model.Post;
import com.zipcode.wilmington.zipzapzopblog.model.User;
import com.zipcode.wilmington.zipzapzopblog.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepo postRepo;

    @Autowired
    public PostService(PostRepo postRepo){this.postRepo = postRepo;}

    public Optional<Post> show(Long id) { return postRepo.findById(id);}

    public Post create(Post post){
        return postRepo.save(post);
    }

    public Post update(Post post){
        return postRepo.save(post);
    }

    public Boolean delete(Long id){
        postRepo.deleteById(id);
        return true;
    }

    public Page<Post>findByUserOrderedByDate(User user, int page){
        return postRepo.findByUserOrderByCreateDateDesc(user, PageRequest.of(subtractPageByOne(page), 5));
    }

    public List<Post> findAllOrderByDate(){
        return postRepo.findByOrderByCreateDateDesc();
    }

    protected int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
}
