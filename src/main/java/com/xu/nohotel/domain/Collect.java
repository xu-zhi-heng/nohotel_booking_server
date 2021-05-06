package com.xu.nohotel.domain;

public class Collect {
    private Integer userId;
    private Integer roomId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "userId=" + userId +
                ", roomId=" + roomId +
                '}';
    }
}
