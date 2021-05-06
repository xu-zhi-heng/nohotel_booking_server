package com.xu.nohotel.domain;

public class RoomInfo {
    private Integer id;
    private String size;
    private int people;
    private int isSmoke;
    private int isFood;
    private String bed;
    private String device;
    private String bath;
    private String roomNum;

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getIsSmoke() {
        return isSmoke;
    }

    public void setIsSmoke(int isSmoke) {
        this.isSmoke = isSmoke;
    }

    public int getIsFood() {
        return isFood;
    }

    public void setIsFood(int isFood) {
        this.isFood = isFood;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBath() {
        return bath;
    }

    public void setBath(String bath) {
        this.bath = bath;
    }
}
