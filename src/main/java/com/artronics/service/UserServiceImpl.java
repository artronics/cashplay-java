package com.artronics.service;

import com.artronics.model.User;
import com.artronics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repo;

    public User create(User user) {
        return repo.save(user);
    }
}
