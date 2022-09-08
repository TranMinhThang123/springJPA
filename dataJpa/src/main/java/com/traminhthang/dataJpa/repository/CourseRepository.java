package com.traminhthang.dataJpa.repository;

import com.traminhthang.dataJpa.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    //giong nhu da noi, Spring boot cho phep ta tuy bien cac phuong thuc dua vao cac tu
    //khoa da co san
    //day la 1 vi du nhu the
    //method nay se tim kiem trong input Pageable(parameter thu 2) xem co course nao
    //co title chua letter khong(parameter thu 1) va tra ve 1 page chua cac course do
    Page<Course> findByTitleContaining(String letter, Pageable pageable);
}
