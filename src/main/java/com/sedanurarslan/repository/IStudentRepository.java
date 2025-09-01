package com.sedanurarslan.repository;

import com.sedanurarslan.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository extends JpaRepository<Student, Integer> {
//Burada kendi özel metodlarımı yazabiliriz.
//HQL:Sınıfın ismi ve değişkenlerin ismi kullanarak sorgular yazılır. nativeQuery false
//SQL: Tablo isimleri ve tablo içindeki kolon adları kulanılaral srgular yazılır.nativeQuery true

    @Query(value = "from Student", nativeQuery = false )
    List<Student> findAllStudent();

    @Query(value = "from Student s where s.id=:studentId ")
    Optional<Student> findStudentById(int studentId);



}
