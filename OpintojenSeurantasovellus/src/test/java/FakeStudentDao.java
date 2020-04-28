import java.util.ArrayList;
import java.util.List;

import dao.StudentDao;
import domain.Student;

public class FakeStudentDao implements StudentDao {
    
    List<Student> students = new ArrayList<>();
    
    public FakeStudentDao(){
        students.add(new Student(1, "Feikki", "1", "HY", "salasana"));
    }

    @Override
    public Student create(Student student) throws Exception {
        students.add(student);
        return student;
    }

    @Override
    public Student findByStudentId(String studentId) {
        return students.stream().filter(s -> s.getStudentId().equals(studentId)).findFirst().orElse(null);
    }

    @Override
    public List<Student> getAll() {
        return students;
    }
    
 
    
}
