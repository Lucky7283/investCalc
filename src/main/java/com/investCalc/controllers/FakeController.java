package com.investCalc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/*
This is the controller what handle whole not valid urls and show to users the main page button for returning the main page
for example if user enter /page/main/something user will see the button for going to main page of site.
 */
@Controller
public class FakeController {
    @GetMapping("/**")
    public String handleAll(Model model) {
        return "error";
    }

}
