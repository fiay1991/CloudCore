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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.park.base.common.RSATools;
import com.park.base.common.ResultTools;
import com.park.cloudcore.common.MySignTools;
import com.park.cloudcore.common.constants.MyCodeConstants;
import com.park.cloudcore.properites.KeysConfig;
import com.park.cloudcore.properites.Spring;

/**
 * 验签
 * @author fangct on 20171129
 *
 */
public class CheckSignFilter implements Filter {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private String privateKey;
    private String publicKey;
    
    public void getKeys() {
        KeysConfig keysConfig = Spring.getBean("keysConfig");
        privateKey = keysConfig.getPrivateKey();
        publicKey = keysConfig.getPublicKey();
    }
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		getKeys();
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response =(HttpServletResponse)resp;
		response.setContentType("text/html; charset=UTF-8");
        String url = request.getRequestURI();
        /** 欢迎页*/
        if (url.endsWith("/CloudCore/")) {
        		chain.doFilter(request, response); 
			return;
		}
        String sign = request.getHeader("Authorization");
        String lockParams = request.getParameter("params");
        
        boolean verify = false;
        try {
            String decryptParams = RSATools.decrypt(lockParams, privateKey);
            request.setAttribute("params", decryptParams);
            verify = MySignTools.RSAVerify(decryptParams, sign, privateKey, publicKey);
        } catch (Exception e) {
            logger.info("** 验签出现异常！");
            e.printStackTrace();
        }
        if (verify) {
       		chain.doFilter(request,response);
       		return;
       	}else {
       	    logger.info("** 验签失败。");
       		response.getWriter().print(ResultTools.setResponse(MyCodeConstants.ERROR_401));
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
