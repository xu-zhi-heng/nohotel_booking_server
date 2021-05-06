package com.xu.nohotel.dao;

import com.xu.nohotel.domain.Login;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao {
    public int update(Login login);
    public int insert(Login login);
    public Login selectByPhoneNum(String phoneNum);
}
