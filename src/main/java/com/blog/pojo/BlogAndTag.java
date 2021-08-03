package com.blog.pojo;/**
 * @author Lu Jianqiang
 * @date 2021/7/25 19 32
 * discription
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName BlogAndTag
 * @Description 把博客和标签关系存到数据库中使用的类
 * @Author Lu Jianqiang
 * @Date 2021/7/25 19:32
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAndTag {
//    把博客和标签关系存到数据库中使用的类
    private Long tagId;

    private Long blogId;
}
