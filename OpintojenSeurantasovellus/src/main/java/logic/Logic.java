
package logic;

import domain.Course;
import domain.Student;
import domain.Degree;

import dao.*;
import java.util.ArrayList;

import java.util.List;

/**
 * Tässä luokassa on sovelluslogiikka
 * @author vpuurone
 */
public class Logic {
    
    private static int id = 1;
    private static String loggedInStudent;
    private CourseDao courseDao;
    private StudentDao studentDao;

    /**
     * Otetaan parametreina tiedosto-oliot (Data Access Objects)
     * @param courseDao
     * @param studentDao
     */
    public Logic(CourseDao courseDao, StudentDao studentDao) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
    }
    
    /**
     * Luo opiskelija
     * @param name
     * @param studentId
     * @param uni
     * @param password
     * @return
     */
    public boolean addStudent(String name, String studentId, String uni, String password) {
        boolean alreadyReg = false;
        
        for (Student s : studentDao.getAll()) {
            if (s.getStudentId().equals(studentId)) {
                alreadyReg = true;
                return false;
            }
        }
        if (alreadyReg == false) {
            Student student = new Student(id, name, studentId, uni, password);
            try {
                studentDao.create(student);
                id++;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Lisää kurssi
     * @param studentId
     * @param courseId
     * @param courseName
     * @param credits
     * @param professor
     * @param degree
     * @param finished
     * @return
     */
    public boolean addCourse(String studentId, int courseId, String courseName, int credits, String professor, Degree degree, boolean finished) {
        
        Course c = new Course(studentId, courseId, courseName, credits, professor, degree, finished);
        try {
            courseDao.create(c);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * Kurssilistan merkkijonoesitys tekstikäyttöliittymää varten
     * @param studentId
     * @return String
     */
    public String listCourses(String studentId) {
        Student s = getStudent(studentId);
        List<Course> courselist = courseDao.findCoursesByStudentId(studentId);
        String courses = "";
        for (Course c : courselist) {
            courses = courses + c.getName() + "\n";
        }
        return courses;
    }
    
    /**
     * Listaa opiskelijan suorittamattomat kurssit
     * @param studentId
     * @return notPassed lista kursseista, jotka ovat opiskelijan kurssilistalla, mutta ei vielä suoritettu
     */
    public List<Course> listCoursesNotPassed(String studentId) {
        
        List<Course> courselist = courseDao.findCoursesByStudentId(studentId);
        List<Course> notPassed = new ArrayList<>();

        for (Course c : courselist) {
            if (c.getFinished() == false) {
                notPassed.add(c);
            }
        }
        return notPassed;
    }
    
    /**
     * Listaa opiskelijan suoritetut kurssit
     * @param studentId
     * @return passed
     */
    public List<Course> listCoursesPassed(String studentId) {
        List<Course> courselist = courseDao.findCoursesByStudentId(studentId);
        List<Course> passed = new ArrayList<>();
        
        for (Course c : courselist) {
            if (c.getFinished() == true) {
                passed.add(c);
            }
        }
        return passed;
    }
    
    /**
     * Poista kurssi listalta
     * @param studentId
     * @param courseId
     * @return boolean
     */
    public boolean removeCourse(String studentId, int courseId) {

        Course c = courseDao.findByCourseId(Integer.toString(courseId));
        
        if (c.getFinished() == false) {
            try {
                courseDao.removeCourse(c, studentId);
            } catch (Exception e) {
                return false;
            }
        } else {
            System.out.println("Olet jo suorittanut kurssin, sitä ei voi poistaa rekisteristä.");
        }
        return true;
    }
    
    /**
     * Etsi opiskelija opiskelijanumerolla
     * @param studentId
     * @return student
     */
    public Student getStudent(String studentId) {
        
        Student student = new Student();
        
        for (Student s : studentDao.getAll()) {
            if (s.getStudentId().equals(studentId)) {
                student = s;
                break;
            }
        }
        
        return student;
    }
    
    /**
     * Listaa kaikki opiskelijat
     * @return students
     */
    public List<Student> getStudents() {
        return studentDao.getAll();
    }
    
    /**
     * Listaa opiskelijan kaikki kurssit
     * @param studentId
     * @return courses
     */
    public List<Course> getCourses(String studentId) {
        return courseDao.findCoursesByStudentId(studentId);
    }
    
    /**
     * Merkitse kurssi suoritetuksi
     * @param studentId
     * @param course
     * @return boolean
     */
    public boolean finishCourse(String studentId, Course course) {
        for (Course c : courseDao.getAll()) {
            if ((c.getId() == course.getId()) & c.getStudent().equals(studentId)) {
                try {
                    courseDao.setDone(c, studentId);
                } catch (Exception e) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Vahvista salasana
     * @param password
     * @param confirm
     * @return boolean
     */
    public boolean confirmPassword(String password, String confirm) {
        if (password.equals(confirm)) {
            return true;
        }
        return false;
    }
    
    /**
     * Vahvista sisäänkirjautuminen
     * @param studentId
     * @param password salasana
     * @return boolean
     */
    public boolean checkLogIn(String studentId, String password) {
        List<Student> studentList = getStudents();
        Student s;
        for (Student st : studentList) {
            if (studentId.equals(st.getStudentId())) {
                s = st;
                if (s.getPassword().equals(password)) {
                    loggedInStudent = studentId;
                    return true;
                } else {
                    System.out.println("Virheellinen salasana!");
                    return false;
                }
            } else {
                System.out.println("Opiskelijanumerolla " + studentId + " ei löytynyt tunnuksia.");
            }
        }
        
        return false;
    }
    
    /**
     * Kirjaudu ulos
     */
    public void logOut() {
        loggedInStudent = "-";
    }
    
    /**
     * Palauta sisäänkirjautunut opiskelija
     * @return String
     */
    public String getLoggedInStudent() {
        return loggedInStudent;
    }
    
}
