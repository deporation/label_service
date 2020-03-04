package com.labservice.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.labservice.demo.entity.People;
import com.labservice.demo.entity.Recordtime;
import com.labservice.demo.service.impl.RecordtimeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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


    @RequestMapping(value = "record.action", method = { RequestMethod.POST, RequestMethod.GET })
    public String select(@RequestParam(value = "proname") String proname, @RequestParam(value = "pname") String pname,
            Model model, HttpSession httpSession) {
        People people = (People) httpSession.getAttribute("people");
        QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
        List<Recordtime> recordtimes = new ArrayList<>();
        if (people.getPlimit() == 1) {
            
            if (proname != "" && pname == "") {
                queryWrapper.eq("proname", proname);
            } else if (pname != "" && proname == "") {
                queryWrapper.eq("pname", pname);
            } else if (pname != "" && proname != "") {
                queryWrapper.eq("pname", pname).eq("proname", proname);
            }
            queryWrapper.and(wrapper -> wrapper.eq("sid", people.getPid()).or().eq("pid", people.getPid()));
            recordtimes = recordtimeServiceImpl.list(queryWrapper);
            model.addAttribute("recordtimes", recordtimes);
        } else {
            queryWrapper.eq("tid", people.getPid());
            if (proname != "" && pname == "") {
                queryWrapper.eq("proname", proname);
            } else if (pname != "" && proname == "") {
                queryWrapper.eq("pname", pname);
            } else if (pname != "" && proname != "") {
                queryWrapper.eq("pname", pname).eq("proname", proname);
            }
            recordtimes = recordtimeServiceImpl.list(queryWrapper);
            model.addAttribute("recordtimes", recordtimes);
            model.addAttribute("proname", proname);
            model.addAttribute("pname", pname);
        }
        return "form-record";
    }
}
