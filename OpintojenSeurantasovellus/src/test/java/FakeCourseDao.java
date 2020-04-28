import java.util.ArrayList;
import java.util.List;

import dao.CourseDao;
import domain.Course;
import domain.Degree;

public class FakeCourseDao implements CourseDao {

    List<Course> courses = new ArrayList<>();
    
    public FakeCourseDao() {
        courses.add(new Course("1", 1, "Kurssi", 5, "prof", Degree.BACHELOR, true));
    }
    
    @Override
    public Course create(Course course) throws Exception {
        courses.add(course);
        return course;
    }

    @Override
    public Course findByCourseId(String courseId) {
        int id = Integer.parseInt(courseId);
        for (Course c : courses) {
            if (c.getId() == id)
                return c;
        }
        
        return null;
    }

    @Override
    public List<Course> findCoursesByStudentId(String studentId) {
        List<Course> result = new ArrayList<>();
        
        for (Course c : courses) {
            if (c.getStudent().equals(studentId))
                result.add(c);
        }
        return result;
    }

    @Override
    public List<Course> getAll() {
        return courses;
    }

    @Override
    public void setDone(Course course, String studentId) throws Exception {
        for (Course c : courses) {
            if ((c.getId() == course.getId()) & c.getStudent().equals(studentId))
                c.finishCourse();
        }
    }

    @Override
    public void removeCourse(Course course, String studentId) throws Exception {
        for (Course c : courses) {
            if ((c.getId() == course.getId()) & c.getStudent().equals(studentId))
                courses.remove(c);
        }
    }
    
}
