package com.henu.examtestsystem.student.repository;

import com.henu.examtestsystem.student.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select t from User t where t.name=?1")
    public User findByName(String name);

    @Query("select t from User t where t.idnumber like?1")
    public User findByIdnumber(String idnumber);

    @Query("select t from User t where t.role like 'admin' or t.role like 'teacher'")
    public List<User> findAdminOrTeacher();
}
