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

    public void set(HttpSession httpSession) {
        String[] strs = httpSession.getValueNames();
        for (String str : strs) {
            if (str.equals("recordtimes")) {
                httpSession.removeAttribute("recordtimes");
            } else if (str.equals("mod")) {
                httpSession.removeAttribute("mod");
            }
        }
    }

    @RequestMapping("/stuself")
    public String self(HttpSession httpSession, Model model) {
        try {
            People stu = (People) httpSession.getAttribute("people");
            QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("pid", stu.getPid());
            List<Recordtime> recordtimes = recordtimeServiceImpl.list(queryWrapper);
            System.out.println(recordtimes);
            set(httpSession);
            httpSession.setAttribute("recordtimes", recordtimes);
            httpSession.setAttribute("mod", 0);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "form-record";
    }

    @RequestMapping("/stulea")
    public String stu_lea(HttpSession httpSession, Model model) {

        try {
            People stu = (People) httpSession.getAttribute("people");
            QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("sid", stu.getPid());
            List<Recordtime> recordtimes = recordtimeServiceImpl.list(queryWrapper);
            System.out.println(recordtimes);
            set(httpSession);
            httpSession.setAttribute("recordtimes", recordtimes);
            httpSession.setAttribute("mod", 1);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "form-record";
    }

    @RequestMapping("/tea")
    public String tea_pro(HttpSession httpSession, Model model) {

        try {
            People tea = (People) httpSession.getAttribute("people");
            System.out.println(tea);
            QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
            System.out.println(tea.getPid());
            queryWrapper.eq("tid", tea.getPid());
            List<Recordtime> recordtimes = recordtimeServiceImpl.list(queryWrapper);
            System.out.println(recordtimes);
            set(httpSession);
            httpSession.setAttribute("recordtimes", recordtimes);
            httpSession.setAttribute("mod", 1);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "form-record";
    }

    @RequestMapping(value = "record.action", method = { RequestMethod.POST, RequestMethod.GET })
    public String select(@RequestBody Map<String, String> recordtime, Model model, HttpSession httpSession) {
        System.out.println(recordtime);
        System.out.println(recordtime.get("proname")==null?1:0);
        People people = (People) httpSession.getAttribute("people");
        System.out.println(recordtime);
        QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
        List<Recordtime> recordtimes = new ArrayList<>();
        //Boolean sot = (boolean) recordtime.get("mod");
        Boolean sot = true;
        try {
            if (sot) {
                if (people.getPlimit() == 1) {
                    queryWrapper.eq("sid", people.getPid());
                    if (recordtime.get("pname") != null && recordtime.get("proname") != null) {
                        queryWrapper.eq("pname", recordtime.get("pname")).eq("proname", recordtime.get("proname"));
                    } else if (recordtime.get("pname") != null && recordtime.get("proname") == null) {
                        queryWrapper.eq("pname", recordtime.get("pname"));
                    } else if (recordtime.get("proname") != null && recordtime.get("pname") == null) {
                        queryWrapper.eq("proname", recordtime.get("proname"));
                    }
                } else {
                    queryWrapper.eq("tid", people.getPid());
                    if (recordtime.get("pname") != null && recordtime.get("proname") != null) {
                        queryWrapper.eq("pname", recordtime.get("pname")).eq("proname", recordtime.get("proname"));
                    } else if (recordtime.get("pname") != null && recordtime.get("proname") == null) {
                        queryWrapper.eq("pname", recordtime.get("pname"));
                    } else if (recordtime.get("proname") != null && recordtime.get("pname") == null) {
                        queryWrapper.eq("proname", recordtime.get("proname"));
                    }
                }

                recordtimes = recordtimeServiceImpl.list(queryWrapper);
            } else {
                if (recordtime.get("proname") != null) {
                    queryWrapper.eq("proname", recordtime.get("proname"));
                } else
                    queryWrapper.eq("pid", people.getPid());
                recordtimes = recordtimeServiceImpl.list(queryWrapper);
            }
            String[] strs = httpSession.getValueNames();
            for (String str : strs) {
                if (str.equals("recordtimes")) {
                    httpSession.removeAttribute("recordtimes");
                    break;
                }
            }
            httpSession.setAttribute("recordtimes", recordtimes);
            System.out.println(recordtimes);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return "form-record";
    }
}
