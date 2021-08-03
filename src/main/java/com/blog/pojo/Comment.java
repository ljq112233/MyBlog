package com.blog.pojo;/**
 * @author Lu Jianqiang
 * @date 2021/7/25 19 32
 * discription
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName Comment
 * @Description TODO
 * @Author Lu Jianqiang
 * @Date 2021/7/25 19:32
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private boolean adminComment;//is admin comment

//    头像
    private String avatar;
    private Date createTime;
    private Long blogId;
    private Long parentCommentId;
    private String parentNickname;

//  父评论
    private Comment parentComment;
    private Blog blog;

}
