package com.park.cloudcore.domain.order;

/**
 * 
 * @author fangct on 20171129
 *
 */
public class OrderModifyRecordDomain {
    private String id, action, parkId, orderNum, modifyType, beforeModify, 
        afterModify, oldCarDesc, newCarDesc, modifyTime, userAccount, createTime, updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getModifyType() {
        return modifyType;
    }

    public void setModifyType(String modifyType) {
        this.modifyType = modifyType;
    }

    public String getBeforeModify() {
        return beforeModify;
    }

    public void setBeforeModify(String beforeModify) {
        this.beforeModify = beforeModify;
    }

    public String getAfterModify() {
        return afterModify;
    }

    public void setAfterModify(String afterModify) {
        this.afterModify = afterModify;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOldCarDesc() {
        return oldCarDesc;
    }

    public void setOldCarDesc(String oldCarDesc) {
        this.oldCarDesc = oldCarDesc;
    }

    public String getNewCarDesc() {
        return newCarDesc;
    }

    public void setNewCarDesc(String newCarDesc) {
        this.newCarDesc = newCarDesc;
    }

    @Override
    public String toString() {
        return "OrderModifyRecordDomain{" +
                "id='" + id + '\'' +
                ", action='" + action + '\'' +
                ", parkId='" + parkId + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", modifyType='" + modifyType + '\'' +
                ", beforeModify='" + beforeModify + '\'' +
                ", afterModify='" + afterModify + '\'' +
                ", oldCarDesc='" + oldCarDesc + '\'' +
                ", newCarDesc='" + newCarDesc + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
