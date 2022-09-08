package com.traminhthang.dataJpa.repository;

import com.traminhthang.dataJpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    //JpaRepository<T,ID> chi ho tro nhung ham co san nhu findById,findAllById
    //tuy nhien no cung cap cho ta cac keyword de bien hoa tuy thich
    //VD chung ta co the tao them ham findByLastName khong he co san trong JpaRepository<T,ID>
    //dua vao keyword findBy
    //Note:ten thuoc tinh phai viet hoa cac chu cai dau
    //VD:LastName chu khong phai lastName thi spring moi hieu duoc
    //co rat nhieu keyword cho chung ta tuy bien phuong thuc dua tren cac phong thuc co san tai document
    //https://docs.spring.io/spring-data/jpa/docs/3.0.0-M5/reference/html/#jpa.sample-app.finders.strategies
    public List<Student> findStudentByFirstName(String firstName);
    public List<Student> findStudentByLastNameIgnoreCase(String lastName);
    public List<Student> findStudentByLastNameAndFirstName(String lastName,String firstName);
    public List<Student> findStudentByFirstNameContaining(String letter);

    //duoc ho tro nhieu tu khoa de tuy bien la vay, nhung khi gap nhung truong hop qua kho
    //de truy van du lieu trong database thi ta van can den cac truy van(query)SQL
    //co 2 loai truy van SQL pho bien trong spring la JPQL va native
    //ve co ban ca 2 deu giong truy van sql thong thuong nhung:
    //-JPQL la kieu truy van su dung ten class va ten attribute trong code
    //-Native la kieu truy van su dung ten bang va ten cot trong database

    //JPQL
    //?1 tuc la parameter dau tien cua ham
    @Query("select s from Student s where s.emailId = ?1")
    public Student findStudentByEmail(String email);

    //Native
    @Query(
            value = "SELECT * FROM tb_student s where s.email_address = ?1",
            nativeQuery = true
    )
    public Student findStudentByEmailNativeQuery(String email);

    //Named Parameter
    //khi co nhieu parameter qua thi thay vi su dung ?1,?2,... ta co the dat ten cho parameter do giup de dang su dung trong truy van sql hon
    @Query(
            //bien duoc dat ten se duoc su dung sau dau ":" de spring boot nhan dien duoc bien do
            //trong vd nay la :email
            value = "SELECT first_name FROM tb_student s where s.email_address = :email",
            nativeQuery = true
    )
    //@Param(<ten bien>) dung de dat ten bien trong truy suat (Query)
    public String findStudentByEmailUsingNamedParameter(@Param("email") String email);

    //Khi muon thay doi trong database chung ta can phai khai bao @Query
    //va them vao 1 annotion la @Modifying
    //@Transactional de dam bao khi co thay doi trong database, nhung thay doi nay la hoan toan
    //hop le va khong xay ra loi,no dong vai tro nhu 1 nguoi giam sat, neu xay ra loi thi se khong
    //cho phep thay doi tren database
    @Modifying
    @Transactional
    @Query(
            value = "update tb_student set last_name = :new_last_name where email_address = :email",
            nativeQuery = true
    )
    public int updateStudentLastNameUsingEmail(@Param("new_last_name") String newLastName,@Param("email") String emailID);
}
