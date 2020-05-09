
package logic;

import domain.Course;
import domain.Student;
import domain.Degree;

import dao.*;
import java.util.ArrayList;

import java.util.List;


public class Logic {
    
    private static int id = 1;
    private static String loggedInStudent;
    private CourseDao courseDao;
    private StudentDao studentDao;

    
    public Logic(CourseDao courseDao, StudentDao studentDao) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
    }
    
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
    
    public boolean addCourse(String studentId, int courseId, String courseName, int credits, String professor, Degree degree, boolean finished) {
        
        Course c = new Course(studentId, courseId, courseName, credits, professor, degree, finished);
        try {
        courseDao.create(c);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public String listCourses(String studentId) {
        Student s = getStudent(studentId);
        List<Course> courselist = courseDao.findCoursesByStudentId(studentId);
        String courses = "";
        for (Course c : courselist) {
            courses = courses + c.getName() + "\n";
        }
        return courses;
    }
    
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
    
    public List<Student> getStudents() {
        return studentDao.getAll();
    }
    
    public List<Course> getCourses(String studentId) {
        return courseDao.findCoursesByStudentId(studentId);
    }
    
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
    
    public boolean confirmPassword(String password, String confirm) {
        if (password.equals(confirm)) {
            return true;
        }
        return false;
    }
    
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
    
    public void logOut() {
        loggedInStudent = "-";
    }
    
    public String getLoggedInStudent() {
        return loggedInStudent;
    }
    
}
