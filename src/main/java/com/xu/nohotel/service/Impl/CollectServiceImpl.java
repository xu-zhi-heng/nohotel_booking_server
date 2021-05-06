package com.xu.nohotel.service.Impl;

import com.xu.nohotel.dao.CollectDao;
import com.xu.nohotel.domain.Collect;
import com.xu.nohotel.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectDao collectDao;
    @Override
    public boolean insert(Collect collect) {
        return collectDao.insert(collect) > 0;
    }

    @Override
    public List<Collect> findByUserId(Integer userId) {
        return collectDao.findByUserId(userId);
    }

    @Override
    public boolean delete(Collect collect) {
        return collectDao.delete(collect) > 0;
    }

    @Override
    public Collect isLike(Collect collect) {
        return collectDao.isLike(collect);
    }
}
