package com.park.cloudcore.dao.system;

import org.springframework.stereotype.Repository;

import com.park.cloudcore.domain.system.FieldGenerateDomain;

/**
 * 此Dao用作对cp_sys_field_generate表的操作
 * @author fangct on 20171225
 */
@Repository(value="fieldGenerateDao")
public interface FieldGenerateDao {
	
    /**
     * 根据type查询当前最新的属性值
     * @param type
     * @return
     */
	public FieldGenerateDomain selectByType(String type);
	
	 /**
     * 更新到对应type下的最新属性值
     * @param domain
     * @return
     */
	public int updateNew(FieldGenerateDomain domain);

}
