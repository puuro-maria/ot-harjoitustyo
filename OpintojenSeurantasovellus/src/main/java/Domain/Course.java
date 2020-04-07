
package Domain;


public class Course {
    
    private int credits, id;
    private String name, professor;
    private Degree degree;
    private Semester semester;
    private boolean finished;
    
    public Course(){
        
    }
    
    public Course(int id, String name, int credits, String professor, Degree degree, boolean finished){
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.professor = professor;
        this.degree = degree;
        this.finished = finished;
    }
    
    
    public void finishCourse(){
        this.finished = true;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getCredits(){
        return this.credits;
    }
    
    public String getProfessor(){
        return this.professor;
    }
    
    public Degree getDegree(){
        return this.degree;
    }
    
    public boolean getFinished(){
        return this.finished;
    }
    
    public void setSemester(Semester s){
        this.semester = s;
    }
    
    public Semester getSemester(){
        return this.semester;
    }
    
    @Override
    public String toString(){
        return "Kurssi " + this.id + ", " + this.name + "\nOpintopisteet: " 
                + this.credits + "\nVastuuopettaja: " + this.professor + 
                "\nKurssi suoritettu: " + this.finished + "\n----------------------\n";
    }
}
