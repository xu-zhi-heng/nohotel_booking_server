package com.xu.nohotel.service;

import com.xu.nohotel.domain.RoomType;

import java.util.List;

public interface RoomTypeService {
    public List<RoomType> getAllRoomType();
    public Integer getRoomTypeId(String typeName);
    public String getRoomTypeName(Integer type);
}
