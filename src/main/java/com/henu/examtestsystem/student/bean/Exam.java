package com.henu.examtestsystem.student.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exam")
public class Exam {
    @Column(nullable = false)
    private String subject;
    @Id
    @GeneratedValue
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start_date;
    @Column(nullable = false)
    private Long exam_len; //考试时长
    @Column
    private String path; //考卷存放路径

    public Exam(String subject, Date start_date, Long exam_len, String path) {
        this.subject = subject;
        this.start_date = start_date;
        this.exam_len = exam_len;
        this.path = path;
    }

    public Exam(String subject, Date start_date, Long exam_len) {
        this.subject = subject;
        this.start_date = start_date;
        this.exam_len = exam_len;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Long getExam_len() {
        return exam_len;
    }

    public void setExam_len(Long exam_len) {
        this.exam_len = exam_len;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
