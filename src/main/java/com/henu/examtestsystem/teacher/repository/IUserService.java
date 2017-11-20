package com.henu.examtestsystem.teacher.repository;

import com.henu.examtestsystem.teacher.bean.User;

import java.util.List;

public interface IUserService {

    public List<User> getUserList();

    public User findUserById(Long id);

    public User save(User user);

    public User edit(User user);

    public void delete(Long id);

    public User findUserByName(String name);

    public User findByStuid(String stuid);

}
