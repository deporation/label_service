package com.labservice.demo.controller;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.labservice.demo.entity.People;
import com.labservice.demo.entity.Peopleinfo;
import com.labservice.demo.mapper.PeopleinfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author deporation
 * @since 2019-06-08
 */
@Controller
@RequestMapping("/peopleinfo")
public class PeopleinfoController {
    @Autowired
    private PeopleinfoMapper peopleinfoMapper;

    @RequestMapping("/updateSelfInformation")
    public String updateSelfInformation(HttpSession session) {
        People people = (People) session.getAttribute("people");
        if (people == null) {
            return "redirect:/";
        }
        System.out.println(people);
        QueryWrapper<Peopleinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", people.getPid());
        Peopleinfo peopleinfo = (Peopleinfo) peopleinfoMapper.selectOne(queryWrapper);
        System.out.println(peopleinfo);
        session.setAttribute("peopleall", peopleinfo);
        return "form-line";
    }
}
