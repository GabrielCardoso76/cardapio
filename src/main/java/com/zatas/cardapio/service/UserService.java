package com.zatas.cardapio.service;

import com.zatas.cardapio.entity.User;
import com.zatas.cardapio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public Optional<User> updateUser(Long id, User user){
        return userRepository.findById(id).map(userDb ->{
            userDb.setNome(user.getNome());
            userDb.setEmail(user.getEmail());
            return userRepository.save(userDb);
        });
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
