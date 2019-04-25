package com.zipcode.wilmington.zipzapzopblog.service;

import com.zipcode.wilmington.zipzapzopblog.model.Post;
import com.zipcode.wilmington.zipzapzopblog.model.Tag;
import com.zipcode.wilmington.zipzapzopblog.repository.PostRepo;
import com.zipcode.wilmington.zipzapzopblog.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepo tagRepo;
    private final PostRepo postRepo;

    @Autowired
    public TagService(TagRepo tagRepo, PostRepo postRepo) {
        this.tagRepo = tagRepo;
        this.postRepo = postRepo;
    }

    public Tag getTag(Long id){
        return tagRepo.findById(id).orElse(null);
    }

    public Tag createTag(Tag tag) {
        return tagRepo.save(tag);
    }

    public List findAll() {

        List<Tag> list = new ArrayList();
        tagRepo.findAll().forEach(tag -> list.add(tag));
        return list;
    }

    public void delete(Long id){
        tagRepo.deleteById(id);
    }

    public Optional<Post> findPost(Long postId) {
       return postRepo.findById(postId);
    }

    public Tag update(Tag tag){
        Tag original = tagRepo.findById(tag.getId()).get();
        original.setKeyWord(tag.getKeyWord());
        original.setPosts(tag.getPosts());

        return tagRepo.save(original);
    }
}
