
package dao;

import domain.Course;
import java.util.List;

/**
 * Kurssin Data Access Object, joka kirjoittaa ja lukee tiedostosta
 * @author vpuurone
 */
public interface CourseDao {
    
    /**
     * Luo kurssi
     * @param course
     * @return
     * @throws Exception
     */
    Course create(Course course) throws Exception;
    
    /**
     * Etsi kurssinumerolla
     * @param courseId
     * @return course
     */
    Course findByCourseId(String courseId);
    
    /**
     * Etsi kurssilistaus opiskelijanumerolla
     * @param studentId
     * @return courselist kurssilistaus
     */
    List<Course> findCoursesByStudentId(String studentId);
    
    /**
     * Etsi kaikki kurssit
     * @return courselist
     */
    List<Course> getAll();
    
    /**
     * Merkitse kurssi suoritetuksi
     * @param course
     * @param studentId
     * @throws Exception
     */
    void setDone(Course course, String studentId) throws Exception;
    
    /**
     * Poista kurssi
     * @param course
     * @param studentId
     * @throws Exception
     */
    void removeCourse(Course course, String studentId) throws Exception;
}
