import domain.Student;
import domain.Degree;
import domain.Course;
import java.util.ArrayList;
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
    Course course;
    FakeCourseDao fakeCourseDao = new FakeCourseDao();
    FakeStudentDao fakeStudentDao = new FakeStudentDao();
    
    public LogicTest() {
    }
    
    @Before
    public void setUp() {
        logic = new Logic(fakeCourseDao, fakeStudentDao);
        logic.addStudent("Viivi", "12", "HY", "salasana");
        logic.addCourse("12", 11, "Tira", 10, "A.A.", Degree.BACHELOR, false);
    }
    
    @Test
    public void addStudentWorks() {
        logic.addStudent("Aapo", "11", "HY", "salasana");
        String name = logic.getStudent("11").getName();
        assertEquals("Aapo", name);
    }
    
    @Test
    public void noDuplicateStudents() {
        logic.addStudent("Viiva", "12", "HY", "salasana");
        String students = logic.getStudent("12").getName();
        assertEquals("Viivi", students);
    }
    
    @Test
    public void addCourseWorks() {
        logic.addCourse("12", 1, "OTM", 5, "Ope", Degree.BACHELOR, false);
        String courses = logic.listCourses("12");
        assertTrue(courses.contains("OTM"));
    }
    
    @Test
    public void listCoursesWorks(){
        logic.addCourse("12", 13, "Kurssi", 10, "A.A.", Degree.BACHELOR, true);
        String print = logic.listCourses("12");
        assertTrue(print.contains("Kurssi") & print.contains("Tira"));
    }
    
    @Test
    public void listCoursesNotPassedWorks(){
        logic.addCourse("12", 16, "Kurssin nimi", 5, "Andy Warhol", Degree.MASTER, true);
        String print = logic.listCoursesNotPassed("12");
        assertTrue(print.contains("Tira") & !print.contains("Kurssin nimi"));
    }
    
    @Test
    public void listCoursesPassedWorks() {
        logic.addCourse("12", 16, "Kurssin nimi", 5, "Andy Warhol", Degree.MASTER, true);
        String print = logic.listCoursesPassed("12");
        assertTrue(!print.contains("Tira") & print.contains("Kurssin nimi"));
    }
    
    @Test
    public void removeCourseWorks() {
        logic.addCourse("12", 16, "Kurssin nimi", 5, "Andy Warhol", Degree.MASTER, false);
        logic.addCourse("12", 17, "Käyty kurssi", 5, "Andy Warhol", Degree.MASTER, true);
        logic.removeCourse("12", 16);
        logic.removeCourse("12", 17);
        String print = logic.listCourses("12");
        assertTrue(!print.contains("Kurssin nimi") & print.contains("Käyty kurssi"));
    }

}
