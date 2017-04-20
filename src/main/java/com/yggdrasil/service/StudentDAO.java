package com.yggdrasil.service;

import com.yggdrasil.entity.Student;
import com.yggdrasil.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yggdrasil on 2017/4/20.
 */
@Service
public class StudentDAO {
    @Resource
    private StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
