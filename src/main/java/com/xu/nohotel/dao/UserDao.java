package com.xu.nohotel.dao;
import com.xu.nohotel.domain.User;
import org.springframework.stereotype.Repository;
@Repository
public interface UserDao {
    public User loginByPhone(String phoneNum);
    public User loginByAccount(String username,String password);
    public User loadingByPhone(String phoneNum);
    public User loadingByAccount(String username);
    public int update(User user);
    public int insert(User user);
    public int updateFaceToken(User user);
}

