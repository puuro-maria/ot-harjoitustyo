
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
                Course c = new Course(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]), parts[3], Degree.valueOf(parts[4]), Boolean.parseBoolean(parts[5]));
                courses.add(c);
            }
        } catch(Exception e) {
            FileWriter fWriter = new FileWriter(new File(file));
            fWriter.close();
        }
    }
    
    private void save() throws Exception{
        try (FileWriter fWriter = new FileWriter(new File(file))) {
            for (Course course : courses) {
                fWriter.write(course.getId() + ";" + course.getName() + ";" + course.getCredits() + ";" + course.getProfessor()+ ";" + course.getDegree().name() + ";" + course.getFinished() + "\n");
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
    public Course create(Course course) throws Exception {
        courses.add(course);
        save();
        return course;
    }
    
}