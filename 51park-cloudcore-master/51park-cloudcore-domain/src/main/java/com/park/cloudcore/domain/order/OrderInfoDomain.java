package com.park.cloudcore.domain.order;

/**
 * @author fangct on 20171129
 */
public class OrderInfoDomain {
    private String id, orderNum, parkId, plateNumber, cardId, ticketId,
    carType, enterTime, costBefore, costAfter, discountAmount,
            prepayAmount, prepayTime, type, carDesc, localOrderId, createTime,
            updateTime, exitTime, serviceStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getLocalOrderId() {
        return localOrderId;
    }

    public void setLocalOrderId(String localOrderId) {
        this.localOrderId = localOrderId;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getCostBefore() {
        return costBefore;
    }

    public void setCostBefore(String costBefore) {
        this.costBefore = costBefore;
    }

    public String getCostAfter() {
        return costAfter;
    }

    public void setCostAfter(String costAfter) {
        this.costAfter = costAfter;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getPrepayAmount() {
        return prepayAmount;
    }

    public void setPrepayAmount(String prepayAmount) {
        this.prepayAmount = prepayAmount;
    }

    public String getPrepayTime() {
        return prepayTime;
    }

    public void setPrepayTime(String prepayTime) {
        this.prepayTime = prepayTime;
    }

    public String getType() {
        return type;
    }

    public String getCarDesc() {
        return carDesc;
    }

    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    @Override
    public String toString() {
        return "OrderInfoDomain{" +
                "id='" + id + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", parkId='" + parkId + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", cardId='" + cardId + '\'' +
                ", ticketId='" + ticketId + '\'' +
                ", carType='" + carType + '\'' +
                ", enterTime='" + enterTime + '\'' +
                ", costBefore='" + costBefore + '\'' +
                ", costAfter='" + costAfter + '\'' +
                ", discountAmount='" + discountAmount + '\'' +
                ", prepayAmount='" + prepayAmount + '\'' +
                ", prepayTime='" + prepayTime + '\'' +
                ", type='" + type + '\'' +
                ", carDesc='" + carDesc + '\'' +
                ", localOrderId='" + localOrderId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", exitTime='" + exitTime + '\'' +
                ", serviceStatus='" + serviceStatus + '\'' +
                '}';
    }
}
