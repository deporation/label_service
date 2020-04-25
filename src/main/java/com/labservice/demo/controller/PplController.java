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
import com.labservice.demo.entity.Pplinfo;
import com.labservice.demo.entity.Project;
import com.labservice.demo.entity.Projectinfo;
import com.labservice.demo.service.impl.PplServiceImpl;
import com.labservice.demo.service.impl.PplinfoServiceImpl;
import com.labservice.demo.service.impl.ProjectServiceImpl;
import com.labservice.demo.service.impl.ProjectinfoServiceImpl;

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
@RequestMapping("/ppl")
public class PplController {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @Autowired
    private PplServiceImpl pplServiceImpl;

    @Autowired
    private ProjectinfoServiceImpl projectinfoServiceImpl;

    @Autowired
    private PplinfoServiceImpl pplinfoServiceImpl;

    @RequestMapping("/signPpl")
    public String sign(HttpSession httpSession, Model model) {
        People stu = (People) httpSession.getAttribute("people");
        if (stu.getPlimit() != 2) {
            List<Projectinfo> projectinfos = projectinfoServiceImpl.list(new QueryWrapper<Projectinfo>()
                    .eq("scid", stu.getScid()).eq("status", true).ne("num", stu.getNum()));
            model.addAttribute("projectinfos", projectinfos);
        }
        return "form-join";
    }

    @RequestMapping(value = "signPpl.action", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, Object> sign(@RequestBody List<String> pnumList, HttpSession session) {
        List<Integer> nums = new ArrayList<>();
        Map<String, Object> n = new HashMap<>();
        People people = (People) session.getAttribute("people");
        boolean res = false;
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("pnum", pnumList);
        List<Project> projects = projectServiceImpl.list(queryWrapper);
        List<Ppl> ppls = new ArrayList<>();
        UpdateWrapper<Ppl> updateWrapper = new UpdateWrapper<>();
        for (int index = 0; index < projects.size(); index++) {
            Ppl ppl = Ppl.builder().lid(projects.get(index).getLid()).pid(people.getPid())
                    .pnum(projects.get(index).getPnum()).stat(false).build();
            updateWrapper.eq("pnum", projects.get(index).getPnum()).eq("pid", people.getPid()).eq("lid",
                    projects.get(index).getLid());
            if (pplServiceImpl.update(ppl, updateWrapper)) {
                nums.add(index + 2);
            } else
                ppls.add(ppl);
        }
        System.out.println(nums.isEmpty());
        res = pplServiceImpl.saveBatch(ppls);
        if (nums.isEmpty()) {
            map.put("res", res);
        } else {
            n.put("res", nums);
            return n;
        }
        return map;
    }

    @RequestMapping("/agree")
    public String agree(HttpSession session, Model model) {
        People stu = (People) session.getAttribute("people");
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sid", stu.getPid());
        List<Project> projects = projectServiceImpl.list(queryWrapper);
        List<String> pnums = new ArrayList<>();
        for (Project project : projects) {
            pnums.add(project.getPnum());
        }
        QueryWrapper<Pplinfo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.in("pnum", pnums).ne("pname", stu.getPname()).eq("stat", false);
        List<Pplinfo> pplinfos = pplinfoServiceImpl.list(queryWrapper2);
        model.addAttribute("pplinfos", pplinfos);
        return "form-join";
    }

    @RequestMapping(value = "agreePpl.action", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, String> agree(@RequestBody List<Integer> pplids, HttpSession session) {
        boolean res = false;
        Map<String, String> map = new HashMap<>();
        res = pplServiceImpl.update(Ppl.builder().stat(true).build(), new UpdateWrapper<Ppl>().in("pplid", pplids));
        map.put("res", res == true ? "1" : "0");
        return map;
    }
}
