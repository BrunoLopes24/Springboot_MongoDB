package com.blopes.springboot_mongodb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blopes.springboot_mongodb.domain.Post;
import com.blopes.springboot_mongodb.repository.PostRepository;
import com.blopes.springboot_mongodb.services.exception.ObjectNotFoundException;

@Service
public class PostServices {
    
    @Autowired
    private PostRepository repo;
    
    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
