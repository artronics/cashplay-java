package com.artronics.service;

import com.artronics.model.User;
import com.artronics.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        User user = new User("foo");
        user.setId(1L);

        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);
    }

    @Test
    public void it_should_create_user() throws Exception {
        User user = userService.create(new User());
        assertEquals(new Long(1), user.getId());
    }
}