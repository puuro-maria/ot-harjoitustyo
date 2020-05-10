
package domain;

/**
 * Luokka, jossa on opiskelijalle tarvittavat muuttujat
 * @author vpuurone
 */
public class Student {
    
    private int id; //not studentid, database id
    private String name, uni, studentId, password;
    
    public Student() {
        
    }
    
    /**
     *
     * @param id
     * @param name
     * @param studentId
     * @param uni
     * @param password
     */
    public Student(int id, String name, String studentId, String uni, String password) {
        this.id = id;
        this.name = name;
        this.uni = uni;
        this.studentId = studentId;
        this.password = password;
    }
    
    /**
     *
     * @return id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }
    
    /**
     *
     * @return name
     */
    public String getUni() {
        return this.uni;
    }
    
    /**
     *
     * @return studentId
     */
    public String getStudentId() {
        return this.studentId;
    }
    
    /**
     * Aseta uusi salasana
     * @param newPassword
     */
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    /**
     * 
     * @return password
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Merkkijonoesitys
     * @return String
     */
    @Override
    public String toString() {
        return "Opiskelija: " + this.name + "\nOpiskelijanumero: " + this.studentId + "\nYliopisto: " + this.uni;
    }
    
}
