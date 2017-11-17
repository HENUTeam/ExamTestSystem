package com.henu.examtestsystem.service;

import com.henu.examtestsystem.bean.User;
import com.henu.examtestsystem.repository.IUserService;
import com.henu.examtestsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }


    public User edit(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public User findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User findByStuid(String stuid) {
        return userRepository.findByStuid(stuid);
    }


}
