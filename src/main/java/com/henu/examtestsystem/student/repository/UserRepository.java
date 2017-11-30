package com.henu.examtestsystem.student.repository;

import com.henu.examtestsystem.student.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select t from User t where t.name=?1")
    public User findByName(String name);

    @Query("select t from User t where t.idnumber like?1")
    public User findByStuid(String idnumber);
}
