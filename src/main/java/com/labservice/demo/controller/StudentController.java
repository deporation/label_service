package com.labservice.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author deporation
 * @since 2019-06-01
 */

@Controller
@RequestMapping("/student")
public class StudentController {

    @RequestMapping("/signppl")
    public String ppl() {
        return "redirect:/ppl/signPpl";
    }
    @RequestMapping("/signproject")
    public String project() {
        return "redirect:/project/signProject";
    }
    @RequestMapping("/agree")
    public String pplc() {
        return "redirect:/ppl/agree";
    }
    @RequestMapping("/recordself")
    public String recself(){
        String str = "/student/recordself";
        return "redirect:/recordtime/stuself";
    }
    @RequestMapping()
    public String recteam(){
        return "redirect:/recordtime/stulea";
    }
    @RequestMapping()
    public String rectea(){
        return "redirect:/recordtime/tea";
    }
}