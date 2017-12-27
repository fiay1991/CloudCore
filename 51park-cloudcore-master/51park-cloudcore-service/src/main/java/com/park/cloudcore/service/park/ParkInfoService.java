package com.park.cloudcore.service.park;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author fangct on 20171224
 *
 */
@Repository(value="parkInfoService")
public interface ParkInfoService {
	
	/**
	 * 查询车场信息
	 * @param params
	 * @return
	 */
	public String find(String params);

}
