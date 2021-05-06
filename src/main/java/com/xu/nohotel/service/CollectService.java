package com.xu.nohotel.service;

import com.xu.nohotel.domain.Collect;

import java.util.List;

public interface CollectService {
    public boolean insert(Collect collect);
    public List<Collect> findByUserId(Integer userId);
    public boolean delete(Collect collect);
    public Collect isLike(Collect collect);
}
