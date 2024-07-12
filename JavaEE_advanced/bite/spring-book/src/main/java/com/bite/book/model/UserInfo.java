package com.bite.book.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Integer id;
    private String userName;
    private String password;
    private Integer delete_flag;
    private Date createTime;
    private Date updateTime;
}
