
package dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FileTranscriptDao implements TranscriptDao {
    
    private HashMap<String, List<String>> studentCourse;
    private String file;
    
    public FileTranscriptDao(String file) throws Exception {
        this.file = file;
        studentCourse = new HashMap<>();
        
        try { 
            Scanner sc = new Scanner(new File(file));
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(";");
                if (!studentCourse.get(parts[0]).isEmpty()) {
                    List<String> c = studentCourse.get(parts[0]);
                    c.add(parts[1]);
                } else {
                    List<String> c = new ArrayList<>();
                    c.add(parts[1]);
                    studentCourse.put(parts[0], c);
                }
            }
        } catch (Exception e) { 
            FileWriter fWriter = new FileWriter(new File(file));
            fWriter.close();
        }
    }
    
    private void save() throws Exception {
        try (FileWriter fWriter = new FileWriter(new File(file))) {
            for (String s : studentCourse.keySet()) {
                List<String> courses = studentCourse.get(s);
                for (String c : courses) {
                    fWriter.write(s + ";" + c + "\n");
                }
            }
        
        }
    }
    
    @Override
    public void create(String studentId) throws Exception {
        
        if (studentCourse.get(studentId).isEmpty()) {
            List<String> courses = new ArrayList<>();
            studentCourse.put(studentId, courses);
        }
        
        save();
    }
    
    @Override
    public void addCourse(String studentId, String courseId) throws Exception {
        
            List<String> courses = studentCourse.get(studentId);
            courses.add(courseId);
            studentCourse.put(studentId, courses);
        
        save();
    }
    
    @Override
    public List<String> findCoursesByStudent(String studentId) {
        if (!studentCourse.get(studentId).isEmpty()) {
            return studentCourse.get(studentId);
        } else {
            List<String> empty = new ArrayList<>();
            return empty;
        }
    }
    
    @Override
    public HashMap<String, List<String>> getAll() {
        return studentCourse;
    }
    
    @Override
    public void removeCourse(String studentId, String courseId) throws Exception {
        
    }
    
}
