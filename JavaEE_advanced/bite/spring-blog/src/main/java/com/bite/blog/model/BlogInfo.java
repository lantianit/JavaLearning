package com.bite.blog.model;

import com.bite.blog.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
public class BlogInfo {

    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
    private boolean isLoginUser;

    public BlogInfo() {
    }

    public BlogInfo(String title, String content, Integer userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public String getCreateTime() {
        return DateUtils.formatDate(createTime);
    }
}
