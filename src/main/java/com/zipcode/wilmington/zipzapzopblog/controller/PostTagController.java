package com.zipcode.wilmington.zipzapzopblog.controller;

import com.zipcode.wilmington.zipzapzopblog.model.Post;
import com.zipcode.wilmington.zipzapzopblog.model.Tag;
import com.zipcode.wilmington.zipzapzopblog.service.PostService;
import com.zipcode.wilmington.zipzapzopblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PostTagController {

    private TagService tagService;

    private PostService postService;

    @Autowired
    public PostTagController(TagService tagService, PostService postService) {
        this.tagService = tagService;
        this.postService = postService;
    }

    @GetMapping("/posts/{postId}/tags")
    public ResponseEntity<Collection<Tag>> getAllTagsOnPost(@PathVariable Long postId){
        Optional<Post> post = tagService.findPost(postId);
        Collection<Tag> tagsOnPost = post.get().getTags();

        return new ResponseEntity<>(tagsOnPost, HttpStatus.OK);
    }

    @GetMapping("/tags/{tagId}/posts")
    public ResponseEntity<Collection<Post>> getAllPostsByTag(@PathVariable Long tagId){
        Tag tag = tagService.getTag(tagId);
        Collection<Post> posts = tag.getPosts();

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PostMapping("/posts/{postId}/tags/{tagId}")
    public ResponseEntity<Boolean> updatePostTag(@PathVariable Long postId, @PathVariable Long tagId) {

        Optional<Post> post = tagService.findPost(postId);

        Tag tag = tagService.getTag(tagId);

        Collection<Tag> tags = new ArrayList<>();

        if (post.isPresent() && !post.get().getTags().contains(tag)) {
            tags.add(tag);
            tags.addAll(post.get().getTags());
            post.get().setTags(tags);
            postService.update(post.get());
           return new ResponseEntity<>(true, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/posts/{postId}/tags/{tagId}")
    public ResponseEntity<Boolean> deletePostTag(@PathVariable Long postId, @PathVariable Long tagId) {
        Optional<Post> post = tagService.findPost(postId);

        Tag tag = tagService.getTag(tagId);

        Collection<Tag> tags = new ArrayList<>();

        if (post.isPresent()) {

            tags.addAll(post.get().getTags());
            tags.remove(tag);
            post.get().setTags(tags);
            postService.update(post.get());
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

    }
}
