package com.traminhthang.dataJpa.entity;

import antlr.collections.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//name chi ra day la  ten cua table
//uniqueContrains chi ra rang cac gia tri cua column duoc chi ra
//trong table phai la duy nhat,khong duoc giong nhau,de thuc hien duoc viec do can tao
//ra 1 bang de ghi cac gia tri da co san
//parameter name chi ra ten cua cai bang do
//parameter columnNames chi ra ten cua cot se ap dung uniqueContrains
@Table(
        name = "tb_student",
        uniqueConstraints = @UniqueConstraint(
            name = "email_unique",
            columnNames =  "email_address"
        )
)
public class Student {
    //@Id chi ra day se la primary_key cua entity nay
    //database can co 1 bang rieng de luu cac primary_key nen @SequenceGenerator() se
    //giup chung ra tao bang day,thuc ra ngoai @SequenceGenerator(),con nhieu loai generator nhu
    //@TableGenerator(),...
    //@SequenceGenerator() voi parameter name se chi ra ten cua generator
    //@SequenceGenerator() voi parameter sequenceName se chi ra ten cua bang do
    //(cot do la cot luu gia tri cua primary_key)
    //@GeneratedValue giup ta chi ra primary_key ta mong muon,trong th nay la sequence
    @Id
    @SequenceGenerator(
            name = "student_sequence_generator",
            sequenceName = "student_sequence_value",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            //chinh la gia tri cua thuoc tinh name trong notation @SequenceGenerator
            generator = "student_sequence_generator"
    )
    private Long studentId;
    private String firstName;
    private String lastName;
    //@Column chi ra ten colum tuong ung trong database
    @Column(name = "email_address",nullable = false)
    private String emailId;
    //@Embedded chi ra rang day la entity khac ,hay noi cach khac no cho springboot biet day la moi quan he HAS-A,
    //thong tin cua guardian van luu tai 1 bang tb_student nhu cu,tuy nhien viec tach guardian ra 1 entity rieng giup code de doc
    //hon,cau truc ro rang hon
    @Embedded
    private Guardian guardian;
//    private String guardianName;
//    private String guardianEmail;
//    @Column(nullable = false)//chi ra rang sdt giao vien k duoc de trong
//    private String guardianMobile;
//    khi chay se ra ntn
//    Hibernate: create table student (student_id bigint not null, email_id varchar(255), first_name varchar(255), guardian_email varchar(255), guardian_mobile varchar(255), guardian_name varchar(255), primary key (student_id)) engine=InnoDB
}
