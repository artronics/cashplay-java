package com.artronics.controller;

import com.artronics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
public class UserController extends ApiController{
    @Autowired
    private UserService userService;

//    @RequestMapping(method = RequestMethod.GET)
//    public User user() {
//        return new User("Jalal");
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<User> user(@RequestBody User user) {
//        user = userService.create(user);
//
//        return new ResponseEntity<User>(user, HttpStatus.CREATED);
//    }
}
