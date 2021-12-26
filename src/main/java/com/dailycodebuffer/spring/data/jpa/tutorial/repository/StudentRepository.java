package com.dailycodebuffer.spring.data.jpa.tutorial.repository;

import com.dailycodebuffer.spring.data.jpa.tutorial.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.lang.annotation.Native;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    public List<Student> findByFirstName(String firstName);

    public List<Student> findByFirstNameContaining(String Student);

    public List<Student> findByLastNameNotNull();

    public List<Student> findByGuardianName(String Student);

    public List<Student> findByFirstNameAndLastName(String firstName, String LastName);


    //Jpql
    @Query("select s from Student s where s.emailId = ?1")
    public Student getStudentByEmailAddress(String emailId);


    //Jpql
    @Query("select s.firstName from Student s where s.emailId = ?1")
    public String getStudentFirstNameByEmailAddress(String emailId);

    //Native
    @Query(
            value ="select * from tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);

    //Native with param
    @Query(
            value ="select * from tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeParam(
            @Param("emailId") String emailId);

    @Modifying
    @Transactional
    @Query(
           value ="update tbl_student set first_name =?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String email);
}
