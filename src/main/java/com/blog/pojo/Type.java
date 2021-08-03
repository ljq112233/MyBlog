package com.blog.pojo;/**
 * @author Lu Jianqiang
 * @date 2021/7/25 19 32
 * discription
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Type
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/25 19:32
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();
}
