package com.rb.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleMapper {

    void deleteByUserId(Integer userId);

    void save(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

    List<Integer> findRoleIdsByUserId(Integer userId);
}
