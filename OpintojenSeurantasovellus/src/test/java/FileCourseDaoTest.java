import dao.CourseDao;
import dao.FileCourseDao;
import dao.FileStudentDao;
import dao.StudentDao;
import domain.Student;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class FileCourseDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File studentFile;
    StudentDao studentDao;
    
    @Before
    public void setUp() throws Exception {
        studentFile = testFolder.newFile("testfile_students.txt");
        StudentDao fstudentDao = new FakeStudentDao();
        fstudentDao.create(new Student(111, "viivi", "12", "HY", "salasana"));
        
        try (FileWriter file = new FileWriter(studentFile.getAbsolutePath())) {
            file.write("111;viivi;12;HY;salasana\n");
        }
        
        studentDao = new FileStudentDao(studentFile.getAbsolutePath());
    }
    
    @Test
    public void studentsAreReadCorrectly() {
        List<Student> students = studentDao.getAll();
        assertEquals(1, students.size());
        Student student = students.get(0);
        assertEquals("viivi", student.getName());
        assertEquals("12", student.getStudentId());
        assertEquals("HY", student.getUni());
        assertEquals("salasana", student.getPassword());
    }
    

}
