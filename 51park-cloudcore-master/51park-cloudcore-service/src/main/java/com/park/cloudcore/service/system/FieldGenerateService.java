package com.park.cloudcore.service.system;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author fangct on 20171225
 *
 */
@Repository(value="fieldGenerateService")
public interface FieldGenerateService {
	
	/**
	 * 按照规则生成最新的属性值
	 * @param params
	 * @return
	 */
	public String generateNew(String params);

}
