package com.labservice.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.labservice.demo.entity.People;
import com.labservice.demo.mapper.PeopleMapper;
import com.labservice.demo.service.impl.PeopleServiceImpl;

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
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleServiceImpl peopleServiceImpl;

    @RequestMapping("/login")
    public String Login() {
        return "login";
    }

    @RequestMapping(value = "/login.action", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, String> selectTest(@RequestBody People people, HttpSession session) {
        Map<String, String> map = new HashMap<String, String>();
        boolean res = false;
        try {
            People peo = peopleServiceImpl.getOne(new QueryWrapper<People>().eq("account", people.getAccount()));
            if (people.getPasswd().equals(peo.getPasswd())) {
                res = true;
                System.out.println(peo);
                session.setAttribute("people", peo);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        map.put("res", res == true ? "1" : "0");
        return map;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("people");
        return "redirect:/";
    }

    @RequestMapping("/signin")
    public String singin() {
        return "signin";
    }

    @RequestMapping(value = "signin.action", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, String> dosignin(@RequestBody People people, HttpSession httpSession) {
        Map<String, String> map = new HashMap<String, String>();
        boolean res = false;
        try {
            People people2 = peopleServiceImpl.getOne(new QueryWrapper<People>().eq("account", people.getAccount()));
            if (people2 == null) {
                people.setPlimit(1);
                res = peopleServiceImpl.save(people);
                map.put("res", res == true ? "1" : "0");
                return map;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(map);
        return map;
    }
}