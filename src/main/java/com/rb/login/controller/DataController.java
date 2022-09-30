package com.rb.login.controller;

import com.rb.login.exception.BusinessException;
import com.rb.login.mapper.RoleMapper;
import com.rb.login.model.bo.ResourceForm;
import com.rb.login.model.bo.RoleForm;
import com.rb.login.model.bo.UserForm;
import com.rb.login.model.entity.Resource;
import com.rb.login.model.entity.Role;
import com.rb.login.model.entity.User;
import com.rb.login.model.vo.ResourceVo;
import com.rb.login.model.vo.RoleVo;
import com.rb.login.model.vo.UserVo;
import com.rb.login.service.ResourceService;
import com.rb.login.service.RoleService;
import com.rb.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

import static com.rb.login.util.BeanCopyUtil.copy;
import static com.rb.login.util.BeanCopyUtil.copyList;

@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    //***********************用户接口********************
    // 新增/修改用户信息
    @PostMapping("/user")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer saveUser(@RequestBody UserForm userForm) {
        User user = copy(userForm,User.class);
        return userService.save(user);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public UserVo findUserById(@PathVariable("id") @Min(value=1,message="id不能小于1")Integer id) {
        User user = userService.findById(id);
        if(null == user){
            throw new BusinessException("用户信息不存在");
        }
        log.info("userName:"+user.getUserName());
        log.info("password:"+user.getPassword());
        log.info("phone:"+user.getPhone());
        UserVo vo = copy(user,UserVo.class);
        return vo;
    }

    @GetMapping("/user")
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> findByPage() {
        List<User> users = userService.findByPage();
        return users;
    }

    @PostMapping("/user/grant/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void grantRole2User(@PathVariable("userId") Integer userId, @RequestBody List<Integer> roleIds) {
        userService.grantRole2User(userId,roleIds);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Boolean deleteUser(@PathVariable("id") @Min(value=1,message="id不能小于1") Integer id) {
        userService.delete(id);
        return true;
    }

    //***********************角色接口********************
// 新增/修改角色信息
    @PostMapping("/role")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer saveRole(@RequestBody RoleForm roleForm) {
        Role role = copy(roleForm,Role.class);
        return roleService.saveInfo(role);
    }

    @GetMapping("/role/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public RoleVo findRoleById(@PathVariable("id") @Min(value=1,message="id不能小于1")Integer id) {
        Role role = roleService.findById(id);
        if(null == role){
            throw new BusinessException("角色信息不存在");
        }
        log.info("roleName:"+role.getName());
        log.info("code:"+role.getCode());
        RoleVo vo = copy(role,RoleVo.class);
        return vo;
    }

    @GetMapping("/role")
    @ResponseStatus(value = HttpStatus.OK)
    public List<RoleVo> findAllRole() {
        List<Role> roles = roleService.findAll();
        List<RoleVo> vos = copyList(roles,RoleVo.class);
        return vos;
    }

    @DeleteMapping("/role/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Boolean deleteRole(@PathVariable("id") @Min(value=1,message="id不能小于1") Integer id) {
        roleService.deleteById(id);
        return true;
    }

    @PostMapping("/role/grant/{roleId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void grantResource2Role(@PathVariable("roleId") Integer roleId, @RequestBody List<Integer> resourceIds) {
        roleService.grantResource2Role(roleId,resourceIds);
    }

    //*****************资源接口**************************
// 新增/修改资源信息
    @PostMapping("/resource")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer saveResource(@RequestBody ResourceForm resourceForm) {
        Resource resource = copy(resourceForm,Resource.class);
        return resourceService.save(resource);
    }

    @GetMapping("/resource/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResourceVo findResourceById(@PathVariable("id") @Min(value=1,message="id不能小于1")Integer id) {
        Resource resource = resourceService.findById(id);
        if(null == resource){
            throw new BusinessException("资源信息不存在");
        }
        log.info("resourceName:"+resource.getName());
        log.info("url:"+resource.getUrl());
        log.info("method:"+resource.getMethod());
        ResourceVo vo = copy(resource,ResourceVo.class);
        return vo;
    }

    @GetMapping("/resource")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ResourceVo> findAllResources() {
        List<Resource> resources = resourceService.findAll();
        List<ResourceVo> vos = copyList(resources,ResourceVo.class);
        return vos;
    }

    @DeleteMapping("/resource/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Boolean deleteResource(@PathVariable("id") @Min(value=1,message="id不能小于1") Integer id) {
        resourceService.delete(id);
        return true;
    }
}
