package com.labservice.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.labservice.demo.entity.People;
import com.labservice.demo.entity.Recordtime;
import com.labservice.demo.service.impl.RecordtimeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/recordtime")
public class RecordtimeController {

    @Autowired
    private RecordtimeServiceImpl recordtimeServiceImpl;
    private boolean sot = false;
    @RequestMapping("/stuself")
    @ResponseBody
    public String self(HttpSession httpSession,Model model){
        People stu = (People) httpSession.getAttribute("people");
        QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", stu.getPid());
        List<Recordtime> recordtimes = recordtimeServiceImpl.list(queryWrapper);
        model.addAttribute("recordtimes", recordtimes);
        sot = false;
        model.addAttribute("mod", sot);
        return "form-record";
    }
    
    @RequestMapping("/stulea")
    public String stu_lea(HttpSession httpSession,Model model) {
        People stu = (People) httpSession.getAttribute("people");
        QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sid", stu.getPid());
        List<Recordtime> recordtimes = recordtimeServiceImpl.list(queryWrapper);
        model.addAttribute("recordtimes", recordtimes);
        sot = true;
        model.addAttribute("mod", sot);
        return "form-record";
    }

    @RequestMapping("/tea")
    public String tea_pro(HttpSession httpSession,Model model) {
        People tea = (People) httpSession.getAttribute("people");
        QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tid", tea.getPid());
        List<Recordtime> recordtimes = recordtimeServiceImpl.list(queryWrapper);
        model.addAttribute("recordtimes", recordtimes);
        sot = true;
        model.addAttribute("mod", sot);
        return "form-record";
    }
    @RequestMapping(value = "record.action", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public String select(@RequestBody Recordtime recordtime,Model model,HttpSession httpSession) {
        QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
        List<Recordtime> recordtimes = new ArrayList<>();
        Map map = model.asMap();
        sot = (boolean)map.get("mod");
        if (sot) {
            queryWrapper.eq("pname", recordtime.getPname()).eq("proname", recordtime.getProname());
            recordtimes = recordtimeServiceImpl.list(queryWrapper);
        } else {
            queryWrapper.eq("proname", recordtime.getProname());
            recordtimes = recordtimeServiceImpl.list(queryWrapper);
        }
        model.addAttribute("recordtimes", recordtimes);
        return "form-record";
    }
}
