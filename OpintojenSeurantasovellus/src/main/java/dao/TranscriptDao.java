
package dao;

import domain.TranscriptOfRecords;
import java.util.List;

public interface TranscriptDao {

    TranscriptOfRecords create(TranscriptOfRecords tOR) throws Exception;
    
    List<String> findCoursesByStudent(String studentId);
    
    List<String> findStudentsByCourses(String courseId);
    
}
