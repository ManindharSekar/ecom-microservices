package com.ecom.app.controller;

import com.ecom.app.dto.UsersRequest;
import com.ecom.app.dto.UsersResponse;
import com.ecom.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UsersResponse>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public String CreateUsers(@RequestBody UsersRequest usersRequest){
        userService.CreateUsers(usersRequest);
        return "User added successfully";
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponse> getUser(@PathVariable Long id){

        return userService.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UsersRequest usersRequest){
        Boolean updated = userService.updateUser(id,usersRequest);
        if(updated)
            return ResponseEntity.ok("User Updatred successfully");
            return ResponseEntity.notFound().build();
    }

}
