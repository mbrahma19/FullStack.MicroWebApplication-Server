package com.zipcode.wilmington.zipzapzopblog.controller;

import com.zipcode.wilmington.zipzapzopblog.model.Comment;
import com.zipcode.wilmington.zipzapzopblog.model.Post;
import com.zipcode.wilmington.zipzapzopblog.model.User;
import com.zipcode.wilmington.zipzapzopblog.service.CommentService;
import com.zipcode.wilmington.zipzapzopblog.service.PostService;
import com.zipcode.wilmington.zipzapzopblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    private CommentService commentService;
    private UserService userservice;
    private PostService postservice;


    @Autowired
    public CommentController(CommentService commentService, UserService userService, PostService postService) {
        this.commentService = commentService;
        this.userservice = userService;
        this.postservice = postService;
    }



    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
    return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.CREATED);
    }

//Comment the Post by Post Id

    @PostMapping ("/commentMyPost/{id}")
    public ResponseEntity<Comment> createCommentonPost(@RequestBody Comment comment, @PathVariable Long id) {

        Optional<Post> post = postservice.show(id);

        comment.setPost(post.get());


        if (post.isPresent()) {

                comment.setPost(post.get());

            }

        return new ResponseEntity<>(commentService.createComment(comment), HttpStatus.CREATED);

    }





    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        return new ResponseEntity<Comment>(commentService.show(id), HttpStatus.OK);
    }

    @GetMapping("/comments")
    public ResponseEntity<Iterable<Comment>> getAllComments() {

        return new ResponseEntity<Iterable<Comment>>(commentService.index(), HttpStatus.OK);

    }



    @GetMapping("commentsByPostId//{id}")
    public ResponseEntity<List<Comment>> getAllCommentsByPost(@PathVariable Long id) {

        return new ResponseEntity<List<Comment>>(commentService.findCommentbyPostId(id), HttpStatus.OK);

    }




    @GetMapping("/commentsByDate/{date}")
    public ResponseEntity<List<Comment>> getAllCommentsByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {



        return new ResponseEntity<List<Comment>>(commentService.findCommentbyCreationDate(date), HttpStatus.OK);

    }


    @DeleteMapping("/deletecomment/{id}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.delete(id), HttpStatus.OK);
    }


    @PutMapping("/comment/{id}")
    public ResponseEntity<Comment> update(@PathVariable Long id, @RequestBody Comment comment) {

        return new ResponseEntity<>(commentService.edit(id, comment), HttpStatus.OK);
    }



//    @PostMapping("/createComment")
//    public String createNewPost(@Valid Comment comment, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "/commentForm";
//
//        } else {
//            commentService.createComment(comment);
//            return "redirect:/post/" + comment.getPost().getId();
//        }
//    }
//
//

//    @GetMapping ("/commentPost/{id}")
//    public String commentPostWithId(@PathVariable Long id,
//                                    Principal principal,
//                                    Model model) {
//
//        Optional<Post> post = postservice.show(id);
//
//        if (post.isPresent()) {
//            Optional<User> user = userservice.findByUsername(principal.getName());
//
//            if (user.isPresent()) {
//                Comment comment = new Comment();
//                //comment.setUser(user.get());
//                comment.setPost(post.get());
//
//                model.addAttribute("comment", comment);
//
//                return "/commentForm";
//
//            } else {
//                return "/error";
//            }
//
//        } else {
//            return "/error";
//        }
//    }
}
