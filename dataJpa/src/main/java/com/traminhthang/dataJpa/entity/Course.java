package com.traminhthang.dataJpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    //khoi ta primary key cho bang course
    @Id
    @SequenceGenerator(
            name = "course_sequence_generator",
            sequenceName = "course_sequence_value"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence_generator"
    )
    private Long courseID;
    private String title;
    private String body;
    @OneToOne(
            //mappedBy chỉ ra rằng thuoc tính courseMaterial của table course được ánh xạ đến thuộc tính course của table courseMaterial
            //mappedBy = "course",
            //FetchType.LAZY nghia la khi lay du lieu tu database se chi lay du lieu cua course thoi,khong lay
            //du lieu tu course material
            fetch = FetchType.EAGER,
            //cascade chi ra rang khi luu thong tin cua course thi thong tin cua
            //courseMaterial cung se duoc luu vao database
            cascade = CascadeType.ALL,
            optional = false//chi ra rang courseMaterial la bat buoc phai co,no khong phai optional
    )
    @JoinColumn(
            //name se la ten cua cot se them vao trong bang course material nay
            name = "course_material_id",
            //referencedColumnName la ten khoa chinh cua bang course
            referencedColumnName = "courseMaterialId"
    )
    private CourseMaterial courseMaterial;


    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    //doi voi moi quan he nhieu-nhieu thay vi su dung 2 bang nhu nhieu-mot,mot-mot thi ta can su dung them bang thu 3
    //bang thu 3 nay luu gia tri khoa chinh(primary key) cua 2 bang co quan he hieu nhieu vs nhau,
    //@JoinTable la dung de them bang thu 3 do
    @JoinTable(
            name = "student_course_map",
            //day la 2 cot su dung de luu khoa chinh cua 2 bang tren
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseID"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> studentList;
}
