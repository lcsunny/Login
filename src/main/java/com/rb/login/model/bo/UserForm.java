package com.rb.login.model.bo;

import lombok.Data;

@Data
public class UserForm {
    private Integer id;
    private String phone;
    private String password;
    private String userName;
}
