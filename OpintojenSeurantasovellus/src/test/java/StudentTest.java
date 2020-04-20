import domain.Student;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StudentTest {
    
    Student s;
    
    public StudentTest() {
        
    }
    
    @Before 
    public void setUp(){
        s = new Student(1, "Viivi", "111", "Helsingin Yliopisto", "salasana");
    }
    
    @Test
    public void constructorAndtoStringWorks(){
        String print = s.toString();
        assertEquals("Opiskelija: Viivi\nOpiskelijanumero: 111\nYliopisto: Helsingin Yliopisto", print);
    }
    
    
    
    }

    