package com.artronics.controller;

import com.artronics.model.User;
import com.artronics.service.UserService;
import com.artronics.service.UserServiceImpl;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;

    @Test
    public void it_should_create_user_in_POST_request() throws Exception {
        User user = new User("foo");

        // should be any otherwise will return null
        given(userService.create(any(User.class))).willReturn(user);

        Gson gson = new Gson();
        mvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andDo(print());

    }
}