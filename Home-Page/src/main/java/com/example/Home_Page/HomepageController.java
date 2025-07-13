package com.example.Home_Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

    @GetMapping("HomePage")
    public String Home(Model model) {
        model.addAttribute("data", "hello!");
        return "HomePage";  // templates/HomePage.html 로 이동!
    }
}
