package com.example.dljproject01.dto;

import lombok.Data;

@Data  //注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
public class User {
    private String id;
    private String userId;
    private String userName;
    private Integer age;
    private String address;
    private String password;
}
