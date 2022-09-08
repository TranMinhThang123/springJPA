package com.traminhthang.dataJpa.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "course")//tuy ta khong lay du lieu tu thuoc tinh course nhung phuong thuc toString khong biet dieu do,ta can chi ra cho
//no biet
public class CourseMaterial {

    //khoi tao primary key cho bang courseMaterial
    @Id
    @SequenceGenerator(
            name = "course_material_sequence_generator",
            sequenceName = "course_material_sequence_value"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_materialId_sequence_generator"
    )
    private Long courseMaterialId;
    private String url;

    //lien ket course material voi course thong qua foreign key
//    @OneToOne the hien day la moi quan he 1vs1
//    @JoinColumn the hien day la khoa ngoai(foreign key cua bang courseMaterial,no la primary key
//    cua bang course)
    @OneToOne(
//            cascade chi ra rang khi luu thong tin cua course material thi thong tin cua
//            course cung se duoc luu vao database
            cascade = CascadeType.ALL,
            //FetchType.LAZY nghia la khi lay du lieu tu database se chi lay du lieu cua course material thoi,khong lay
            //du lieu tu course
            fetch = FetchType.LAZY,
            //mappedBy chỉ ra rằng thuoc tính course của table courseMaterial được ánh xạ đến thuộc tính courseMaterial của table course
            mappedBy = "courseMaterial"
    )
    private Course course;
}
