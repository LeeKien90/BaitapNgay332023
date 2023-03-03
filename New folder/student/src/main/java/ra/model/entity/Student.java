package ra.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "StudentId")
    private int studentId;
    @Column(name = "StudentName")
    private String studentName;
    @Column(name = "StudentAge")
    private int studentAge;
    @Column(name = "StudentStatus")
    private boolean studentStatus;
}
