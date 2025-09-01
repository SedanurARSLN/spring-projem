package com.sedanurarslan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;// id yazılmaz body de çünkü veritabanı id artacak şekilde kaydeder.

    @Column( nullable = false, length = 50)
    private String name;// Değişken adı postamanda body kısmına yazılır.

    //Column eklemezsen değişken ismini otomatik atar.
    @Column(nullable = false)
    private String surname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    private Date dateofBirth;
}
