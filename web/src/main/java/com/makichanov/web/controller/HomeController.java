package com.makichanov.web.controller;

import com.makichanov.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private UserService userService;

}
