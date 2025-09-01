package com.sedanurarslan.service.impl;

import com.sedanurarslan.dto.DtoStudent;
import com.sedanurarslan.dto.DtoStudentIU;
import com.sedanurarslan.entity.Student;
import com.sedanurarslan.repository.IStudentRepository;
import com.sedanurarslan.service.IStudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {
    // Interface'teki metotları BURADA yazmak zorundayım

    @Autowired
    private IStudentRepository studentRepository;

    @Override
    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU) {//DtoStudentIU:Input Dto yani kullanıcıdan gelen veriyi taşımak için
        Student student = new Student();//Entitydir.Veritbanıyla çalışmak için önce bir Student nesnesi oluşturuyoruz studentRepository sadece Entity yani (Student)ile çalışır. DTO-->Entity
        BeanUtils.copyProperties(dtoStudentIU, student);//Dto içindeki alanları(name,surname)otom. olarak student entitysine kopyalıyor. Set metodundan kurtuduk

        Student dbStudent= studentRepository.save(student);//student entitysini veritabanına kaydediyoruz.Kaydedilen nesneyi dbStudent olarak geri döndük.DB ye kaydedidikten sonra id otom. set edildi
        // “Senin kaydın alındı, işte kaydedilen öğrenci bilgilerin” demek için.DB’nin set ettiği değerleri göstermek için→ ID, tarih, vb..
        DtoStudent response = new DtoStudent();//Kullanıcıya cevap dönecez ama entityi direkt dönmüyoruz bunun yerine DtoStudent adında output dto kullanırız.İçinde istemediğimiz bilgileri gizleyip gösterir.

        BeanUtils.copyProperties(dbStudent, response);//DBden dönen entitynin(dbStudent)alanlarını, response DTO suna kopyalıyoruz böylce kulanıcıya sadece istediğimiz alanları döneriz

        return response;//student → dışarıdan gelen input’u entity’ye çevirmek için.
                        //dbStudent → DB’ye kaydedilmiş hal (id gibi alanlar eklenmiş).
                       //response (DtoStudent) → dış dünyaya güvenli bir şekilde göstereceğimiz OUTPUT DTO versiyon.
    }

    @Override
    public List<DtoStudent> getAllStudents() {
        List<DtoStudent> dtoList = new ArrayList<>();//Dış dünyaya entity deil Dto göndereceğim için yeni bir liste hazırlar.

        List<Student> studentList = studentRepository.findAllStudent();//Veritabanındaki tüm öğrencileri çeker.Dönen liste Student türünde Entity listesi
        for (Student student : studentList) {//DB den gelen her öğrenciyi tek tek dolaşıyoruz
            DtoStudent dto = new DtoStudent();//Önce boş bir DTO oluşturduk.
            BeanUtils.copyProperties(student, dto);//entitynin alanların DTOya kopyala.
            dtoList.add(dto);//DTOyu listeye ekliyoruz.Yani List<Student>-->List<DtoStudent> dönüşümü yaparız.
        }
        return dtoList;//Dış dünyaya sadece DTO listesi döneriz.
//Kısacası DBden gelen ham entity listesini-->dış dünyaya verilebilecek DTO listesine dönüştürüyoruz.
    }

    @Override
    public DtoStudent getStudentById(int id) {
        DtoStudent dto = new DtoStudent();

    Optional<Student> student = studentRepository.findStudentById(id);//Optional ile var mı yok mu kontrol etti

    if(student.isPresent()){//Eğer öğrenci varsa true olur
        Student dbStudent= student.get();//student.get ile Optional içinden entityi alırız.Artık elimde DB den gelen öğrenci var
        BeanUtils.copyProperties(dbStudent, dto);//DB den gelen entity alanını DTO ya kopyala.Böylece dış dünyaya göndereceğim dto hazır.

    }
        return dto;//En sonda DTO döneriz.Eğer öğrenci yoksa boş DTO döner çünkü en başta newledik ama içine kopyalanacak değer olmadı
    }

    @Override
    public void deleteStudentById(int id) {
        Optional<Student> optional= studentRepository.findById(id);
                if(optional.isPresent()){
                    studentRepository.delete(optional.get());

                }

    }

    @Override
    public DtoStudent updateStudent(int id, DtoStudentIU dtoStudentIU) {
        DtoStudent dto = new DtoStudent();
        Optional<Student> optional= studentRepository.findById(id);
        if(optional.isPresent()){
            Student dbStudent = optional.get();
    dbStudent.setName(dtoStudentIU.getName());
    dbStudent.setSurname(dtoStudentIU.getSurname());
    Student updateStudent = studentRepository.save(dbStudent);
            BeanUtils.copyProperties(updateStudent, dto);
            return dto;
        }
        return null;

        /*//Student updateStudent: Güncellenmiş verileri tutan öğrenci nesnesi. Yani dışarıdan gelen “yeni bilgiler”.
        Student dbStudent = getStudentById(id);//Bu metodun işi: Veritabanında o id’ye sahip öğrenci var mı bakmak.

       // Dönen değer dbStudent isimli bir değişkene atanıyor. Burada mantık şu: Eğer öğrenci bulunursa dbStudent içinde bir Student nesnesi olur.
        if(dbStudent != null){
      /*veritabanındaki öğrenci nesnesi adı*/ //dbStudent.setName(updateStudent.getName());//dışarıdan gelen nesnenin “yeni adı”.

          //  dbStudent.setSurname(updateStudent.getSurname());

          //  studentRepository.save(dbStudent);
        }


       // return null;
    }



