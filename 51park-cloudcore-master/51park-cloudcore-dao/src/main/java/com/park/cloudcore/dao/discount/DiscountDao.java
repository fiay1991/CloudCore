package com.park.cloudcore.dao.discount;

import com.park.cloudcore.domain.discount.DiscountDomain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author fangct on 20171129
 *
 */
@Repository(value="discountDao")
public interface DiscountDao {
    
    /**
     * 优惠表插入操作
     * @param discount
     * @return
     */
    public int insertDiscount(DiscountDomain discount);

    /**
     * 优惠表查找idList操作
     * @param paramMap
     * @return
     */
    public List<Integer> selectDiscountIds(Map<String, String> paramMap);

    /**
     * 优惠表查询discountList操作
     * @param paramMap
     * @return
     */
    public int updateDiscount(Map<String, String> paramMap);

    /**
     * 优惠表多项查询操作
     * @param paramMap
     * @return
     */
    public List<DiscountDomain> selectDiscounts(Map<String, String> paramMap);

    /**
     * 优惠表多项查询操作
     * @param paramMap
     * @return
     */
    public DiscountDomain selectDiscount(Map<String, String> paramMap);
    
}
