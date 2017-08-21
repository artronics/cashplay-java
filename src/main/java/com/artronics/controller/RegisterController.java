package com.artronics.controller;

import com.artronics.model.Account;
import com.artronics.model.User;
import com.artronics.repository.AccountRepository;
import com.artronics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(path = "/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Account account = accountRepository.save(user.getAccount());
        user.setAccount(account);
        user = userRepository.save(user);

        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
}
