
package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class TranscriptOfRecords {
    
    HashMap<Student, ArrayList<Course>> transcript = new HashMap<>();
    
    public void addStudent(Student student){
        if(!transcript.containsKey(student)){
            ArrayList<Course> courses = new ArrayList<>();
            transcript.put(student, courses);
        } 
    }
    
    public String getStudents(){
        
        String students = "Opiskelijat:\n--------------";
        for(Student s : transcript.keySet())
            students = students + s.toString() + "\n";
        return students;
    }
    
    public void addCourse(Student student, Course course){
        
        if(transcript.containsKey(student)){
            ArrayList<Course> courses = transcript.get(student);
            courses.add(course);
            transcript.put(student, courses);
        }
        
        if(!transcript.containsKey(student)){
            addStudent(student);
            addCourse(student, course);
        }
            
    }
    
    public void removeCourse(Student s, Course c){
        ArrayList<Course> courses = transcript.get(s);
        courses.remove(c);
        transcript.put(s, courses);
    }
    
    public Course getCourse(Student s, int courseId){
        ArrayList<Course> courses = transcript.get(s);
        
        Course c = new Course();
        
        for(Course co : courses){
            if(co.getId() == courseId){
                c = co;
                break;
            }
        }
        
        return c;
    }
    
    public ArrayList<Course> getTranscript(Student s){
            
        return this.transcript.get(s);
    }
    
    public String toString(Student s){
        
        ArrayList<Course> c = transcript.get(s);
        String list = "Opiskelijan " + s.getName() + " " + s.getStudentId() + " kurssilistaus:\n";
        
        for(Course co : c)
            list = list + co.toString();
        
        return list;
    }

}