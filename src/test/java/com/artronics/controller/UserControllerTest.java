package com.artronics.controller;

import com.artronics.model.User;
import com.artronics.service.UserService;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest extends ApiControllerTest {
    @MockBean
    private UserService userService;

    @Test
    public void it_should_create_user_in_POST_request() throws Exception {
        User user = new User("foo");

        // should be any otherwise will return null
        given(userService.create(any(User.class))).willReturn(user);

        mvc.perform(post("/api/users")
        .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(user)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andDo(print());
    }
}