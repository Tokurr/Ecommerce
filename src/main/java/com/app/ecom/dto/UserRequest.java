package com.app.ecom.dto;

import com.app.ecom.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;




public record UserRequest(String firstName,
                          String lastName,
                          String email,
                          String phone,
                          AddressDto address) {

}
