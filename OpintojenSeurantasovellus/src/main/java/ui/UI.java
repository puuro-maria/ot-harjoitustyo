package ui;

import dao.FileCourseDao;
import dao.FileStudentDao;
import domain.Course;
import domain.Degree;
import logic.Logic;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UI extends Application {
    
    private Scene listScene;
    private Scene newStudentScene;
    private Scene newCourseScene;
    private Scene loginScene;
    
    private VBox courseNodes;
    
    private Label menuLabel = new Label();
    
    private Logic logic;
    
    @Override
    public void init() throws Exception {
        
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String studentFile = properties.getProperty("studentFile");
        String courseFile = properties.getProperty("courseFile");
        
        FileStudentDao studentDao = new FileStudentDao(studentFile);
        FileCourseDao courseDao = new FileCourseDao(courseFile);
        
        logic = new Logic(courseDao, studentDao);
    }
    
    public Node createCourseNode(Course course, String studentId) {
        HBox box = new HBox(10);
        Label label = new Label(course.getName());
        label.setMinHeight(28);
        Button button = new Button("Course passed");
        button.setOnAction(e->{
        logic.finishCourse(studentId, course);
        redrawCourseList(studentId);
        });
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0,5,0,5));
        
        box.getChildren().addAll(label, spacer, button);
        return box;
    }
    
    public void redrawCourseList(String studentId) {
        courseNodes.getChildren().clear();
        
        List<Course> allcourses = logic.getCourses(studentId);
        allcourses.forEach(course ->{
            courseNodes.getChildren().add(createCourseNode(course, studentId));
        });
    }

    @Override
    public void start(Stage primary) throws Exception {
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Opiskelijanumero");
        Label passwordLabel = new Label("Salasana");
        TextField studentIdInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        
        inputPane.getChildren().addAll(loginLabel, studentIdInput, passwordLabel, passwordInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("Kirjaudu sisään");
        Button createButton = new Button("Rekisteröidy");
        
        loginButton.setOnAction(e->{
            String studentId = studentIdInput.getText();
            String password = passwordInput.getText();
            if (logic.checkLogIn(studentId, password)){
                menuLabel.setText("Opiskelijanumero " + studentId + " kirjautunut sisään");
                loginMessage.setText("");
                redrawCourseList(studentId);
                primary.setScene(listScene);
                studentIdInput.setText("");
                passwordInput.setText("");
            } else {
                loginMessage.setText("Virheellinen käyttäjätunnus tai salasana!");
                loginMessage.setTextFill(Color.RED);
            }            
        });
        
        createButton.setOnAction(e->{
            studentIdInput.setText("");
            passwordInput.setText("");
            primary.setScene(newStudentScene);
        });
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);
        
        loginScene = new Scene(loginPane, 500, 500);
        
        VBox newStudentPane = new VBox(10);
        
        HBox newStudentIdPane = new HBox(10);
        newStudentIdPane.setPadding(new Insets(10));
        TextField newStudentIdInput = new TextField();
        Label newStudentIdLabel = new Label("Opiskelijanumero");
        newStudentIdLabel.setPrefWidth(200);
        newStudentIdPane.getChildren().addAll(newStudentIdLabel, newStudentIdInput);
        
        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("Opiskelijan nimi");
        newNameLabel.setPrefWidth(200);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);
        
        HBox uniPane = new HBox(10);
        uniPane.setPadding(new Insets(10));
        TextField uniInput = new TextField();
        Label uniLabel = new Label("Oppilaitos");
        uniLabel.setPrefWidth(200);
        uniPane.getChildren().addAll(uniLabel, uniInput);
        
        HBox newPasswordPane = new HBox(10);
        newPasswordPane.setPadding(new Insets(10));
        PasswordField newPasswordInput = new PasswordField();
        Label newPasswordLabel = new Label("Salasana");
        newPasswordLabel.setPrefWidth(200);
        newPasswordPane.getChildren().addAll(newPasswordLabel, newPasswordInput);
        
        HBox confirmPane = new HBox(10);
        confirmPane.setPadding(new Insets(10));
        PasswordField confirmInput = new PasswordField();
        Label confirmLabel = new Label("Toista salasana");
        confirmLabel.setPrefWidth(200);
        confirmPane.getChildren().addAll(confirmLabel, confirmInput);
        
        Label studentCreationMessage = new Label();
        
        Button createStudentButton = new Button("Luo tunnus");
        createStudentButton.setPadding(new Insets(10));
        
        createStudentButton.setOnAction(e->{
            String newStudentId = newStudentIdInput.getText();
            String newName = newNameInput.getText();
            String newUni =  uniInput.getText();
            String password = newPasswordInput.getText();
            String confirm = confirmInput.getText();
            if (!newStudentId.matches("[0-9]+")) {
                studentCreationMessage.setText("Opiskelijanumero voi sisältää vain numeroita!");
                studentCreationMessage.setTextFill(Color.RED);
            }
            if (logic.confirmPassword(password, confirm)) {
                if (logic.addStudent(newName, newStudentId, newUni, password)) {
                    studentCreationMessage.setText("Uusi käyttäjä luotu");
                    studentCreationMessage.setTextFill(Color.GREEN);
                } else {
                    studentCreationMessage.setText("Opiskelijanumerolla on jo tunnus!");
                    studentCreationMessage.setTextFill(Color.RED);
                }
                
            } else {
                studentCreationMessage.setText("Syöttämäsi salasanat eivät täsmänneet!");
                studentCreationMessage.setTextFill(Color.RED);
            }
        });
        
        newStudentPane.getChildren().addAll(studentCreationMessage, newStudentIdPane, newNamePane, uniPane, newPasswordPane, confirmPane, createStudentButton);
        
        newStudentScene = new Scene(newStudentPane, 500, 500);
        
        ScrollPane courseScrollBar = new ScrollPane();
        BorderPane mainPane = new BorderPane(courseScrollBar);
        listScene = new Scene(mainPane, 500, 500);
        
        HBox menuPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("Kirjaudu ulos");
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e->{
            logic.logOut();
            primary.setScene(loginScene);
        });
        
        Label createCourseMessage = new Label();
        HBox createCourseForm = new HBox(10);
        Button createCourse = new Button("Lisää kurssi");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        TextField courseIdInput = new TextField();
        Label courseIdLabel = new Label("Kurssinumero");
        HBox courseIdPane = new HBox(10);
        courseIdPane.setPadding(new Insets(10));
        courseIdPane.getChildren().addAll(courseIdLabel, courseIdInput);
        
        TextField courseNameInput= new TextField();
        Label courseNameLabel = new Label("Kurssin nimi");
        HBox courseNamePane = new HBox(10);
        courseNamePane.setPadding(new Insets(10));
        courseNamePane.getChildren().addAll(courseNameLabel, courseNameInput);
        
        TextField courseCreditInput = new TextField();
        Label courseCreditLabel = new Label("Opintopisteet");
        HBox courseCreditPane = new HBox(10);
        courseCreditPane.setPadding(new Insets(10));
        courseCreditPane.getChildren().addAll(courseCreditLabel, courseCreditInput);
        
        TextField courseProfInput = new TextField();
        Label courseProfLabel = new Label("Kurssin vastuuopettaja"); 
        HBox courseProfPane = new HBox(10);
        courseProfPane.setPadding(new Insets(10));
        courseProfPane.getChildren().addAll(courseProfLabel, courseProfInput);

        
        ChoiceBox degreeBox = new ChoiceBox();
        degreeBox.getItems().add("Kandidaatti");
        degreeBox.getItems().add("Maisteri");

        CheckBox passed = new CheckBox("Kurssi suoritettu");
        
        createCourse.setOnAction(e->{
            int courseId = Integer.parseInt(courseIdInput.getText());
            String courseName = courseNameInput.getText();
            int credits = Integer.parseInt(courseCreditInput.getText());
            String prof = courseProfInput.getText();
            Degree degree = Degree.NULL;
            if (degreeBox.getValue().equals("Kandidaatti")) {
                degree = Degree.BACHELOR;
            } else if (degreeBox.getValue().equals("Maisteri")) {
                degree = Degree.MASTER;
            } else {
                createCourseMessage.setText("Onko kurssi kandi- vai maisterivaiheen kurssi?");
                createCourseMessage.setTextFill(Color.RED);
            }

            boolean finished = passed.isSelected();
            if (logic.addCourse(logic.getLoggedInStudent(), courseId, courseName, credits, prof, degree, finished)) {
                createCourseMessage.setText("Kurssi lisätty opiskelijalle" + logic.getLoggedInStudent());
                createCourseMessage.setTextFill(Color.GREEN);
            } else {
                createCourseMessage.setText("Tarkista, että kaikki kentät on täytetty!");
                createCourseMessage.setTextFill(Color.RED);
            }
            redrawCourseList(logic.getLoggedInStudent());
        });
        
        createCourseForm.getChildren().addAll(createCourseMessage, spacer, courseIdPane, courseNamePane, courseCreditPane, courseProfPane, degreeBox, passed, createCourse);
        
        courseNodes = new VBox(10);
        courseNodes.setMaxWidth(280);
        courseNodes.setMaxHeight(280);
        redrawCourseList(logic.getLoggedInStudent());
        
        courseScrollBar.setContent(courseNodes);
        mainPane.setBottom(createCourseForm);
        mainPane.setTop(menuPane);
        
        primary.setTitle("Opintojen seurantasovellus");
        primary.setScene(loginScene);
        primary.show();
        primary.setOnCloseRequest(e->{
            System.out.println("Suljetaan...");
            System.out.println(logic.getLoggedInStudent());
            if (logic.getLoggedInStudent()!=null) {
                e.consume();
            }
        });
    }
    
    @Override
    public void stop(){
        System.out.println("Suljetaan sovellus");
        Platform.exit();
        System.exit(0);
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
