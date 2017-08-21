package com.artronics;

import com.artronics.model.Account;
import com.artronics.model.User;
import com.artronics.repository.AccountRepository;
import com.artronics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Account account = accountRepository.save(new Account("cashconversions"));
        User user = new User();
        user.setName("jalal");
        user.setEmail("jalalhosseiny@gmail.com");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("secret"));
        user.setAccount(account);
        userRepository.save(user);
    }
}
