package com.park.cloudcore.controller.order;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.cloudcore.service.order.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
    
    @Resource(name="orderServiceImpl")
    private OrderService orderServiceImpl;
    
    @ResponseBody
    @RequestMapping(value="/sumTrade", produces="text/html;charset=UTF-8")
    public String sumTrade(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return orderServiceImpl.sumTrade(params);
    }
    
}
