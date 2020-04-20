
package dao;

import domain.Course;
import java.util.List;

public interface CourseDao {
    
    Course create(Course course) throws Exception;
    
    Course findByCourseId(String courseId);
    
    List<Course> getAll();
    
}
