package com.rb.login.mapper;

import com.rb.login.model.entity.Resource;
import com.rb.login.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceMapper {

    long countByResourceName(String name);

    void save(Resource resource);

    Resource findById(Integer id);

    void update(Resource resource);

    List<Resource> findAll();

    void delete(Integer id);
}
