
package Domain;


public class Student {
    
    private int id; //not studentid, database id
    private String name, uni, studentId;
    
    public Student(){
        
    }
    
    public Student(int id, String name, String studentId, String uni){
        this.id = id;
        this.name = name;
        this.uni = uni;
        this.studentId = studentId;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getUni(){
        return this.uni;
    }
    
    public String getStudentId(){
        return this.studentId;
    }
    
    @Override
    public String toString(){
        return "Opiskelija: " + this.name + "\nOpiskelijanumero: " + this.studentId + "\nYliopisto: " + this.uni;
    }
    
}
