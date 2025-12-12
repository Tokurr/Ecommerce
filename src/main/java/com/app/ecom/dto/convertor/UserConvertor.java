package com.app.ecom.dto.convertor;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.model.Address;
import com.app.ecom.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {

    public User convert(UserRequest req) {

        User user = new User();
        user.setFirstName(req.firstName());
        user.setLastName(req.lastName());
        user.setEmail(req.email());
        user.setPhone(req.phone());

        if (req.address() != null) {
            Address address = new Address();
            address.setStreet(req.address().getStreet());
            address.setCity(req.address().getCity());
            address.setState(req.address().getState());
            address.setCountry(req.address().getCountry());
            address.setZipcode(req.address().getZipcode());

            user.setAddress(address);
        }

        return user;
    }
    public void convert(User user, UserRequest req) {

        user.setFirstName(req.firstName());
        user.setLastName(req.lastName());
        user.setEmail(req.email());
        user.setPhone(req.phone());

        if (req.address() != null) {

            if (user.getAddress() == null) {
                user.setAddress(new Address());
            }

            Address a = user.getAddress();
            a.setStreet(req.address().getStreet());
            a.setCity(req.address().getCity());
            a.setState(req.address().getState());
            a.setCountry(req.address().getCountry());
            a.setZipcode(req.address().getZipcode());
        }
    }
}
