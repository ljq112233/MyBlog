package com.blog.pojo;
/**
 * @author Lu Jianqiang
 * @date 2021/7/25 19 31
 * discription
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName User
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/25 19:31
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;

    private List<Blog> blogs = new ArrayList<>();

}
