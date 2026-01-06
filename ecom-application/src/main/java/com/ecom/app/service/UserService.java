package com.ecom.app.service;

import com.ecom.app.dto.AddressDTO;
import com.ecom.app.dto.UsersRequest;
import com.ecom.app.dto.UsersResponse;
import com.ecom.app.model.Address;
import com.ecom.app.model.Users;
import com.ecom.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<UsersResponse> getAllUsers(){

        return userRepository.findAll().stream().map(this::mapToUserResponse).collect(Collectors.toList()) ;
    }


    public void CreateUsers(UsersRequest usersRequest){

        Users users = new Users();
        updateUserFromRequest(users, usersRequest);
        userRepository.save(users);
    }

    private void updateUserFromRequest(Users users, UsersRequest usersRequest) {
        users.setFirstName(usersRequest.getFirstName());
        users.setLastName(usersRequest.getLastName());
        users.setEmail(usersRequest.getEmail());
        users.setPhone(usersRequest.getPhone());
        if(usersRequest.getAddress()!=null){
            Address address = new Address();
            address.setStreet(usersRequest.getAddress().getStreet());
            address.setCity(usersRequest.getAddress().getCity());
            address.setState(usersRequest.getAddress().getState());
            address.setCountry(usersRequest.getAddress().getCountry());
            address.setZipCode(usersRequest.getAddress().getZipCode());
            users.setAddress(address);
        }

    }

    public Optional<UsersResponse> fetchUser(Long id) {
        return userRepository.findById(id).map(this::mapToUserResponse);
    }

    public Boolean updateUser(Long id, UsersRequest usersRequest) {
    return userRepository.findById(id).map(existingUser->{updateUserFromRequest(existingUser,usersRequest);
    userRepository.save(existingUser);
    return true;
    }).orElse(false) ;
        }


        private UsersResponse mapToUserResponse(Users user){
            UsersResponse response = new UsersResponse();
            response.setId(String.valueOf(user.getId()));
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
            response.setEmail(user.getEmail());
            response.setPhone(user.getPhone());
            response.setRole(user.getRole());
            if(user.getAddress()!=null){
                AddressDTO addressDTO = new AddressDTO();
                addressDTO.setStreet(user.getAddress().getStreet());
                addressDTO.setCity(user.getAddress().getCity());
                addressDTO.setCountry(user.getAddress().getCountry());
                addressDTO.setZipCode(user.getAddress().getZipCode());
                response.setAddress(addressDTO);
            }
            return response;
        }
}
