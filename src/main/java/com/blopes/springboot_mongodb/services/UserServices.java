package com.blopes.springboot_mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blopes.springboot_mongodb.domain.User;
import com.blopes.springboot_mongodb.dto.UserDTO;
import com.blopes.springboot_mongodb.repository.UserRepository;
import com.blopes.springboot_mongodb.services.exception.ObjectNotFoundException;

@Service
public class UserServices {
    
    @Autowired
    private UserRepository repo;
    
    public List<User> findAll(){
        return repo.findAll();
    }
    
    public User findById(String id) {
        Optional<User> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
    
    public User insert(User obj){
        return repo.insert(obj);
    }
    
    public User fromDTO(UserDTO objDTO){
        return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
    }
    
    public void deleteById(String id){
        findById(id);
        repo.deleteById(id);
    }
    
    public User updateById(User obj){
        User newObj = repo.findById(obj.getId()).get();
        updateData(newObj, obj);
        return repo.save(newObj);
    }
    
    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }
}
