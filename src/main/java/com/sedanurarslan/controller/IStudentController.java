package com.sedanurarslan.controller;

import com.sedanurarslan.dto.DtoStudent;
import com.sedanurarslan.dto.DtoStudentIU;
import com.sedanurarslan.entity.Student;

import java.util.List;

public interface IStudentController {
    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU);

    public List<DtoStudent> getAllStudents();

    public DtoStudent getStudentById(int id);

    public void deleteStudentById(int id);

        public DtoStudent save(int id, DtoStudentIU dtoStudentIU);

}
