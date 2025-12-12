package com.app.ecom.service;

import com.app.ecom.dto.UserDto;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.convertor.UserConvertor;
import com.app.ecom.dto.convertor.UserDtoConvertor;
import com.app.ecom.model.User;
import com.app.ecom.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final UserDtoConvertor userDtoConvertor;

    private final UserConvertor userConvertor;

    public UserService(UserRepository userRepository, UserDtoConvertor userDtoConvertor, UserConvertor userConvertor) {
        this.userRepository = userRepository;
        this.userDtoConvertor = userDtoConvertor;
        this.userConvertor = userConvertor;
    }

    public List<UserDto> fetchAllUsers()
    {
        return  userRepository.findAll().stream().map(userDtoConvertor::convert).collect(Collectors.toList());
    }

    public UserDto fetchUser(String id)
    {

        return userDtoConvertor.convert(userRepository.findById(id).orElseThrow(()-> new RuntimeException("user couldn't found by id: " +id))) ;
    }

    public UserDto addUser(UserRequest userRequest){

       User user = userConvertor.convert(userRequest);
        userRepository.save(user);
        return userDtoConvertor.convert(user);
    }


    public UserDto UpdateUser(String id, UserRequest updatedUserRequest){

       User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("user couldn't found by id: " +id));

       userConvertor.convert(user,updatedUserRequest);
                userRepository.save(user);
                return userDtoConvertor.convert(user);
    }


}
