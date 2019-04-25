package com.zipcode.wilmington.zipzapzopblog.controller;

import com.zipcode.wilmington.zipzapzopblog.model.Post;
import com.zipcode.wilmington.zipzapzopblog.model.Tag;
import com.zipcode.wilmington.zipzapzopblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    @PostMapping("/tags")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag){
        return new ResponseEntity<>(tagService.createTag(tag), HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable Long id){
        return new ResponseEntity<>(tagService.getTag(id), HttpStatus.OK);
    }

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getAllTags(){
       return new ResponseEntity<>(tagService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag tag){
        return new ResponseEntity<>(tagService.update(tag), HttpStatus.OK);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<Boolean> deleteTag(@PathVariable Long id){
        tagService.delete(id);
        return new ResponseEntity(true, HttpStatus.OK);
    }



}
