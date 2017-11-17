package com.henu.examtestsystem.repository;

import com.henu.examtestsystem.bean.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select t from Teacher t where t.name=?1")
    public Teacher findByName(String name);

    @Query("select t from Teacher t where t.teaid=?1")
    public Teacher findByTeaid(String teaid);

}
