package com.labservice.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.labservice.demo.entity.People;
import com.labservice.demo.entity.Recordtime;
import com.labservice.demo.service.impl.RecordtimeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/stu_com_pro")
    public String stu_pro(HttpSession httpSession) {
        People people = (People) httpSession.getAttribute("people");
        QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", people.getPid());
        List<Recordtime> recordtimes = recordtimeServiceImpl.list(queryWrapper);
        httpSession.setAttribute("stu_com_pro", recordtimes);

        return "list_stu_pro";
    }

    @RequestMapping("/stu_lea_pro")
    public String stu_lea(HttpSession httpSession) {
        try {
            People people = (People) httpSession.getAttribute("people");
            QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sid", people.getPid());
            List<Recordtime> recordtimes = recordtimeServiceImpl.list(queryWrapper);
            httpSession.setAttribute("stu_lea_pro", recordtimes);
        } catch (Exception e) {
            return "list_stu_pro";
        }
        return "list_stu_pro";
    }

    @RequestMapping("/tea_pro")
    public String tea_pro(HttpSession httpSession) {
        People people = (People) httpSession.getAttribute("people");
        QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tid", people.getPid());
        List<Recordtime> recordtimes = recordtimeServiceImpl.list(queryWrapper);
        httpSession.setAttribute("tea_pro", recordtimes);
        return "list_stu_pro";
    }

}
