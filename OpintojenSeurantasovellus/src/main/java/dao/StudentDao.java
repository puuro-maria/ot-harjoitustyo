
package dao;

import domain.Student;
import java.util.List;

public interface StudentDao {
    
    Student create(Student student) throws Exception;
    
    Student findByStudentId(String studentId);
    
    List<Student> getAll();
    
}
