
package Logic;

import Domain.*;

import java.util.ArrayList;
import java.util.Set;



public class Logic {
    
    private static int id = 1;
    TranscriptOfRecords records = new TranscriptOfRecords();
    ArrayList<Student> students = new ArrayList<>();

    
    public Logic(){
        
    }
    
    public void addStudent(String name, String studentId, String uni){
        
        Student student = new Student(id, name, studentId, uni);
        records.addStudent(student);
        this.students.add(student);
        id++;
        
    }
    
    public void addCourse(String studentId, int courseId, String courseName, int credits, String professor, Degree degree, boolean finished){
        
        Student student = getStudent(studentId);
        
        Course c = new Course(courseId, courseName, credits, professor, degree, finished);
        
        records.addCourse(student, c);
        
    }
    
    public void listCourses(Student s){
        records.toString(s);
    }
    
    public void listCourses(String studentId){
        Student s = getStudent(studentId);
        System.out.println(records.toString(s));
    }
    
    public void removeCourse(String studentId, int courseId){
        Student s = getStudent(studentId);
        Course c = records.getCourse(s, courseId);
        
        records.removeCourse(s, c);
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
    
    public void getStudents(){
        System.out.println(records.getStudents());
    }
    
}
