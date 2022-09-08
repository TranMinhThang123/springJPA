package com.traminhthang.dataJpa.repository;

import com.traminhthang.dataJpa.entity.Course;
import com.traminhthang.dataJpa.entity.CourseMaterial;
import com.traminhthang.dataJpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        //B1:Tao courseMaterial nhung khong co thuoc tinh course vi ta khong yeu cau no bat buoc co
        //B2:Tao course + thuoc tinh courseMaterial vi ta yeu cau thuoc tinh nay la bat buoc di kem,
        //B3:Luu thong tin cua course vao database va database se tu dong luu thong tin cua courseMaterial vi tham so mappedBy da chi ra dieu do

        //Luu y:
        // -courseMaterial duoc mappedBy course nhung course khong duoc mapped nguoc lai vi ta coi no la bang chinh
        // -course se luon xuat hien trong database truoc courseMaterial vi no la bang chinh
        CourseMaterial courseMaterial1 = CourseMaterial.builder()
                .courseMaterialId(2L)
                .url("www.html.com")
                .build();

        CourseMaterial courseMaterial2 = CourseMaterial.builder()
                .courseMaterialId(3L)
                .url("www.css.com")
                .build();

        Course htmlCourse = Course.builder()
                .courseID(2L)
                .body("html")
                .title("This is html course")
                .courseMaterial(courseMaterial1)
                .build();

        Course cssCourse = Course.builder()
                .courseID(5L)
                .body("css")
                .title("This is css course")
                .courseMaterial(courseMaterial2)
                .build();


        Teacher teacher = Teacher.builder()
                .teacherId(2L)
                .firstName("Phuong")
                .lastName("Thuy")
                .courses(List.of(htmlCourse))
                .build();

        teacherRepository.save(teacher);
    }

}

