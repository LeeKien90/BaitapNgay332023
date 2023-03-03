package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Student;
import ra.model.service.StudentService;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.getAll();
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return studentService.findById(studentId);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.saveOrUpdate(student);
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student){
        Student studentUpdate = studentService.findById(studentId);
        studentUpdate.setStudentName(student.getStudentName());
        studentUpdate.setStudentAge(student.getStudentAge());
        studentUpdate.setStudentStatus(student.isStudentStatus());
        return studentService.saveOrUpdate(studentUpdate);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId){
        studentService.delete(studentId);
    }

    @GetMapping("/search")
    public List<Student> searchByNameOrId(@RequestParam("studentName") String studentName, @RequestParam("studentId") int studentId){
        return studentService.searchByName(studentName,studentId);
    }

    @GetMapping("/sortByName")
    public ResponseEntity<List<Student>> sortBookByBookName(@RequestParam("direction") String direction) {
        List<Student> listStudents = studentService.sortStudentByName(direction);
        return new ResponseEntity<>(listStudents, HttpStatus.OK);
    }

    @GetMapping("/sortByNameAndPrice")
    public ResponseEntity<List<Student>> sortBookByNameAndPrice(@RequestParam("directionName") String directionName,
                                                              @RequestParam("directionPrice") int directionAge) {
        List<Student> listStudents = studentService.sortStudentByNameAndAge(directionName, directionAge);
        return new ResponseEntity<>(listStudents, HttpStatus.OK);
    }

    @GetMapping("/getPaggingAndSortByName")
    public ResponseEntity<Map<String,Object>> getPaggingAndSortByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam String direction){
        Sort.Order order;
        if (direction.equals("asc")){
            order=new Sort.Order(Sort.Direction.ASC,"bookName");
        }else{
            order=new Sort.Order(Sort.Direction.DESC,"bookName");
        }
        Pageable pageable = PageRequest.of(page,size,Sort.by(order));
        Page<Student> pageStudent = studentService.getPagging(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("student",pageStudent.getContent());
        data.put("total",pageStudent.getSize());
        data.put("totalItems",pageStudent.getTotalElements());
        data.put("totalPages",pageStudent.getTotalPages());
        return  new ResponseEntity<>(data,HttpStatus.OK);
    }

}
