
package logic;

import domain.Course;
import domain.Student;
import domain.Degree;

import dao.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Logic {
    
    private static int id = 1;
    private static String loggedInStudent;
    private CourseDao courseDao;
    private StudentDao studentDao;
    private TranscriptDao transcriptDao;

    
    public Logic(CourseDao courseDao, StudentDao studentDao, TranscriptDao transcriptDao) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.transcriptDao = transcriptDao;
    }
    
    public boolean addStudent(String name, String studentId, String uni, String password) {
        boolean alreadyReg = false;
        
        for (Student s : studentDao.getAll()) {
            if (s.getStudentId().equals(studentId)) {
                System.out.println("\n!!!!!!!!!!Opiskelija " + studentId + " on jo rekisteröitynyt!!!!!!!\n");
                alreadyReg = true;
            }
        }
        if (alreadyReg == false) {
            Student student = new Student(id, name, studentId, uni, password);
            try {
            transcriptDao.create(studentId);
            studentDao.create(student);
            id++;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
    
    public boolean addCourse(String studentId, int courseId, String courseName, int credits, String professor, Degree degree, boolean finished) {
        
        Course c = new Course(courseId, courseName, credits, professor, degree, finished);
        try {
        transcriptDao.addCourse(studentId, Integer.toString(courseId));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public String listCourses(String studentId) {
        Student s = getStudent(studentId);
        List<String> courselist = transcriptDao.findCoursesByStudent(studentId);
        String courses = "";
        for (String c : courselist) {
            courses = courses + courseDao.findByCourseId(c).getName() + "\n";
        }
        return courses;
    }
    
    public String listCoursesNotPassed(String studentId) {
        Student s = getStudent(studentId);
        List<String> courselist = transcriptDao.findCoursesByStudent(studentId);
        String courses = "";
        for (String c : courselist) {
            if (courseDao.findByCourseId(c).getFinished() == true) {
                courses = courses + courseDao.findByCourseId(c).getName() + "\n";
            }
        }
        return courses;
    }
    
    public String listCoursesPassed(String studentId) {
        Student s = getStudent(studentId);
        return records.listCoursesPassed(s);
    }
    
    public void removeCourse(String studentId, int courseId) {
        Student s = getStudent(studentId);
        Course c = records.getCourse(s, courseId);
        
        if (c.getFinished() == false) {
            records.removeCourse(s, c);
        } else {
            System.out.println("Olet jo suorittanut kurssin, sitä ei voi poistaa rekisteristä.");
        }
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
    
    public void finishCourse(int courseId, String studentId) {
        Student s = getStudent(studentId);
        Course c = records.getCourse(s, courseId);
        c.finishCourse();
    }
    
    public boolean confirmPassword(String password, String confirm) {
        if (password.equals(confirm)) {
            return true;
        }
        return false;
    }
    
    public boolean checkLogIn(String studentId, String password) {
        ArrayList<Student> studentList = getStudents();
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
