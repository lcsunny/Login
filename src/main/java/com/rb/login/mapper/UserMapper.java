package com.rb.login.mapper;

import com.rb.login.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void save(User user);

    void update(User user);

    User findById(Integer id);

    User findByUserName(String userName);

    List<User> findByPage();

    void delete(int id);

    Integer count();

    long countByUserName(String userName);

}
