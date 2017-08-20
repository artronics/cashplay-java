package com.artronics.controller;

import com.google.gson.Gson;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
public class ApiControllerTest {
    @Autowired
    protected MockMvc mvc;

    protected Gson gson = new Gson();

}