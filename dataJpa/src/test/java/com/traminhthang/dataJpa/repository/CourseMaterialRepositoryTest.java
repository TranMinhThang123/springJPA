package com.traminhthang.dataJpa.repository;

import com.traminhthang.dataJpa.entity.Course;
import com.traminhthang.dataJpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;


    @Test
    public void printCourseMaterial(){
        List<CourseMaterial> courseMaterial = courseMaterialRepository.findAll();
        System.out.println("courseMaterial = " + courseMaterial);
    }
}