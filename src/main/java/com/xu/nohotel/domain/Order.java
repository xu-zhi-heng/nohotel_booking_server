package com.xu.nohotel.domain;

import java.util.Date;

public class Order {
    private Integer id;
    private Integer daysNum;
    private Integer roomId;
    private Integer userId;
    private Date checkIn;
    private String roomNum;
    private Double totalPrice;
    private Integer state;
    private Date createTime;
    private String note;
    private String faceToken;
    private String idNum;
    private String title;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDaysNum() {
        return daysNum;
    }

    public void setDaysNum(Integer daysNum) {
        this.daysNum = daysNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", daysNum=" + daysNum +
                ", roomId=" + roomId +
                ", userId=" + userId +
                ", checkIn=" + checkIn +
                ", roomNum='" + roomNum + '\'' +
                ", totalPrice=" + totalPrice +
                ", state=" + state +
                ", createTime=" + createTime +
                ", note='" + note + '\'' +
                ", faceToken='" + faceToken + '\'' +
                ", idNum='" + idNum + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
