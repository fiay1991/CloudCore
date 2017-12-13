package com.park.cloudcore.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fangct on 2017/11/27.
 */
public class PublicHandleTools {
    private static Logger logger = LoggerFactory.getLogger(PublicHandleTools.class);

    /**
     * 记录操作数据库的结果
     *
     * @param records
     * @param topic
     * @param params
     */
    public static boolean logOperDbResult(int records, String topic, String params) {
        if (0 != records) {
            return true;
        } else {
            logger.info("** {}, 失败：参数={}", topic, params);
            return false;
        }
    }
}
