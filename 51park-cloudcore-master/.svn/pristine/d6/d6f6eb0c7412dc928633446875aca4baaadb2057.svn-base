package com.park.cloudcore.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.park.cloudcore.service.system.FieldGenerateService;

/**
 * @author fangct on 20171225
 */
@Controller
@RequestMapping("/system")
public class FieldGenerateController {
	
	@Autowired
    private FieldGenerateService fieldGenerateServiceImpl;
    
	/**
	 * 获取属性值
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/generateFieldValue", produces="text/html;charset=UTF-8")
	public String add(HttpServletRequest request, HttpServletResponse response) {
		String params = (String) request.getAttribute("params");
		return fieldGenerateServiceImpl.generateNew(params);
	}
	
}
