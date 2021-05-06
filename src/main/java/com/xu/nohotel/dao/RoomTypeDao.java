package com.xu.nohotel.dao;

import com.xu.nohotel.domain.RoomType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomTypeDao {
    public List<RoomType> getAllRoomType();           // 获取所有的房间类型
    public Integer getRoomTypeId(String typeName);   //  根据类型名称获取类型id
    public String getRoomTypeName(Integer type);       // 根据类型id获取类型名称
}
