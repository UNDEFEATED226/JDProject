package com.example.demo.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
@Slf4j
public class IndexController {
    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model m) {
        return "index";
    }
}
