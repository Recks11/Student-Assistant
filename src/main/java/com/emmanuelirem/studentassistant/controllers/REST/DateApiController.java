package com.emmanuelirem.studentassistant.controllers.REST;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/Api/Date")
public class DateApiController {

    @RequestMapping("/getCurrentDate")
    public String getDate(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");



        return formatter.format(date);
    }
}
