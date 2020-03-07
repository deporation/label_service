package com.labservice.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
            @RequestParam(value = "pagenum") int pagenum, Model model, HttpSession httpSession) {
        PageHelper.startPage(pagenum, 20);
        People people = (People) httpSession.getAttribute("people");
        QueryWrapper<Recordtime> queryWrapper = new QueryWrapper<>();
        if (people.getPlimit() == 1) {

            if (proname != "" && pname == "") {
                queryWrapper.eq("proname", proname);
            } else if (pname != "" && proname == "") {
                queryWrapper.eq("pname", pname);
            } else if (pname != "" && proname != "") {
                queryWrapper.eq("pname", pname).eq("proname", proname);
            }
            queryWrapper.and(wrapper -> wrapper.eq("sid", people.getPid()).or().eq("pid", people.getPid()));
        } else {
            queryWrapper.eq("tid", people.getPid());
            if (proname != "" && pname == "") {
                queryWrapper.eq("proname", proname);
            } else if (pname != "" && proname == "") {
                queryWrapper.eq("pname", pname);
            } else if (pname != "" && proname != "") {
                queryWrapper.eq("pname", pname).eq("proname", proname);
            }
        }
        List<Recordtime> recordtimes = recordtimeServiceImpl.list(queryWrapper);
        PageInfo<Recordtime> pageInfo = new  PageInfo<Recordtime>(recordtimes,1);
        model.addAttribute("recordtimes", pageInfo);
        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "form-record";
    }
}
