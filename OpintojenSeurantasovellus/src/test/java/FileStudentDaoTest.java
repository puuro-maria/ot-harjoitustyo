
import dao.FileStudentDao;
import dao.StudentDao;
import domain.Student;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


public class FileStudentDaoTest {
    
       
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File studentFile;
    StudentDao studentDao;
    
    @Before
    public void setUp() throws Exception {
        studentFile = testFolder.newFile("testfile_students.txt");
        
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
    
    @Test
    public void getAllWorks() {
        try {
            studentDao.create(new Student(1, "niklas", "0", "SAMK", "salasanna"));
        } catch (Exception e) {
            
        }
        List<Student> studentlist = studentDao.getAll();
        assertTrue(studentlist.size() == 2 & studentlist.get(1).getName().equals("niklas"));
    }
    
    @Test
    public void findByStudentIdWorks() {
        Student s = studentDao.findByStudentId("12");
        assertEquals("viivi", s.getName());
    }
    
    @After
    public void tearDown() {
        studentFile.delete();
    }
}
