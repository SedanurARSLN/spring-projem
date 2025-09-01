package com.sedanurarslan.controller.impl;

import com.sedanurarslan.controller.IStudentController;
import com.sedanurarslan.dto.DtoStudent;
import com.sedanurarslan.dto.DtoStudentIU;
import com.sedanurarslan.entity.Student;
import com.sedanurarslan.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest/api/student")
public class StudentControllerImpl implements IStudentController {

    @Autowired
    private IStudentService studentService;

    @PostMapping("/save")
    @Override
    public DtoStudent saveStudent(@RequestBody DtoStudentIU dtoStudentIU) {
       return studentService.saveStudent(dtoStudentIU);
    }

    @GetMapping("/list")
    @Override //Interface’ten (ya da parent class’tan) gelen bu metodu kendi mantığımla yeniden tanımlıyorum.”
    public List<DtoStudent> getAllStudents() {
        List<DtoStudent> studentList = studentService.getAllStudents();
        return studentList;
    }

    @GetMapping("/id/{id}")
    @Override
    public DtoStudent getStudentById(@PathVariable int id) {
    //Optional:Veritabanında bir kayıt arıyorsanız ve olmayabilir(findById)
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteStudentById(@PathVariable int id) {
        studentService.deleteStudentById(id);

    }

    @Override
    @PatchMapping("/update/{id}")
    public DtoStudent save(@PathVariable(name = "id")int id, @RequestBody  DtoStudentIU dtoStudentIU) {
        return studentService.updateStudent(id, dtoStudentIU);
    }

}
