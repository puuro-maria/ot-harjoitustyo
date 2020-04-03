import Domain.*;
import java.util.ArrayList;

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
        s = new Student(1, "Viivi", "123", "HY", "salasana");
        c = new Course(2, "OTM", 5, "Ope Opetin", Degree.BACHELOR, false);
    }
    
    @Test
    public void addStudentWorks(){
        t.addStudent(s);
        ArrayList<Student> students = t.getStudents();
        String print =""; 
        for(Student st : students)
            if(s.getStudentId().equals(st.getStudentId()))
                print = st.getName();
        assertTrue(print.equals("Viivi"));
    }
    
    @Test
    public void addCourseWorks(){
        t.addCourse(s, c);
        assertEquals(t.getCourse(s, 2).getName(), "OTM");
    }
    
  
}
