package com.henu.examtestsystem.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.henu.examtestsystem.student.bean.Exam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

}
