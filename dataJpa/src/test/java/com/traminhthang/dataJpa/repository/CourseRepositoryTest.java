package com.traminhthang.dataJpa.repository;

import com.traminhthang.dataJpa.entity.Course;
import com.traminhthang.dataJpa.entity.CourseMaterial;
import com.traminhthang.dataJpa.entity.Student;
import com.traminhthang.dataJpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourse(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourse (){
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .courseMaterialId(10L)
                .url("www.math.com")
                .build();
        Course course = Course.builder()
                .courseID(5L)
                .body("This is math course")
                .title("py")
                .courseMaterial(courseMaterial)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void findAllPaginationWithThreeRecord(){
        //Spring co the chia 1 table nao do trong database thanh nhieu page nho,moi page chua 1 so luong record nhat dinh tuy ta quyet dinh
        //cau lenh duoi day tuc la database duoc chia lam cac page voi size 3 (parameter thu 2) va doi tuong firstPageWithThreeRecords la page co chi so 2
        //VD voi database co 7 record chia thanh cac page trong do moi page chua 3 record thi
        // +page 1 chua:record 0,record 1,record 2
        // +page 2 chua:record 3,record 4,record 5
        // +page 3 chua:record 6
        // va cau lenh duoi day dang khai bao 1 doi tuong thuoc page 3(chi so 2 thi la page 3)
        Pageable firstPageWithThreeRecords =
                PageRequest.of(2,3);
        //getContent() se tra ve tat ca record trong page
        List<Course> courses =
                courseRepository.findAll(firstPageWithThreeRecords).getContent();
        //getTotalElements() tra ve tong so record co trong database
        //output = 7
        long totalElement  =
                courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        //getTotalPages() tra ve tong so page co trong database
        //output = 3
        long totalPage =
                courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();
        System.out.println("courses = " + courses);
        System.out.println("totalPage = " + totalPage);
        System.out.println("totalElement = " + totalElement);
    }

    @Test
    public void findAllPaginationWithTwoRecord(){
        //Spring co the chia 1 table nao do trong database thanh nhieu page nho,moi page chua 1 so luong record nhat dinh tuy ta quyet dinh
        //cau lenh duoi day tuc la database duoc chia lam cac page voi size 2 (parameter thu 2) va doi tuong firstPageWithThreeRecords la page co chi so 0(parameter thu 1)
        //VD voi database co 7 record chia thanh cac page trong do moi page chua 2 record thi
        // +page 1 chua:record 0,record 1
        // +page 2 chua:record 2,record 3
        // +page 3 chua:record 4,record 5
        // +page 4 chua:record 6
        // va cau lenh duoi day dang khai bao 1 doi tuong thuoc page 1(chi so 0 thi la page 1)
        Pageable firstPageWithTwoRecords =
                PageRequest.of(0,2);
        //getContent() se tra ve tat ca record trong page
        List<Course> courses =
                courseRepository.findAll(firstPageWithTwoRecords).getContent();
        //getTotalElements() tra ve tong so record co trong database
        //output =  7
        long totalElement  =
                courseRepository.findAll(firstPageWithTwoRecords).getTotalElements();
        //getTotalPages() tra ve tong so page co trong database
        //output = 4
        long totalPage =
                courseRepository.findAll(firstPageWithTwoRecords).getTotalPages();
        System.out.println("courses = " + courses);
        System.out.println("totalPage = " + totalPage);
        System.out.println("totalElement = " + totalElement);
    }

    //thay vi sap xep theo thu tu mac dinh trong database, cac page cung co the sap xep theo thu tu nao do neu chung ta muon
    //va day la cac chung ta lam cac page sap xep theo thu tu do
    @Test
    public void findAllPaginationSorted(){
        //firstPageWithTwoRecordSortByTitle la 1 doi tuong khi 1 table nao do trong databaes duoc sap xep theo thuoc tinh title va chia lam cac page,
        //moi page chua 2 record,tuong tu nhu tren thoi nhung la co sap xep theo thu tu nao do
        Pageable firstPageWithTwoRecordSortByTitle =
                PageRequest.of(0,2, Sort.by("title"));
        List<Course> courseByTitle = courseRepository.findAll(firstPageWithTwoRecordSortByTitle).getContent();
        System.out.println("firstPageWithTwoRecordSortByTitle = " + courseByTitle);
        //firstPageWithTwoRecordSortByBody giong firstPageWithTwoRecordSortByTitle nhung la sap theo thuoc tinh body va co chi so la 1
        Pageable firstPageWithTwoRecordSortByBody =
                PageRequest.of(1,2,Sort.by("body"));
        List<Course> courses2 = courseRepository.findAll(firstPageWithTwoRecordSortByBody).getContent();
        System.out.println("firstPageWithTwoRecordSortByBody = " + courses2);
    }

    @Test
    public void printfindByTitleContaining(){
        Pageable pageWithFirstFiveRecord = PageRequest.of(0,5);
        //phuong thuc findByTitleContaining tra ve 1 Page nhung sau khi .getContent() thi
        //se tra ve 1 List
        List<Course> courses = courseRepository.findByTitleContaining("D",pageWithFirstFiveRecord).getContent();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacherAndStudent(){

        Student student1 = Student.builder()
                .studentId(5L)
                .emailId("nonono@gmail.com")
                .firstName("Ngo")
                .lastName("Chinh")
                .build();

        Student student2 = Student.builder()
                .studentId(6L)
                .emailId("hehehe@gmail.com")
                .firstName("Vu")
                .lastName("Viet")
                .build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .courseMaterialId(6L)
                .url("www.chemical.com")
                .build();

        Course course = Course.builder()
                .courseID(7L)
                .title("Hoa hoc")
                .body("Day la mon hoa hoc")
                .courseMaterial(courseMaterial)
                .studentList(List.of(student1,student2))
                .build();
        courseRepository.save(course);
    }
}
