package com.rb.login.model.bo;

import lombok.Data;

@Data
public class ResourceForm {
    private Integer id;
    private String code;
    private String name;
    private String url;
    private String method;
}
