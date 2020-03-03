package com.labservice.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.labservice.demo.entity.People;
import com.labservice.demo.entity.School;
import com.labservice.demo.service.impl.SchoolServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author deporation
 * @since 2019-06-01
 */
@Controller
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolServiceImpl schoolServiceImpl;

    @RequestMapping("/signSchool")
    public String sign(HttpSession httpSession) {
        try {
            People people = (People) httpSession.getAttribute("people");

            if (people.getPlimit() != 2 || people == null) {
                return "redirect:/";
            } else
                return "form-school";
        } catch (NullPointerException exception) {
            return "redirect:/";
        }
    }

    /* @RequestMapping("/signSchool.redo")
    public String signre(HttpSession httpSession) {
        String value = "该学校已经被注册";
        if (httpSession.getAttribute("SchoolError") != null) {
            return "form-school";
        } else {
            httpSession.setAttribute("SchoolError", value);
            return "form-school";
        }

    } */

    @RequestMapping(value = "signSchool.action", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, String> sign(@RequestBody School school, HttpSession httpSession) {
        Map<String, String> map = new HashMap<String, String>();
        boolean res = false;
        try {
            QueryWrapper<School> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("scname", school.getScname());
            if (schoolServiceImpl.getOne(queryWrapper) == null) {
                if (httpSession.getAttribute("SchoolError") != null) {
                    httpSession.removeAttribute("SchoolError");
                }
                res = schoolServiceImpl.save(school);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("res", res == true ? "1" : "0");
        return map;
    }
}