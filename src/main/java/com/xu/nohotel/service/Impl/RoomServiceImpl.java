package com.xu.nohotel.service.Impl;

import com.xu.nohotel.dao.RoomDao;
import com.xu.nohotel.dao.RoomTypeDao;
import com.xu.nohotel.domain.Room;
import com.xu.nohotel.domain.RoomType;
import com.xu.nohotel.service.RoomService;
import com.xu.nohotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;

    @Override
    public List<Room> getRoomByType(Integer type) {
        return roomDao.getRoomByType(type);
    }

    @Override
    public Room getRoomDelById(Integer id) {
        return roomDao.getRoomDelById(id);
    }

    @Override
    public Room getRoomDelByRoomNum(String roomNum) {
        return roomDao.getRoomDelByRoomNum(roomNum);
    }
}
