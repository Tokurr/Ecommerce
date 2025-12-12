package com.app.ecom.controller;

import com.app.ecom.dto.UserDto;
import com.app.ecom.dto.UserRequest;
import com.app.ecom.model.User;
import com.app.ecom.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>>  getAllUser()
    {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getUser(@PathVariable String id)
    {

        return ResponseEntity.ok(userService.fetchUser(id));

    }

    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest)
    {

        userService.addUser(userRequest);
        return  ResponseEntity.ok("user created");
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id, @RequestBody UserRequest updatedUser)
    {
       return ResponseEntity.ok(userService.UpdateUser(id,updatedUser));
    }

}
