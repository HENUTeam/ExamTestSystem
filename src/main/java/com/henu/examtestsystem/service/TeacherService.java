package com.henu.examtestsystem.service;

import com.henu.examtestsystem.bean.Teacher;
import com.henu.examtestsystem.repository.ITeacherService;
import com.henu.examtestsystem.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements ITeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getTeacherList() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findTeacherById(Long id) {
        return teacherRepository.findOne(id);
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher edit(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.delete(id);
    }

    @Override
    public Teacher findTeacherByName(String name) {
        return teacherRepository.findByName(name);
    }

    @Override
    public Teacher findByTeaid(String teaid) {
        return teacherRepository.findByTeaid(teaid);
    }
}
