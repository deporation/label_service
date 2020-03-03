package com.labservice.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.labservice.demo.entity.People;
import com.labservice.demo.entity.Ppl;
import com.labservice.demo.entity.Project;
import com.labservice.demo.entity.Projectinfo;
import com.labservice.demo.service.impl.PplServiceImpl;
import com.labservice.demo.service.impl.ProjectServiceImpl;
import com.labservice.demo.service.impl.ProjectinfoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author deporation
 * @since 2019-06-10
 */
@Controller
@RequestMapping("/projectinfo")
public class ProjectinfoController {

    @Autowired
    private ProjectinfoServiceImpl projectinfoServiceImpl;
    @Autowired
    private ProjectServiceImpl projectServiceImpl;
    @Autowired
    private PplServiceImpl pplServiceImpl;

    @RequestMapping("/ppllist")
    public String listppl(HttpSession httpSession) {
        try {
            People people = (People) httpSession.getAttribute("people");

            if (people.getPlimit() != 2 || people == null) {
                return "redirect:/";
            } else {
                QueryWrapper<Projectinfo> wrapper = new QueryWrapper<>();
                wrapper.eq("scid", people.getScid());
                List<Projectinfo> proinfo = projectinfoServiceImpl.list(wrapper);
                httpSession.setAttribute("ProjectInfo", proinfo);
                return "list_ppl";
            }
        } catch (NullPointerException exception) {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "checkProject")
    public String Check(Model model, HttpSession httpSession) {
        People teacher = (People) httpSession.getAttribute("people");
        if (teacher.getPlimit() == 2) {
            QueryWrapper<Projectinfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tid", teacher.getPid()).eq("status", false);
            List<Projectinfo> projectinfos = projectinfoServiceImpl.list(queryWrapper);
            model.addAttribute("projectinfos", projectinfos);
            System.out.println(projectinfos);
        }

        return "form-checkPro";
    }

    @RequestMapping(value = "updateProjectsta.action")
    @ResponseBody
    public Map<String, String> updateProject(@RequestBody List<String> pnumList) {
        boolean res = false;
        System.out.println(pnumList);
        Map<String, String> map = new HashMap<>();
        Project entity = Project.builder().status(true).build();
        UpdateWrapper<Project> updateWrapper = new UpdateWrapper<>();
        updateWrapper.in("pnum", pnumList);
        projectServiceImpl.update(entity, updateWrapper);
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("pnum", pnumList);
        List<Project> projects = projectServiceImpl.list(queryWrapper);
        List<Ppl> ppls = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            Ppl ppl = Ppl.builder().lid(projects.get(i).getLid()).pid(projects.get(i).getSid())
                    .pnum(projects.get(i).getPnum()).stat(false).build();
            ppls.add(ppl);
        }
        res = pplServiceImpl.saveBatch(ppls);
        map.put("res", res == true ? "1" : "0");
        return map;
    }
}