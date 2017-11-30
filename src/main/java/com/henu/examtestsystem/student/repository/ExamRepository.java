package com.henu.examtestsystem.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.henu.examtestsystem.student.bean.Exam;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long>{

}
