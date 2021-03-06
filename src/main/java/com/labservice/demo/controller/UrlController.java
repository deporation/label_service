package com.labservice.demo.controller;

import javax.servlet.http.HttpSession;

import com.labservice.demo.entity.People;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UrlController {
    @RequestMapping("/")
    public ModelAndView checkindex(HttpSession session) {
        String[] strs = session.getValueNames();
        for (String str : strs) {
            if (str.equals("people")) {
                return new ModelAndView("redirect:/index");
            }
        }
        return new ModelAndView("redirect:/people/login");
    }

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }
    @RequestMapping("select")
    private String name() {
        return "redirect:/recordtime/record.action?proname=&pname=&pagenum=1";
    }
}