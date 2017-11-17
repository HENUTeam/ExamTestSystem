package com.henu.examtestsystem.repository;

import com.henu.examtestsystem.bean.Teacher;

import java.util.List;

public interface ITeacherService {
    public List<Teacher> getTeacherList();

    public Teacher findTeacherById(Long id);

    public Teacher save(Teacher teacher);

    public Teacher edit(Teacher teacher);

    public void delete(Long id);

    public Teacher findTeacherByName(String name);

    public Teacher findByTeaid(String teaid);

}
