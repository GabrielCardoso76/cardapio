package com.zatas.cardapio.Controller;

import com.zatas.cardapio.entity.User;
import com.zatas.cardapio.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;


    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        Optional<User> user = userService.getById(id);
        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/update/{id}")
    public Optional<User> update(@PathVariable Long id , @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }
}
