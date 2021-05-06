package com.xu.nohotel.dao;

import com.xu.nohotel.domain.Collect;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CollectDao {
    public int insert(Collect collect);
    public List<Collect> findByUserId(Integer userId);
    public int delete(Collect collect);
    public Collect isLike(Collect collect);
}
