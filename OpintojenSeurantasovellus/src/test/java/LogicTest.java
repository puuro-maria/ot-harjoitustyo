import domain.Student;
import domain.Degree;
import domain.Course;
import java.util.List;
import logic.Logic;

import org.junit.Before;
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
        logic.addStudent("Feikki", "12", "HY", "salasana");
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
        assertEquals("Feikki", students);
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
        logic.addCourse("12", 16, "Kurssin niumi", 5, "Andy Warhol", Degree.MASTER, true);
        String print = logic.listCoursesNotPassed("12").toString();
        if (print.contains("Tira"))
        assertTrue((print.contains("Tira")) & (!print.contains("Kurssin niumi")));
    }
    
    @Test
    public void listCoursesPassedWorks() {
        logic.addCourse("12", 16, "Kurssin niimi", 5, "Andy Warhol", Degree.MASTER, true);
        String print = logic.listCoursesPassed("12").toString();
        assertTrue(!print.contains("Tira") & print.contains("Kurssin niimi"));
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
    
    @Test
    public void getStudentWorks() {
        Student s = logic.getStudent("12");
        assertEquals("Feikki", s.getName());
    }
    
    @Test
    public void getStudentsWorks() {
        logic.addStudent("Irmeli", "555", "GG", "salasanna");
        logic.addStudent("Apina", "99", "Laanilan lukio", "gg");
        List<Student> studentlist = logic.getStudents();
        assertTrue(studentlist.size() == 3);
    }
    
    @Test
    public void finishCourseWorks() {
        Course c = logic.getCourses("12").get(0);
        logic.finishCourse("12", c);
        assertTrue(c.getFinished());
    }
    
    @Test
    public void confirmPasswordWorks() {
        assertFalse(logic.confirmPassword("kää", "kääk"));
        assertTrue(logic.confirmPassword("kääk", "kääk"));
    }
    
    @Test
    public void chechkLogInWorks() {
        assertTrue(logic.checkLogIn("12", "salasana"));
        assertFalse(logic.checkLogIn("12", "kääk"));
    }
    
    @Test
    public void logInAndOutWorks() {
        logic.checkLogIn("12", "salasana");
        assertTrue(logic.getLoggedInStudent().equals("12"));
        logic.logOut();
        assertFalse(logic.getLoggedInStudent().equals("12"));
    }
}
