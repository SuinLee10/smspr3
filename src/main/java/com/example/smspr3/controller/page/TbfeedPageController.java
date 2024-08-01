package com.example.smspr3.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbfeed")
@Controller
public class TbfeedPageController {
    @GetMapping("/{page}")
    public String  page(@PathVariable String page){
        return "tbfeed/" + page;
    }
    @GetMapping("/{page}/{id}")
    public String page(@PathVariable String page, @PathVariable String id){
      return "tbfeed/" + page;
    }
}
