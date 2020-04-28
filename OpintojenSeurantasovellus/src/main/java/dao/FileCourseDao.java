
package dao;

import domain.Course;
import domain.Degree;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class FileCourseDao implements CourseDao {
    
    private List<Course> courses;
    private String file;
    
    public FileCourseDao(String file) throws Exception {
        this.file = file;
        courses = new ArrayList<>();
        
        try {
            Scanner sc = new Scanner(new File(file));
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(";");
                Course c = new Course(parts[0], Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]), parts[4], Degree.valueOf(parts[5]), Boolean.parseBoolean(parts[6]));
                courses.add(c);
            }
        } catch (Exception e) {
            FileWriter fWriter = new FileWriter(new File(file));
            fWriter.close();
        }
    }
    
    private void save() throws Exception {
        try (FileWriter fWriter = new FileWriter(new File(file))) {
            for (Course course : courses) {
                fWriter.write(course.getStudent() + ";" + course.getId() + ";" + course.getName() + ";" + course.getCredits() + ";" + course.getProfessor() + ";" + course.getDegree().name() + ";" + course.getFinished() + "\n");
            }
        
        }
    }
    
    @Override
    public List<Course> getAll() {
        return courses;
    }
    
    @Override
    public Course findByCourseId(String courseId) {
        int id = Integer.parseInt(courseId);
        return courses.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
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
    public Course create(Course course) throws Exception {
        courses.add(course);
        save();
        return course;
    }
    
    @Override
    public void setDone(Course course, String studentId) throws Exception {
        for (Course c : courses) {
            if ((c.getId() == course.getId()) & c.getStudent().equals(studentId))
                c.finishCourse();
        }
        
        save();
    }
    
    @Override
    public void removeCourse(Course course, String studentId) throws Exception {
        for (Course c : courses) {
            if ((c.getId() == course.getId()) & c.getStudent().equals(studentId))
                courses.remove(c);
        }
        
        save();
    }
    
}