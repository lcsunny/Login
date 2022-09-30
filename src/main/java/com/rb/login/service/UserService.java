package com.rb.login.service;

import com.rb.login.model.entity.LoginRecord;
import com.rb.login.model.entity.Resource;
import com.rb.login.model.entity.Role;
import com.rb.login.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDetails loadUserByUsername(String userName);

    Integer countLogin(String username,String tokenId);

    boolean checkLoginUser(String url,String tokenId,String method);

    void saveLogin(String username,String tokenId);

    void addLogin(String userName);

    void grantRole2User(Integer userId, List<Integer> roleIds);

    List<Role> findRoleByUserId(Integer userId);

    List<Resource> findResourceByUserId(Integer userId);

    List<LoginRecord> findAllLoginInfo();

    Integer save(User user);

    User findById(Integer id);

    User findByUserName(String userName);

    List<User> findByPage();

    int count();

    void delete(Integer userId);

    long countByUserName(String userName);

}
