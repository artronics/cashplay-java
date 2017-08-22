package com.artronics;

import com.artronics.model.Account;
import com.artronics.model.Customer;
import com.artronics.model.User;
import com.artronics.repository.AccountRepository;
import com.artronics.repository.CustomerRepository;
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
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        account1();
        account2();
    }

    private void account1() {
        Account account = accountRepository.save(new Account("cashconversions"));
        User user = new User();
        user.setName("jalal");
        user.setEmail("jalalhosseiny@gmail.com");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("secret"));
        user.setAccount(account);
        userRepository.save(user);

        Customer c1 = new Customer(account, "Jalal", "Hosseini");
        Customer c2 = new Customer(account, "magid", "sarhangi");
        Customer c3 = new Customer(account, "Reza", "Hosseini");
        Customer c4 = new Customer(account, "Ali", "Moghadasian");
        Customer c5 = new Customer(account, "Nazanin", "Keshmiri");
        Customer c6 = new Customer(account, "Akram", "Sabzalipour");

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);
        customerRepository.save(c4);
        customerRepository.save(c5);
        customerRepository.save(c6);
    }

    private void account2() {
        Account account = accountRepository.save(new Account("foo"));
        User user = new User();
        user.setName("foo");
        user.setEmail("foo@bar.com");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode("secret"));
        user.setAccount(account);
        userRepository.save(user);

        Customer c1 = new Customer(account, "kir", "kiri");
        Customer c2 = new Customer(account, "gholi", "ghilian");
        Customer c3 = new Customer(account, "kos", "kosmashang");
        Customer c4 = new Customer(account, "jafang", "jagana");
        Customer c5 = new Customer(account, "ajdar", "jeefar");
        Customer c6 = new Customer(account, "ak", "gholian");

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);
        customerRepository.save(c4);
        customerRepository.save(c5);
        customerRepository.save(c6);
    }
}
