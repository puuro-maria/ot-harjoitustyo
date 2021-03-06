
package dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import domain.Student;

/**
 * Opiskelijan Data Access Object, joka kirjoittaa tiedostoon ja lukee sieltä
 * @author vpuurone
 */
public class FileStudentDao implements StudentDao {
    
    private List<Student> students;
    private String file;
    
    /**
     * Konstruktori ottaa yhteyden tiedostoon ja lukee sieltä opiskelijalistan
     * @param file
     * @throws Exception
     */
    public FileStudentDao(String file) throws Exception {
        this.file = file;
        students = new ArrayList<>();
        
        try {
            Scanner sc = new Scanner(new File(file));
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(";");
                Student s = new Student(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]);
                students.add(s);
            }
        } catch (Exception e) {
            FileWriter fWriter = new FileWriter(new File(file));
            fWriter.close();
        }
    }
    
    private void save() throws Exception {
        try (FileWriter fWriter = new FileWriter(new File(file))) {
            for (Student student : students) {
                fWriter.write(student.getId() + ";" + student.getName() + ";" + student.getStudentId() + ";" + student.getUni() + ";" + student.getPassword() + "\n");
            }
        
        }
    }
    
    /**
     * Palauttaa listan kaikista opiskelijoista
     * @return
     */
    @Override
    public List<Student> getAll() {
        return students;
    }
    
    /**
     * Hakee opiskelijan opiskelijanumerolla
     * @param studentId
     * @return student
     */
    @Override
    public Student findByStudentId(String studentId) {
        return students.stream()
                .filter(s -> s.getStudentId()
                .equals(studentId))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Luo opiskelijan
     * @param student
     * @return student
     * @throws Exception
     */
    @Override
    public Student create(Student student) throws Exception {
        students.add(student);
        save();
        return student;
    }
    
}
