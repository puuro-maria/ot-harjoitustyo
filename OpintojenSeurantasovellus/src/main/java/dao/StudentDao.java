
package dao;

import domain.Student;
import java.util.List;

/**
 * Opiskelijan Data Access Object, joka kirjoittaa ja lukee tiedostoon
 * @author vpuurone
 */
public interface StudentDao {
    
    /**
     * Luo opiskelijan
     * @param student
     * @return student
     * @throws Exception
     */
    Student create(Student student) throws Exception;
    
    /**
     * Etsii opiskelijan opiskelijanumerolla
     * @param studentId
     * @return
     */
    Student findByStudentId(String studentId);
    
    /**
     * Palauttaa kaikki opiskelijat listassa
     * @return
     */
    List<Student> getAll();
    
}
