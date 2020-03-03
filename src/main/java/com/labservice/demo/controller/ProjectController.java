package com.labservice.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.labservice.demo.entity.Label;
import com.labservice.demo.entity.People;
import com.labservice.demo.entity.Project;
import com.labservice.demo.service.impl.LabelServiceImpl;
import com.labservice.demo.service.impl.PeopleServiceImpl;
import com.labservice.demo.service.impl.ProjectServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;
    @Autowired
    private PeopleServiceImpl peopleServiceImpl;
    @Autowired
    private LabelServiceImpl labelServiceImpl;
    
    @RequestMapping("/")
    public String root(HttpSession httpSession) {
        try {
            People people = (People) httpSession.getAttribute("people");

            if (people == null) {
                return "redirect:/";
            } else if (people.getPlimit() == 1)
                return "redirect:/project/checkProject";
            else
                return "redirect:/project";
        } catch (NullPointerException exception) {
            return "redirect:/";
        }
    }

    @RequestMapping("/signProject")
    public String sign(HttpSession httpSession, Model model) {
        People student = (People) httpSession.getAttribute("people");
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();
        Map<String, String> params = new HashMap<>();
        params.put("scid", student.getScid().toString());
        params.put("plimit", "2");
        List<People> teachers = peopleServiceImpl.list(new QueryWrapper<People>().allEq(params));
        List<Label> labels = labelServiceImpl.list(queryWrapper.eq("scid", student.getScid()));
        model.addAttribute("labels", labels);
        model.addAttribute("teachers", teachers);
        return "form-Project";
    }

    @RequestMapping("/signProject.redo")
    public String signre(Model model) {
        String attributeValue = "该项目已经被注册";
        model.addAttribute("signProjectError", attributeValue);
        return "redirect:/project/signProject";
    }

    @RequestMapping(value = "signProject.action", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, String> signProject(@RequestBody Map<String, String> project, HttpSession httpSession) {
        System.out.println(project);
        Map<String, String> map = new HashMap<String, String>();
        boolean res = false;
        map.put("res", res == true ? "1" : "0");
        try {
            People student = (People) httpSession.getAttribute("people");
            QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("pnum", project.get("pnum"));
            if (projectServiceImpl.getOne(queryWrapper) != null) {
                map.put("res", res == true ? "1" : "0");
                return map;
            }
            QueryWrapper<People> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("num", project.get("teanum")).eq("scid", student.getScid());
            People teacher = (People) peopleServiceImpl.getObj(queryWrapper2);
            int lid = Integer.parseInt(project.get("label"));
            Project signProject = Project.builder().pnum(project.get("pnum")).proname(project.get("pname"))
                    .sid(student.getPid()).tid(teacher.getPid()).lid(lid).status(false).build();
            res = projectServiceImpl.save(signProject);
            map.put("res", res == true ? "1" : "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
