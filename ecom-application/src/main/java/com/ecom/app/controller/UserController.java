package com.ecom.app.controller;

import com.ecom.app.service.UserService;
import com.ecom.app.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    Long id=1l;


    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public String CreateUsers(@RequestBody Users user){
        userService.CreateUsers(user);
        return "User added successfully";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id){

        Optional<Users> user = userService.fetchUser(id);
        return new ResponseEntity(user, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody Users user){
        Boolean updated = userService.updateUser(id,user);
        if(updated)
            return ResponseEntity.ok("User Updatred successfully");
            return ResponseEntity.notFound().build();
    }

}
