
package UI;

import java.util.Scanner;
import Logic.Logic;


public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        TextUi ui = new TextUi(sc);
        ui.launch();
    }
    
}
