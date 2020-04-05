package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class text {
    @GetMapping
    public void sadf(){
        MailUtil.sendEmail();
        System.out.println("阿斯蒂芬");
    }

}
