package com.app.ecom.dto.convertor;

import com.app.ecom.dto.AddressDto;
import com.app.ecom.dto.UserDto;
import com.app.ecom.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConvertor {

    public UserDto convert(User user)
    {
        UserDto response = new UserDto();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());
        response.setEmail(user.getEmail());

        if(user.getAddress()!=null)
        {
            AddressDto addressDto = new AddressDto();
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setZipcode(user.getAddress().getZipcode());
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setId(user.getAddress().getId());
            addressDto.setState(user.getAddress().getState());
            addressDto.setCountry(user.getAddress().getCountry());
            response.setAddress(addressDto);

        }
        return response;
    }

}
