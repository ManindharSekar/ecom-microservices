package com.ecom.app.service;

import com.ecom.app.model.Users;
import com.ecom.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<Users> getAllUsers(){
        return userRepository.findAll() ;
    }


    public Users CreateUsers(Users user){
        return userRepository.save(user);
    }


    public Optional<Users> fetchUser(Long id) {
        Optional<Users> user = userRepository.findById(id);
        return user;
    }

    public Boolean updateUser(Long id, Users user) {
    return userRepository.findById(id).map(existingUser->{existingUser.setFirstName(user.getFirstName());
    existingUser.setLastName(user.getLastName());
    userRepository.save(existingUser);
    return true;
    }).orElse(false) ;
        }
}
