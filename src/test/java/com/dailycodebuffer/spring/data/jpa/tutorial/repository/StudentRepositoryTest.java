package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Guardian;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public  void saveStudent(){
        Student student = Student.builder()
                .emailId("aashish18verma@gmail.com")
                .firstName("Aashish")
                .lastName("verma")
               /* .guardianName("tushar")
                .guardianEmail("tushar@gmail.com")
                .guardianMobile("999999999")*/
                .build();
        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("tushar")
                .email("tushar@gmail.com")
                .mobile("999999999")
                .build();
        Student student = Student.builder()
                .firstName("Aashish")
                .lastName("verma")
                .emailId("aashish@gmail.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void findStudentByName(){
        List<Student> students = studentRepository.findByFirstName("Aashish");
        System.out.println(students);
    }
    @Test
    public void findStudentByNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("Aas");
        System.out.println(students);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student StudentByEmail = studentRepository.getStudentByEmailAddress("aashish@gmail.com");
        System.out.println(StudentByEmail);
    }

    @Test
    public void printStudentFirstNameByEmailAddress(){
        String StudentFNByEmail = studentRepository.getStudentFirstNameByEmailAddress("aashish@gmail.com");
        System.out.println(StudentFNByEmail);
    }


    @Test
    public void printStudentFirstNameByEmailAddressNative (){
        Student StudentFNByEmail = studentRepository.getStudentByEmailAddressNative("aashish@gmail.com");
        System.out.println(StudentFNByEmail);
    }

    @Test
    public void printStudentFirstNameByEmailAddressNativeParam (){
        Student StudentFNByEmail = studentRepository.getStudentByEmailAddressNativeParam("aashish@gmail.com");
        System.out.println(StudentFNByEmail);
    }

    @Test
    public void findStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("tushar");
        System.out.println("Student By Guardian Name :" + students);
    }
    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println(studentList);
    }

    @Test
    public void updateStudentNameByEmailidTest(){
        studentRepository.updateStudentNameByEmailId(
                "shincahan",
                "aashish@gmail.com"
        );
    }


}