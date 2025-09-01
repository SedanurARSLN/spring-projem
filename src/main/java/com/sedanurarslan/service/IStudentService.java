package com.sedanurarslan.service;

import com.sedanurarslan.dto.DtoStudent;
import com.sedanurarslan.dto.DtoStudentIU;
import com.sedanurarslan.entity.Student;

import java.util.List;

public interface IStudentService {
//İçinde sadece ne yapılacağını (metod imzaları) söylüyor, nasıl yapılacağını söylemiyor.
    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU);

    public List<DtoStudent> getAllStudents();

    public DtoStudent getStudentById(int id);

    public void deleteStudentById(int id);

    public DtoStudent updateStudent(int id,DtoStudentIU dtoStudentIU);

}
