package com.zipcode.wilmington.zipzapzopblog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    Long id;
    @Column(name = "body", columnDefinition = "TEXT")
    @NotEmpty(message = "*Please write something")
    private String body;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date creationDate;
    @ManyToOne

    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = true)
    //@NotNull
    private Post post;

    @ManyToOne

    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = true)
    //@NotNull
    private User user;


    public Comment() {

    }



    public Comment (String body, Date creationDate) {
        this.body = body;
        this.creationDate = creationDate;


    }


    public Comment (Long id, String body, Date creationDate, Post post, User user) {
        this.id=id;
        this.body = body;
        this.creationDate = creationDate;
        this.post=post;
        this.user=user;


    }



    public Comment (String body, Date creationDate, Post post) {
        this.id=id;
        this.body = body;
        this.creationDate = creationDate;
        this.post=post;

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", Body='" + this.body + '\'' +
                ", CreateDate=" + this.creationDate +
                '}';
    }
}
