package com.xu.nohotel.service.Impl;

import com.xu.nohotel.dao.RoomTypeDao;
import com.xu.nohotel.domain.RoomType;
import com.xu.nohotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeDao roomTypeDao;
    @Override
    public List<RoomType> getAllRoomType() {
        return roomTypeDao.getAllRoomType();
    }

    @Override
    public Integer getRoomTypeId(String typeName) {
        return roomTypeDao.getRoomTypeId(typeName);
    }

    @Override
    public String getRoomTypeName(Integer type) {
        return roomTypeDao.getRoomTypeName(type);
    }
}
