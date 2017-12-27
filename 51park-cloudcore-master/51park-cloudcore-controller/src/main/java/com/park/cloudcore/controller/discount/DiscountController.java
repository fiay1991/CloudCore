package com.park.cloudcore.controller.discount;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.cloudcore.service.discount.DiscountService;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Controller
@RequestMapping("/discount")
public class DiscountController {
    
    @Autowired
    private DiscountService discountServiceImpl;
    
    @ResponseBody
    @RequestMapping("/add")
    public String addDiscount(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return discountServiceImpl.addDiscount(params);
    }
    
    @ResponseBody
    @RequestMapping("/modByOrdernum")
    public String modByOrdernum(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return discountServiceImpl.modByOrdernum(params);
    }
    
    @ResponseBody
    @RequestMapping("/modByNo")
    public String modByNo(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return discountServiceImpl.modByOrdernum(params);
    }

    @ResponseBody
    @RequestMapping("/find")
    public String findDiscount(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return discountServiceImpl.findDiscount(params);
    }
    @ResponseBody
    @RequestMapping("/finds")
    public String findDiscounts(HttpServletRequest request, HttpServletResponse response) {
        String params = (String) request.getAttribute("params");
        return discountServiceImpl.findDiscounts(params);
    }
    
}
