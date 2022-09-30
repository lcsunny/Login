package com.rb.login.model.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Resource {
    private Integer id;
    private String code;
    private String name;
    private String url;
    private String method;
}
