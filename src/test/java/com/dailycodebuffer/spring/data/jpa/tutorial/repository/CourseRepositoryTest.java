package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Course;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Guardian;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;
import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {


    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("veena")
                .lastName("verma")
                .build();
        Course course = Course.builder()
                .credit(5)
                .title("Python")
                .teacher(teacher)
                .build();
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1,2);

        List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords).getContent();

        Long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();

        Integer totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();

        System.out.println("courses : "+ courses);
        System.out.println("totalElements : "+ totalElements);
        System.out.println("totalPages : "+ totalPages);

    }

    @Test
    public void  findAllSort(){
        Pageable sortByTitle =
                PageRequest.of(0,3,Sort.by("title"));
        Pageable sortByCreditDesc =
                PageRequest.of(0, 2, Sort.by("Credit").descending());
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(0,2,
                        Sort.by("title").and(Sort.by("credit")));


        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        System.out.println(courses);
    }

    @Test
    public void printFindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0,10);
        List<Course> course =
                courseRepository.findByTitleContaining(
                "D",
                firstPageTenRecords).getContent();
        System.out.println("course :"+course );
    }


    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("veena")
                .lastName("verma")
                .build();

        Guardian guardian = Guardian.builder()
                .name("tushar")
                .email("tushar@gmail.com")
                .mobile("999999999")
                .build();

        Student student = Student.builder()
                .firstName("Abhishek")
                .lastName("Singh")
                .emailId("abhishek@gmail.com")
                .guardian(guardian)
                .build();

        Course course = Course
                .builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }
}