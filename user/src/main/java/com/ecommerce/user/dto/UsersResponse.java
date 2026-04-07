package com.ecommerce.user.dto;


import com.ecommerce.user.model.UsersRole;
import lombok.Data;

@Data
public class UsersResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UsersRole role;
    private AddressDTO address;
}
