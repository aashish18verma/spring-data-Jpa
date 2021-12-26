package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(5)
                .build();
        Course courseJAVA = Course.builder()
                .title("JAVA")
                .credit(6)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("veena")
                .lastName("verma")
                //.courses(List.of(courseDBA,courseJAVA))
                .build();

        teacherRepository.save(teacher);

    }
}