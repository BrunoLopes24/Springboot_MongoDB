package com.blopes.springboot_mongodb.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blopes.springboot_mongodb.domain.Post;
import com.blopes.springboot_mongodb.services.PostServices;


@RestController
@RequestMapping(value="/posts")
public class PostResource {
    
    @Autowired
    private PostServices services;
    
    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post obj = services.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
}
