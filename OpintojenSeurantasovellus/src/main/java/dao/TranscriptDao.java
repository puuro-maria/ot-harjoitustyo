
package dao;

import java.util.HashMap;
import java.util.List;

public interface TranscriptDao {

    void create(String studentId, String courseId) throws Exception;
    
    List<String> findCoursesByStudent(String studentId);
    
    HashMap<String, List<String>> getAll();
    
}
