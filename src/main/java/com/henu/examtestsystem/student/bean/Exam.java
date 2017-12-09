package com.henu.examtestsystem.student.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "exam")
public class Exam {
    public static enum ExamState {
        now,end,future;
    };
    @Column(nullable = false)
    private String subject;
    @Id
    @GeneratedValue
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start_date;

    @Column(nullable = false)
    private String path; //答案存放路径 文件路径 格式"\path1\path\"

    @Column(nullable = false)
    private String paper_path; //试卷存放路径  文件 格式 "\path\name.file"

    @Column(nullable = false)
    boolean is_download; //判断教师是否下载答卷


    @Column(nullable = false)

    @Enumerated(EnumType.STRING)
    private ExamState examState;

    public String getPaper_path() {
        return paper_path;
    }

    public void setPaper_path(String paper_path) {
        this.paper_path = paper_path;
    }


    public ExamState getExamState() {
        return examState;
    }

    public void setExamState(ExamState examState) {
        this.examState = examState;
    }





    public Exam(String subject, Date start_date, String path) {
        this.subject = subject;
        this.start_date = start_date;
        this.path = path;
    }

    public Exam(String subject, Date start_date) {
        this.subject = subject;
        this.start_date = start_date;
    }

    public Exam() {
    }
    public boolean isIs_download() {
        return is_download;
    }

    public void setIs_download(boolean is_download) {
        this.is_download = is_download;
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


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
