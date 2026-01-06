package com.ecom.app.dto;

import com.ecom.app.model.UsersRole;
import lombok.Data;

@Data
public class UsersRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UsersRole role;
    private AddressDTO address;
}
