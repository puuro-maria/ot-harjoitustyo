
package domain;


public class Student {
    
    private int id; //not studentid, database id
    private String name, uni, studentId, password;
    
    public Student() {
        
    }
    
    public Student(int id, String name, String studentId, String uni, String password) {
        this.id = id;
        this.name = name;
        this.uni = uni;
        this.studentId = studentId;
        this.password = password;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getUni() {
        return this.uni;
    }
    
    public String getStudentId() {
        return this.studentId;
    }
    
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public String toString() {
        return "Opiskelija: " + this.name + "\nOpiskelijanumero: " + this.studentId + "\nYliopisto: " + this.uni;
    }
    
}
