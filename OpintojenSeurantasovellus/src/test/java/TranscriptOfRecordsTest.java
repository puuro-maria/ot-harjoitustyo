import Domain.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TranscriptOfRecordsTest {
    
    TranscriptOfRecords t;
    Student s;
    Course c;
    
    public TranscriptOfRecordsTest() {
        
    }
    

    @Before
    public void setUp() {
        t = new TranscriptOfRecords();
        s = new Student(1, "Viivi", "123", "HY");
        c = new Course(2, "OTM", 5, "Ope Opetin", Degree.BACHELOR, false);
    }
    
    @Test
    public void addStudentWorks(){
        t.addStudent(s);
        String print = t.getStudents();
        assertTrue(print.contains("Viivi"));
    }
    
    @Test
    public void addCourseWorks(){
        t.addCourse(s, c);
        assertEquals(t.getCourse(s, 2).getName(), "OTM");
    }
    
  
}
