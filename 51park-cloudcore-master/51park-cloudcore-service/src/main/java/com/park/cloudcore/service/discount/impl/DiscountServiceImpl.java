package com.park.cloudcore.service.discount.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.park.base.common.DataChangeTools;
import com.park.base.common.RandomTools;
import com.park.base.common.ResultTools;
import com.park.base.common.ToolsUtil;
import com.park.cloudcore.common.PublicHandleTools;
import com.park.cloudcore.common.constants.MyCodeConstants;
import com.park.cloudcore.dao.discount.DiscountDao;
import com.park.cloudcore.domain.discount.DiscountDomain;
import com.park.cloudcore.service.discount.DiscountService;

/**
 * @author fangct on 20171129
 */
@Repository(value = "discountServiceImpl")
public class DiscountServiceImpl implements DiscountService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "discountDao")
    private DiscountDao discountDao;

    public String addDiscount(String params) {
        try {
            DiscountDomain discount = DataChangeTools.gson2bean(params, DiscountDomain.class);
            // 校验必填参数
            String discountNo = discount.getDiscountNo();
            String parkId = discount.getParkId();
            String orderNum = discount.getOrderNum();
            String type = discount.getType();
            String amount = discount.getAmount();
            String fromType = discount.getFromType();
            String status = discount.getStatus();
            String discountTime = discount.getDiscountTime();
            if (ToolsUtil.isNull(parkId) || ToolsUtil.isNull(orderNum) ||
                    ToolsUtil.isNull(type) || ToolsUtil.isNull(amount) ||
                    ToolsUtil.isNull(fromType) || ToolsUtil.isNull(status) ||
                    ToolsUtil.isNull(discountTime)) {
                logger.info("** 新增优惠 - 必填参数为空：parkId=" + parkId +
                        "，orderNum=" + orderNum + "，type=" + type +
                        "，amount=" + amount + "，fromType=" +
                        fromType + "，status=" + status + ", discountTime=" + discountTime);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            //如果没有传优惠编号，则生成一个
            if(ToolsUtil.isNull(discountNo)){
            	discount.setDiscountNo(RandomTools.GenerateDiscountNo());
            }
            // 插入数据库并返回数据
            int result = discountDao.insertDiscount(discount);
            boolean success = PublicHandleTools.logOperDbResult(result, "新增优惠", discount.getOrderNum());
            if (!success) {
                return ResultTools.setResponse(MyCodeConstants.ERROR_406);
            }
            Map<String, String> resultMap = new HashMap<String, String>();
            String id = discount.getId();
            resultMap.put("id", id);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, resultMap);
        } catch (Exception e) {
            logger.info("** 新增优惠 - 保存出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String modByOrdernum(String params) {
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            // 校验必填参数
            String orderNum = paramMap.get("orderNum");
            String newStatus = paramMap.get("newStatus");
            if (null == orderNum || "".equals(orderNum) ||
                    null == newStatus || "".equals(newStatus)) {
                logger.info("** 修改优惠 - 必填参数为空：orderNum=" + orderNum
                        + "，newStatus=" + newStatus);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            // 查找对应ID以备日志和返回数据
            List<Integer> discountIds = discountDao.selectDiscountIds(paramMap);
            if (discountIds != null && discountIds.size() > 0) {
                // 更新数据库并返回数据
                int result = discountDao.updateDiscount(paramMap);
                boolean success = PublicHandleTools.logOperDbResult(result, "修改优惠", orderNum);
                if (!success) {
                    return ResultTools.setObjectResponse(MyCodeConstants.ERROR_406, discountIds);
                }
                return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, discountIds);
            } else {
                return ResultTools.setResponse(MyCodeConstants.ERROR_404);
            }
        } catch (Exception e) {
            logger.info("** 修改优惠 - 更新出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }
    
    public String modByDiscountNo(String params) {
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            // 校验必填参数
            String discountNo = paramMap.get("discountNo");
            String newStatus = paramMap.get("newStatus");
            if (null == discountNo || "".equals(discountNo) ||
                    null == newStatus || "".equals(newStatus)) {
                logger.info("** 修改优惠 - 必填参数为空：discountNo=" + discountNo
                        + "，newStatus=" + newStatus);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            int result = discountDao.updateDiscount(paramMap);
            if(result != 0){
            	return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, discountNo);
            } else {
                return ResultTools.setResponse(MyCodeConstants.ERROR_404);
            }
        } catch (Exception e) {
            logger.info("** 修改优惠 - 更新出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }
    public String findDiscounts(String params) {
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            // 校验参数
            String parkId = paramMap.get("parkId");
            String orderNum = paramMap.get("orderNum");

            if ((null == parkId || "".equals(parkId)) &&
                    (null == orderNum || "".equals(orderNum))) {
                logger.info("** 查询优惠列表 - 必填参数全空：parkId=" + parkId + "，orderNum=" +
                        orderNum);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            List<DiscountDomain> discountList = discountDao.selectDiscounts(paramMap);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, discountList);
        } catch (Exception e) {
            logger.info("** 查询优惠列表 - 查询出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }

    public String findDiscount(String params) {
        try {
            Map<String, String> paramMap = DataChangeTools.json2Map(params);
            // 校验参数
            String id = paramMap.get("id");
            if (ToolsUtil.isNull(id)) {
                logger.info("** 查询优惠 - 必填参数为空：id=" + id);
                return ResultTools.setResponse(MyCodeConstants.ERROR_400);
            }
            DiscountDomain discount = discountDao.selectDiscount(paramMap);
            return ResultTools.setObjectResponse(MyCodeConstants.SUCCESS, discount);
        } catch (Exception e) {
            logger.info("** 查询优惠 - 查询出现异常：" + e.getMessage());
            e.printStackTrace();
            return ResultTools.setResponse(MyCodeConstants.ERROR);
        }
    }
}
