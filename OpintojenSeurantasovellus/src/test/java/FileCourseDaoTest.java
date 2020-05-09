import dao.CourseDao;
import dao.FileCourseDao;
import dao.StudentDao;
import domain.Course;
import domain.Degree;
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
    
    File courseFile;
    Course course = new Course("12", 1, "Kurssi", 5, "prof", Degree.BACHELOR, false);
    Student student = new Student(1, "Feikki", "12", "HY", "salasana");
    CourseDao courseDao;
    
    
    @Before
    public void setUp() throws Exception {
        courseFile = testFolder.newFile("testfile_courses.txt");
     
        try (FileWriter file = new FileWriter(courseFile.getAbsolutePath())) {
            file.write("12;1;Kurssi;5;prof;BACHELOR;false\n");
        }
        
        courseDao = new FileCourseDao(courseFile.getAbsolutePath());
    }
    
    @Test
    public void coursesAreReadCorrectly() {
        List<Course> courses = courseDao.getAll();
        assertEquals(1, courses.size());
        Course course = courses.get(0);
        assertFalse(course.getFinished());
        assertEquals("Kurssi", course.getName());
        assertEquals("12", course.getStudent());
        assertEquals(Degree.BACHELOR, course.getDegree());
        assertEquals("prof", course.getProfessor());
        assertTrue(course.getCredits() == 5);
    }
    
    @Test
    public void canSetPassed() {
        try {
            courseDao.setDone(course, "12");
        } catch (Exception e) {
            
        }
        List<Course> courselist = courseDao.getAll();
        Course c = courselist.get(0);
        assertTrue(c.getFinished());
    }
    
    @Test
    public void removeCourseWorks() {
        try {
            courseDao.create(new Course("12", 2, "Kurssilainen", 5, "proffa", Degree.MASTER, false));
        } catch (Exception e) {
            
        }
        assertTrue(courseDao.getAll().size() == 2);
        Course tcourse = courseDao.getAll().get(1);
        try {
            courseDao.removeCourse(tcourse, "12");
        } catch (Exception e) {
            
        }
        
        assertTrue(courseDao.getAll().size() == 1);
    }
    
}
