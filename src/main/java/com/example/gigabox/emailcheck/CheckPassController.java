package com.example.gigabox.emailcheck;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckPassController {
    @GetMapping("/checkpass")
    public String checkpass(){
        return "checkpass";
    }
}
