package ra.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student findById(int studentId);
    Student saveOrUpdate(Student student);
    void delete(int studentId);
    List<Student> searchByName(String studentName, int studentId);
    List<Student> sortStudentByName(String direction);
    List<Student> sortStudentByNameAndAge(String directionName,Integer directionAge);
    Page<Student> getPagging(Pageable pageable);
}
