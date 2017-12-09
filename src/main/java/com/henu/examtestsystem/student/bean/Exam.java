package com.henu.examtestsystem.student.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    /**
     * 考试发起者
     */
    @NotNull
    private String createUser;
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

    /**
     * 是否上传试卷
     */
    private boolean hasPaper;
    /**
     * 是否自动开始
     */
    private boolean isAutostart;
    /**
     * 是否已经归档
     */
    private boolean hasStore;
    /**
     * 是否清理这次考试
     */
    private boolean hasClean;

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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public boolean isHasPaper() {
        return hasPaper;
    }

    public void setHasPaper(boolean hasPaper) {
        this.hasPaper = hasPaper;
    }

    public boolean isAutostart() {
        return isAutostart;
    }

    public void setAutostart(boolean autostart) {
        isAutostart = autostart;
    }

    public boolean isHasStore() {
        return hasStore;
    }

    public void setHasStore(boolean hasStore) {
        this.hasStore = hasStore;
    }

    public boolean isHasClean() {
        return hasClean;
    }

    public void setHasClean(boolean hasClean) {
        this.hasClean = hasClean;
    }
}
