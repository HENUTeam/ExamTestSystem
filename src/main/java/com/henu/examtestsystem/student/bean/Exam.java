package com.henu.examtestsystem.student.bean;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "exam")
public class Exam {
    public static enum ExamState {
        now, end, future;
    }

    ;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startDate;
    @Column(nullable = false)
    //答案存放路径 文件路径 格式"\path1\path\"
    private String path;
    //试卷存放路径  文件 格式 "\path\name.file"
    @Column(nullable = false)
    private String paperPath;
    //判断教师是否下载答卷
    @Column(nullable = false)
    private boolean isDownload;


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
    private boolean autoStart;
    /**
     * 是否已经归档
     */
    private boolean hasStore;
    /**
     * 是否清理这次考试
     */
    private boolean hasClean;

    @ManyToMany(cascade = {}, fetch = FetchType.EAGER)
    @JoinTable(name = "exam_user",
            joinColumns = {@JoinColumn(name = "exam_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> user;



    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Exam() {
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPaperPath() {
        return paperPath;
    }

    public void setPaperPath(String paperPath) {
        this.paperPath = paperPath;
    }

    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean download) {
        isDownload = download;
    }

    public ExamState getExamState() {
        return examState;
    }

    public void setExamState(ExamState examState) {
        this.examState = examState;
    }

    public boolean isHasPaper() {
        return hasPaper;
    }

    public void setHasPaper(boolean hasPaper) {
        this.hasPaper = hasPaper;
    }

    public boolean isAutoStart() {
        return autoStart;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
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
