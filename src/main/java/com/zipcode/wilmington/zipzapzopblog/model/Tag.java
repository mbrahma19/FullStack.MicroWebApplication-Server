package com.zipcode.wilmington.zipzapzopblog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", unique = false, nullable = false)
    private Long id;

    @Column(name = "key_word",unique = false, nullable = false)
    private String keyWord;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "tags")
    private Collection<Post> posts;

    public Tag(){
       this("");
    }

    public Tag(String keyWord) {
        this.keyWord = keyWord;
        this.posts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        if(posts!=null){
            this.posts = posts;
        }
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", keyWord='" + keyWord + '\'' +
                ", posts=" + posts +
                '}';
    }
}
