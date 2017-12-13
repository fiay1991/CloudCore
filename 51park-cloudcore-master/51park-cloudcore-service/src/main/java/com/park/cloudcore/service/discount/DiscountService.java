package com.park.cloudcore.service.discount;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Repository(value="discountService")
public interface DiscountService {
    
    /**
     * 添加一项优惠
     * @param params
     * @return
     */
    public String addDiscount(String params);

    /**
     * 修改一项优惠
     * @param params
     * @return
     */
    public String modByOrdernum(String params);
    /**
     * 修改一项优惠
     * @param params
     * @return
     */
    public String modByDiscountNo(String params);
    
    /**
     * 查询优惠列表
     * @param params
     * @return
     */
    public String findDiscounts(String params);
    /**
     * 查询优惠
     * @param params
     * @return
     */
    public String findDiscount(String params);
    
}
