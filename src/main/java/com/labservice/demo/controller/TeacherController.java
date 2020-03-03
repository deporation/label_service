package com.labservice.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @RequestMapping("/signschool")
    public String ppl() {
        return "redirect:/school/signSchool";
    }

    @RequestMapping("/signlabel")
    public String label() {
        return "redirect:/label/signLabel";
    }

    @RequestMapping("/checkProject")
    public String project() {
        return "redirect:/projectinfo/checkProject";
    }
    @RequestMapping("/tea")
    public String rectea(){
        String str = "/teacher/tea";
        return "redirect:/recordtime/tea";
    }
}