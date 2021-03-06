package com.park.cloudcore.controller.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.cloudcore.service.order.OrderPayRecordService;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Controller
@RequestMapping("/orderPayRecord")
public class OrderPayRecordController {
    
	@Autowired
    private OrderPayRecordService orderPayRecordServiceImpl;
    
    @ResponseBody
    @RequestMapping(value="/add", produces="text/html;charset=UTF-8")
    public String addOrderPayRecord(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return orderPayRecordServiceImpl.addOrderPayRecord(params);
    }
    
    @ResponseBody
    @RequestMapping(value="/modBase", produces="text/html;charset=UTF-8")
    public String modOrderPayRecord(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return orderPayRecordServiceImpl.modBase(params);
    }

    @ResponseBody
    @RequestMapping(value="/modStatus", produces="text/html;charset=UTF-8")
    public String modStatus(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return orderPayRecordServiceImpl.modStatus(params);
    }
    @ResponseBody
    @RequestMapping(value="/find", produces="text/html;charset=UTF-8")
    public String findOrderPayRecord(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return orderPayRecordServiceImpl.findOrderPayRecord(params);
    }
    
    @ResponseBody
    @RequestMapping(value="/sum", produces="text/html;charset=UTF-8")
    public String sumOrderPayRecord(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return orderPayRecordServiceImpl.sumOrderPayRecord(params);
    }
    
}
