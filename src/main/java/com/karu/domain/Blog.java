package com.karu.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @description: create Blog domain
 * @author: StevenWu
 * @create: 2019-05-10 15:42
 **/
@Entity
@Table(name="t_blog")
public class Blog {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;
    private Date createTime;
    private Date updateTime;

    public Blog() {
    }


}
