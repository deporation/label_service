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
        return "redirect:/ppl/signppl";
    }
    @RequestMapping("/signproject")
    public String project() {
        return "redirect:/project/signProject";
    }
    @RequestMapping("/agree")
    public String pplc() {
        return "redirect:/ppl/agree";
    }
}