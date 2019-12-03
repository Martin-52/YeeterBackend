package com.yeeter.web.YeeterWebBackend.controller;

import com.yeeter.web.YeeterWebBackend.service.YeetService;
import com.yeeter.web.YeeterWebBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YeetController {
    @Autowired
    YeetService yeetService;

    @Autowired
    UserService userService;
}