package com.park.cloudcore.controller.order;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.cloudcore.service.order.OrderEnExRecordService;

@Controller
@RequestMapping("/orderEnExRecord")
public class OrderEnExRecordController {
    
    @Resource(name="orderEnExRecordServiceImpl")
    private OrderEnExRecordService orderEnExRecordServiceImpl;
    
    @ResponseBody
    @RequestMapping(value="/add", produces="text/html;charset=UTF-8")
    public String addOrderEnExRecord(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return orderEnExRecordServiceImpl.addOrderEnExRecord(params);
    }
    
    @ResponseBody
    @RequestMapping(value="/finds", produces="text/html;charset=UTF-8")
    public String findOrderEnExRecords(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return orderEnExRecordServiceImpl.findOrderEnExRecords(params);
    }
    
    @ResponseBody
    @RequestMapping(value="/mod", produces="text/html;charset=UTF-8")
    public String modOrderEnExRecord(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return orderEnExRecordServiceImpl.modOrderEnExRecord(params);
    }
    
}
