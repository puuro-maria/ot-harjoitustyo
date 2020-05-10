
package domain;

/**
 * Luokka mallintaa kurssin tietoja
 * @author vpuurone
 */

public class Course {
    
    private int credits, id;
    private String name, professor, studentId;
    private Degree degree;
    private Semester semester;
    private boolean finished;
    
    /**
     * Parametriton konstruktori
     */
    public Course() {
        
    }
    
    /** 
     * Parametrillinen konstruktori kurssille
     * 
     * @param studentId opiskelijanumero
     * @param id kurssin id
     * @param name opiskelijan nimi
     * @param credits kurssin opintopisteet
     * @param professor kurssin vastuuopettaja
     * @param degree mihin tutkintoon kurssi kuuluu
     * @param finished onko kurssi suoritettu
     */
    
    public Course(String studentId, int id, String name, int credits, String professor, Degree degree, boolean finished) {
        this.studentId = studentId;
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.professor = professor;
        this.degree = degree;
        this.finished = finished;
    }
    
    /**
     * Merkitsee kurssin suoritetuksi
     * 
     */
    
    public void finishCourse() {
        this.finished = true;
    }
    
    /**
     * Palauttaa kurssin id:n
     * 
     * @return id kurssin id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Palauttaa kurssin nimen
     * @return name
     */
    
    public String getName() {
        return this.name;
    }
    
    /**
     * Palauttaa kurssin opintopisteet
     * @return credits
     */
    public int getCredits() {
        return this.credits;
    }
    
    /**
     * Palauttaa kurssin vastuuopettajan
     * @return professor
     */
    
    public String getProfessor() {
        return this.professor;
    }
    
    /**
     * Palauttaa tutkinnon, johon kurssi kuuluu
     * @return degree kandi tai maisteri
     */
    public Degree getDegree() {
        return this.degree;
    }
    
    /**
     * Kertoo, onko kurssi suoritettu vai ei
     * @return finished
     */
    
    public boolean getFinished() {
        return this.finished;
    }
    
    /**
     * Asettaa kurssille lukukauden tarvittaessa
     * @param s lukukausi
     */
    public void setSemester(Semester s) {
        this.semester = s;
    }
    
    /**
     * Palauttaa kurssin lukukauden
     * @return semester
     */
    public Semester getSemester() {
        return this.semester;
    }
    
    /**
     * Palauttaa kurssin opiskelijan opiskelijanumeron
     * @return studentId
     */
    public String getStudent() {
        return this.studentId;
    }
    /**
     * Kurssin merkkijonoesitys tekstikäyttöliittymää varten
     * @return String
     */
    @Override
    public String toString() {
        return "Opiskelija " + this.studentId + ", kurssi " + this.id + ", " + this.name + "\nOpintopisteet: " 
                + this.credits + "\nVastuuopettaja: " + this.professor + 
                "\nKurssi suoritettu: " + this.finished + "\n----------------------\n";
    }


}
