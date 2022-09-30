package com.rb.login.mapper;

import com.rb.login.model.entity.Role;
import com.rb.login.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    Role findById(Integer id);

    void save(Role role);

    void update(Role role);

    long countByRoleName(String name);

    List<Role> findAll();

    void delete(Integer id);
}
