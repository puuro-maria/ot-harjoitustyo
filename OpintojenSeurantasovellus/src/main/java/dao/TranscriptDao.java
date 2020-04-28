
package dao;

import java.util.HashMap;
import java.util.List;

public interface TranscriptDao {

    void create(String studentId) throws Exception;
    
    void addCourse(String studentId, String courseId) throws Exception;
    
    List<String> findCoursesByStudent(String studentId);
    
    HashMap<String, List<String>> getAll();
    
    void removeCourse(String studentId, String courseId) throws Exception;
    
}
