
package dao;

import domain.Course;
import java.util.List;

public interface CourseDao {
    
    Course create(Course course) throws Exception;
    
    Course findByCourseId(String courseId);
    
    List<Course> findCoursesByStudentId(String studentId);
    
    List<Course> getAll();
    
    void setDone(Course course, String studentId) throws Exception;
    
    void removeCourse(Course course, String studentId) throws Exception;
}
