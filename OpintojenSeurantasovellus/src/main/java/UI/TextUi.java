
package UI;

import Logic.Logic;
import Domain.Degree;

import java.util.Scanner;


public class TextUi {
    
    private Scanner sc = new Scanner(System.in);
    private Scanner scint = new Scanner(System.in);
    private Logic logic = new Logic();
    
    public TextUi(Scanner sc){
        this.sc = sc;
    }
    
    public void launch(){
       printTitle(); 
    }
    
    void printTitle(){
        System.out.println("*******************************");
        System.out.println("*Opintojen seurantajärjestelmä*");
        System.out.println("*******************************\n");
        printNavigator();
    }
    
    void printNavigator(){
        System.out.println("****************");
        System.out.println("Valitse toiminto");
        System.out.println("1 - Lisää tunnus");
        System.out.println("2 - Lisää kurssi");
        System.out.println("3 - Listaa kurssit");
        System.out.println("4 - Poista kurssi");
        System.out.println("Syötä valinta> ");
        
        int input = scint.nextInt();
        
        switch(input){
            case 1:
                addStudent();
            case 2:
                addCourse();
            case 3:
                listCourses();
            case 4:
                removeCourse();
        }
    }
    
    void addStudent(){
        System.out.println("******************");
        System.out.println("*Lisää opiskelija*");
        System.out.println("******************");
        
        System.out.print("Syötä opiskelijan nimi> ");
        String name = sc.nextLine();
        System.out.println("Nimesi: " + name);
        

        System.out.print("Syötä opiskelijanumero> ");
        String studentId = sc.nextLine();
        System.out.println("Opiskelijanumerosi: " + studentId);
        
        
        System.out.print("Syötä yliopiston nimi> ");
        String uni = sc.nextLine();
        System.out.println("Yliopistosi: " + uni);
        
        logic.addStudent(name, studentId, uni);

        printNavigator();
        
    }
    
    void addCourse(){
        System.out.println("**************");
        System.out.println("*Lisää kurssi*");
        System.out.println("**************");
        

        System.out.print("Syötä opiskelijanumero> ");
        String studentId = sc.nextLine();
        System.out.println("Olet lisäämässä uuden kurssin opiskelijalle: " + logic.getStudent(studentId).toString());
        

        System.out.print("Syötä kurssin id> ");
        int courseId = scint.nextInt();
        System.out.println("Kurssi-id: " + courseId);

        

        System.out.print("Syötä kurssin nimi >");
        String courseName = sc.nextLine();
        System.out.println("Kurssin nimi: " + courseName);
        
        
        System.out.print("Syötä kurssin opintopisteet> ");
        int credits = scint.nextInt();
        System.out.println("Opintopisteitä: " + credits);
        

        System.out.print("Syötä kurssin professorin/vastuuopettajan nimi >");
        String professor = sc.nextLine();
        System.out.println("Kurssin vastuuopettaja: " + professor);
        
        
        Degree d = Degree.NULL;
        System.out.print("Kuuluuko kandi- vai maisteriohjelmaan? Kandi = k, maisteri = m >");
        String in = sc.nextLine();
        if(in.equals("k"))
            d = Degree.BACHELOR;
        if(in.equals("m"))
            d = Degree.MASTER;
        System.out.println("Kurssin taso: " + d);
        

        boolean finished=false;

        System.out.print("Onko kurssi suoritettu? Kyllä = k, ei = e >");
        String input = sc.nextLine();
        String out = "";
        if(input.equals("k")){
            finished = true;
            out = "kandikurssi";
        }
        if(input.equals("e")){
            finished = false;
            out = "maisterikurssi";
        }
        System.out.println("Kurssi on " + out);
        
        
        logic.addCourse(studentId, courseId, courseName, credits, professor, d, finished);
        printNavigator();
    }
    
    void listCourses(){
        System.out.println("****************************");
        System.out.println("*Listaa opiskelijan kurssit*");
        System.out.println("****************************");
        System.out.print("Syötä opiskelijanumero >");
        String studentId = sc.nextLine();
        logic.listCourses(studentId);
        System.out.print("Syötä x jos haluat aloitusvalikkoon");
        String input = sc.nextLine();
        if(input.equals("x"))
            printNavigator();
    }
    
    void removeCourse(){
        System.out.println("***************");
        System.out.println("*Poista kurssi*");
        System.out.println("***************");
        System.out.print("Syötä opiskelijanumero >");
        String studentId = sc.nextLine();
        
        logic.listCourses(studentId);
        System.out.print("Syötä poistettavan kurssin id> ");
        int courseId = scint.nextInt();
        
        logic.removeCourse(studentId, courseId);
        printNavigator();
    }
    
}