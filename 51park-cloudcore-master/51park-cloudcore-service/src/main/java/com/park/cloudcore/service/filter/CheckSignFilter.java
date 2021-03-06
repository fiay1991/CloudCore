package com.park.cloudcore.service.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.park.base.common.RSATools;
import com.park.base.common.ResultTools;
import com.park.cloudcore.common.constants.Code;
import com.park.cloudcore.common.constants.PrivateKeyConstants;

/**
 * 验签
 * @author fangct on 20171129
 *
 */
public class CheckSignFilter implements Filter {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response =(HttpServletResponse)resp;
		response.setContentType("text/html; charset=UTF-8");
        String url = request.getRequestURI();
        /** 欢迎页*/
        if (url.endsWith("/CloudCore/")) {
        		chain.doFilter(request, response); 
			return;
		}
        String pid = request.getHeader("PID");
        String params = request.getParameter("params");
        logger.info("***PID={}, 请求的URL={}", pid, url);
        
        if (StringUtils.isBlank(params)) {
			logger.info("***未获取到params参数");
			response.getWriter().print(ResultTools.setResponse(Code.ERROR_400, Code.getName(Code.ERROR_400)));
			return;
		}
        boolean verify = false;
        try {
            String decryptParams = RSATools.decrypt(params, PrivateKeyConstants.THIS.getPrivateKey());
            request.setAttribute("params", decryptParams);
            verify = true;
        } catch (Exception e) {
            e.printStackTrace();
			logger.error("解密环节出现错误：URL：" + url + "错误信息" + e.toString());
        }
        if (verify) {
       		chain.doFilter(request,response);
       		return;
       	}else {
			logger.info("来自" + pid + "的访问,认证失败，请求url:" + url + "***参数：" + params);
       		response.getWriter().print(ResultTools.setResponse(Code.ERROR_401, Code.getName(Code.ERROR_401)));
       		return;
       	}  
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		/*excludedPages = filterConfig.getInitParameter("excludedUrl");     
		if (StringUtils.isNotEmpty(excludedPages)) {     
			excludedPageArray = excludedPages.split(",");     
		}*/
		return;  
	}
}
