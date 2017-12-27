package com.park.cloudcore.controller.park;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.cloudcore.service.park.ParkInfoService;

/**
 * 车场信息
 * @author fangct on 20171224
 */
@Controller
@RequestMapping("/parkInfo")
public class ParkInfoController {
	
	@Autowired
    private ParkInfoService parkInfoServiceImpl;
    
	@ResponseBody
	@RequestMapping(value="/find", produces="text/html;charset=UTF-8")
	public String add(HttpServletRequest request, HttpServletResponse response) {
		String params = (String) request.getAttribute("params");
		return parkInfoServiceImpl.find(params);
	}
	
}
