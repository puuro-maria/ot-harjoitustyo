
package Logic;

import Domain.*;

import java.util.ArrayList;
import java.util.Set;



public class Logic {
    
    private static int id = 1;
    TranscriptOfRecords records = new TranscriptOfRecords();
    ArrayList<Student> students = new ArrayList<>();
    private static String loggedInStudent;

    
    public Logic(){
        TranscriptOfRecords records = new TranscriptOfRecords();
        ArrayList<Student> students = new ArrayList<>();
    }
    
    public void addStudent(String name, String studentId, String uni, String password){
        
        Student student = new Student(id, name, studentId, uni, password);
        records.addStudent(student);
        this.students.add(student);
        id++;
        
    }
    
    public void addCourse(String studentId, int courseId, String courseName, int credits, String professor, Degree degree, boolean finished){
        
        Student student = getStudent(studentId);
        
        Course c = new Course(courseId, courseName, credits, professor, degree, finished);
        
        records.addCourse(student, c);
        
    }
    
    
    public String listCourses(String studentId){
        Student s = getStudent(studentId);
        return records.toString(s);
    }
    
    public void listCoursesNotPassed(String studentId){
        Student s = getStudent(studentId);
        System.out.println(records.listCoursesNotPassed(s));
    }
    
    public void removeCourse(String studentId, int courseId){
        Student s = getStudent(studentId);
        Course c = records.getCourse(s, courseId);
        
        if(c.getFinished()==false)
            records.removeCourse(s, c);
        else
            System.out.println("Olet jo suorittanut kurssin, sitä ei voi poistaa rekisteristä.");
    }
    
    public Student getStudent(String studentId){
        
        Student student = new Student();
        
        for(Student s : this.students){
            if(s.getStudentId().equals(studentId)){
                student = s;
                break;
            }
        }
        
        return student;
    }
    
    public ArrayList getStudents(){
        return records.getStudents();
    }
    
    public void finishCourse(int courseId, String studentId){
        Student s = getStudent(studentId);
        Course c = records.getCourse(s, courseId);
        c.finishCourse();
    }
    
    public boolean confirmPassword(String password, String confirm){
        if(password.equals(confirm))
            return true;
        return false;
    }
    
    public boolean checkLogIn(String studentId, String password){
        ArrayList<Student> studentList = getStudents();
        Student s;
        for(Student st : studentList)
            if(studentId.equals(st.getStudentId())){
                s = st;
                if(s.getPassword().equals(password)){
                    loggedInStudent=studentId;
                    return true;
                }
                else{
                    System.out.println("Virheellinen salasana!");
                    return false;
                }
            }
            else 
                System.out.println("Opiskelijanumerolla " + studentId + " ei löytynyt tunnuksia.");
        return false;
    }
    
    public void logOut(){
        loggedInStudent = "-";
    }
    
    public String getLoggedInStudent(){
        return loggedInStudent;
    }
    
}
