package com.labservice.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.labservice.demo.entity.Label;
import com.labservice.demo.entity.People;
import com.labservice.demo.service.impl.LabelServiceImpl;

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
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelServiceImpl labelServiceImpl;

    @RequestMapping("/signLabel")
    public String sign() {
        return "form-label";
    }

    @RequestMapping(value = "signLabel.action", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public Map<String, String> sign(@RequestBody Label label, HttpSession httpSession) {
        Map<String, String> map = new HashMap<String, String>();
        boolean res = false;
        try {
            if (labelServiceImpl.getOne(new QueryWrapper<Label>().eq("lname", label.getLname()).eq("scid",
                    ((People) httpSession.getAttribute("people")).getScid())) == null) {
                label = label.setScid(((People) httpSession.getAttribute("people")).getScid());
                res = labelServiceImpl.save(label);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("res", res == true ? "1" : "0");
        return map;
    }

}