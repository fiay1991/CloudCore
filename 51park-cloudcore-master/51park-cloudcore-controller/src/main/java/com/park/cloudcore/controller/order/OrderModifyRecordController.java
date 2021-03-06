package com.park.cloudcore.controller.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.cloudcore.service.order.OrderModifyRecordService;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Controller
@RequestMapping("/orderModifyRecord")
public class OrderModifyRecordController {
    
	@Autowired
    private OrderModifyRecordService orderModifyRecordServiceImpl;
    
    @ResponseBody
    @RequestMapping(value="/add", produces="text/html;charset=UTF-8")
    public String addOrderModifyRecord(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return orderModifyRecordServiceImpl.addOrderModifyRecord(params);
    }
    
    @ResponseBody
    @RequestMapping(value="/finds", produces="text/html;charset=UTF-8")
    public String findOrderModifyRecords(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return orderModifyRecordServiceImpl.findOrderModifyRecords(params);
    }
    
}
