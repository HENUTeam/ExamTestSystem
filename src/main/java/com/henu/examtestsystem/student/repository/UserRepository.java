package com.henu.examtestsystem.student.repository;

import com.henu.examtestsystem.student.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select t from User t where t.name=?1")
    public List<User> findByName(String name);

    @Query("select t from User t where t.idnumber like?1")
    public User findByIdnumber(String idnumber);

    @Query("select t from User t where t.role like 'admin' or t.role like 'teacher'")
    public List<User> findAdminOrTeacher();

    @Query("select t from User t where t.idnumber=?1 and t.name=?2 and t.role like 'student'")
    public List<User> findByIdnumberAndName(String idnumber,String name);

    /***
     * 查询已登陆的学生的信息
     * ip为空代表未登录
     * @param idnumber
     * @param name
     * @return
     */
    @Query("select t from User t where t.idnumber=?1 and t.name=?2 and t.role like 'student' and t.ip <> null")
    public List<User> findByIdnumberAndNameAndIp(String idnumber,String name);

    @Query("select t from User t where t.name=?1 and t.role like 'student' and t.ip <> null")
    public List<User> findByNameAndIp(String name);

    @Query("select t from User t where t.idnumber like?1 and t.role like 'student' and t.ip <> null")
    public List<User> findByIdnumberAndIp(String idnumber);

    @Query("select t from User t where t.ip=?1 ")
    public List<User> findByIp(String ip);
}
