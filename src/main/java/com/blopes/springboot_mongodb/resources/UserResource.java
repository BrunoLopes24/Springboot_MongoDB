package com.blopes.springboot_mongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blopes.springboot_mongodb.domain.Post;
import com.blopes.springboot_mongodb.domain.User;
import com.blopes.springboot_mongodb.dto.UserDTO;
import com.blopes.springboot_mongodb.services.UserServices;


@RestController
@RequestMapping(value="/users")
public class UserResource {
    
    @Autowired
    private UserServices services;
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = services.findAll();
        List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(listDTO);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        User obj = services.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }
    
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
        User obj = services.fromDTO(objDto);
        obj = services.insert(obj);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        services.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@RequestBody UserDTO objDto, @PathVariable String id){
        User obj = services.fromDTO(objDto);
        obj.setId(id); // Id da requisição
        
        obj = services.updateById(obj); 
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id){
        User obj = services.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }
}
