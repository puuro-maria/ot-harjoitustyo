import domain.Student;
import domain.Degree;
import logic.Logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogicTest {
    
    Logic logic;
    Student student;
    
    public LogicTest() {
    }
    
    @Before
    public void setUp() {
        logic = new Logic();
        logic.addStudent("Viivi", "12", "HY", "salasana");
    }
    
    @Test
    public void addStudentWorks(){
        logic.addStudent("Viivi", "12", "HY", "salasana");
        String name = logic.getStudent("12").getName();
        assertEquals("Viivi", name);
    }
    
    @Test
    public void addCourseWorks(){
        logic.addCourse("12", 1, "OTM", 5, "Ope", Degree.BACHELOR, false);
        String courses = logic.listCourses("12");
        assertTrue(courses.contains("OTM"));
    }
    


}
