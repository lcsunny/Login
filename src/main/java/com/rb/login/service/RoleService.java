package com.rb.login.service;

import com.rb.login.model.entity.Resource;
import com.rb.login.model.entity.Role;

import java.util.List;

public interface RoleService {

    Role findById(Integer roleId);

    List<Resource> findResourceByRoleId(Integer roleId);

    //保存角色信息
    Integer saveInfo(Role role);

    //查询角色条数
    Integer count();

    //查询全部角色
    List<Role> findAll();

    //删除角色
    void deleteById(Integer id);

    //将资源授权给角色
    void grantResource2Role(Integer roleId, List<Integer> resourceIds);
}
