package com.traminhthang.dataJpa.repository;

import com.traminhthang.dataJpa.entity.Guardian;
import com.traminhthang.dataJpa.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest thi se luu vao trong csdl
//@DataJpaTest thi khong luu vao trong csdl that
@SpringBootTest
class StudentRepositoryTest {


    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void saveStudent(){
        Guardian guardian = Guardian.builder()
                .name("thuy")
                .email("thuy@gmail.com")
                .mobile("12345141435")
                .build();
        Student student = Student.builder()
                .firstName("Tran")
                .lastName("Tan")
                .emailId("trantan@gmail.com")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void findStudentById(){
        Optional<Student> student = studentRepository.findById(1L);
        System.out.println("student = " + student.toString());
    }

    @Test
    public void findStudentByLastNameAndFirstName(){
        List<Student> student = studentRepository.findStudentByLastNameAndFirstName("Thang","Tran");
        System.out.println("student = " + student);
    }


    @Test
    public void findStudentByFirstName(){
        List<Student> students = studentRepository.findStudentByFirstName("Tran");
        System.out.println("students = " + students);
    }

    @Test
    public void findStudentByLastName(){
        List<Student> students = studentRepository.findStudentByLastNameIgnoreCase("thang");
        System.out.println("students = " + students);
    }

    @Test
    public void findStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findStudentByFirstNameContaining("Tr");
        System.out.println("students = " + students);
    }

    @Test
    public void listStudent(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("Student List "+studentList);
    }

    @Test
    public void findStudentByEmail(){
        Student student = studentRepository.findStudentByEmail("genkiskhan@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void findStudentByEmailNative(){
        Student student = studentRepository.findStudentByEmailNativeQuery("genkiskhan@gmail.com");
        System.out.println("student = " + student);
    }


    @Test
    public void findStudentByEmailUsingNamedParameter(){
        String student = studentRepository.findStudentByEmailUsingNamedParameter("trantan@gmail.com");
        System.out.println("student = " + student);
    }

    @Test
    public void updateStudent(){
        studentRepository.updateStudentLastNameUsingEmail("KAKAKAKAKAKKAAKA","trantan@gmail.com");
    }
}