package com.rb.login.mapper;

import com.rb.login.model.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleResourceMapper {

    void deleteByRoleId(Integer roleId);

    void save(@Param("roleId") Integer roleId, @Param("resourceIds") List<Integer> resourceIds);

    List<Resource> findResourceByRoleId(Integer roleId);
}
