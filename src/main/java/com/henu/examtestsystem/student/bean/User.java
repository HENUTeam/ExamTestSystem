package com.henu.examtestsystem.student.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    public static enum Sex {
        男,//老师
        女;//学生
    };

    public static enum Role {
        student, teacher, admin;
    }

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String idnumber;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdate;

    @Column(nullable = false) //注意该getset 方法
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false) //注意该getset 方法
    @Enumerated(EnumType.STRING)
    private Sex sex;
    //    @Column(nullable = false)
    private String IDCard;
    @Column
    private String ip;//用于ip绑定 差一个考试信息

    public User() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
