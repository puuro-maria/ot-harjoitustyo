import domain.Course;
import domain.Degree;
import domain.Semester;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CourseTest {
    
    Course c;
    
    public CourseTest() {
        
    }
    
    
    @Before
    public void setUp() {
        c = new Course("12", 2, "OTM", 5, "Opettaja Opetin", Degree.BACHELOR, false);
    }
    
    @Test
    public void constructorAndtoStringWorks() {
        String print = c.toString();
        assertEquals("Opiskelija 12, kurssi 2, OTM\nOpintopisteet: 5\nVastuuopettaja: Opettaja Opetin\nKurssi suoritettu: false\n----------------------\n", print);
    }
    
    @Test
    public void finishCourseWorks() {
        c.finishCourse();
        assertTrue(c.getFinished());
    }
    
    @Test
    public void setSemesterWorks()  {
        c.setSemester(Semester.SYKSY);
        assertTrue(c.getSemester() == Semester.SYKSY);
    }
    
}
