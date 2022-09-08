package com.traminhthang.dataJpa.entity;


import com.traminhthang.dataJpa.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_sequence_generator",
            sequenceName = "teacher_sequence_table"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence_generator"
    )
    private Long teacherId;
    private String firstName;
    private String lastName;
    //@OneToMany chi ra rang 1 giao vien co the day nhieu khoa hoc
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherID"
    )
    private List<Course> courses;
}
