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
        String degree;
        if(course.getDegree() == Degree.BACHELOR) {
            degree = "kandi";
        } else {
            degree = "maisteri";
        }
        String labelText = course.getName() +  ", " + course.getCredits() + " op., suoritettu: " + course.getFinished() + ", tutkinto: " + degree;
        Label label = new Label(labelText);
        label.setMinHeight(28);

        Button button = new Button("Course passed");
        button.setOnAction(e->{
        logic.finishCourse(studentId, course);
        redrawCourseList(studentId);
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0,5,0,5));
        
        box.getChildren().addAll(label, spacer);
        if (course.getFinished() == false) {
            box.getChildren().addAll(button);
        }
        return box;
    }
    
    public void redrawCourseList(String studentId) {
        courseNodes.getChildren().clear();
        
        List<Course> courses = logic.getCourses(studentId);

        courses.forEach(course ->{
            courseNodes.getChildren().add(createCourseNode(course, studentId));
        });

    }
    
    public void drawCoursesNotPassed(String studentId) {
        courseNodes.getChildren().clear();
        
        List<Course> courses = logic.listCoursesNotPassed(studentId);

        courses.forEach(course ->{
            courseNodes.getChildren().add(createCourseNode(course, studentId));
        });

    }
    
    public void drawCoursesPassed(String studentId) {
        courseNodes.getChildren().clear();
        
        List<Course> courses = logic.listCoursesPassed(studentId);

        courses.forEach(course ->{
            courseNodes.getChildren().add(createCourseNode(course, studentId));
        });

    }

    @Override
    public void start(Stage primary) throws Exception {
        
        VBox loginPane = new VBox(10);
        VBox inputPane = new VBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Opiskelijanumero");
        loginLabel.setMinWidth(Region.USE_PREF_SIZE);
        Label passwordLabel = new Label("Salasana");
        passwordLabel.setMinWidth(Region.USE_PREF_SIZE);
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
        
        loginScene = new Scene(loginPane, 800, 800, Color.AQUA);
        
        VBox newStudentPane = new VBox(20);
        
        HBox newStudentIdPane = new HBox(20);
        newStudentIdPane.setPadding(new Insets(20));
        TextField newStudentIdInput = new TextField();
        Label newStudentIdLabel = new Label("Opiskelijanumero");
        newStudentIdLabel.setMinWidth(Region.USE_PREF_SIZE);
        newStudentIdPane.getChildren().addAll(newStudentIdLabel, newStudentIdInput);
        
        HBox newNamePane = new HBox(20);
        newNamePane.setPadding(new Insets(20));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("Opiskelijan nimi");
        newNameLabel.setMinWidth(Region.USE_PREF_SIZE);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);
        
        HBox uniPane = new HBox(20);
        uniPane.setPadding(new Insets(20));
        TextField uniInput = new TextField();
        Label uniLabel = new Label("Oppilaitos");
        uniLabel.setMinWidth(Region.USE_PREF_SIZE);
        uniPane.getChildren().addAll(uniLabel, uniInput);
        
        HBox newPasswordPane = new HBox(20);
        newPasswordPane.setPadding(new Insets(20));
        PasswordField newPasswordInput = new PasswordField();
        Label newPasswordLabel = new Label("Salasana");
        newPasswordLabel.setMinWidth(Region.USE_PREF_SIZE);
        newPasswordPane.getChildren().addAll(newPasswordLabel, newPasswordInput);
        
        HBox confirmPane = new HBox(20);
        confirmPane.setPadding(new Insets(20));
        PasswordField confirmInput = new PasswordField();
        Label confirmLabel = new Label("Toista salasana");
        confirmLabel.setMinWidth(Region.USE_PREF_SIZE);
        confirmPane.getChildren().addAll(confirmLabel, confirmInput);
        
        Label studentCreationMessage = new Label();
        
        Button createStudentButton = new Button("Luo tunnus");
        createStudentButton.setPadding(new Insets(20));
        
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
                    loginMessage.setText("");
                    primary.setScene(loginScene);
                } else if (logic.addStudent(newName, newStudentId, newUni, password) == false) {
                    studentCreationMessage.setText("Opiskelijanumerolla on jo tunnus!");
                    studentCreationMessage.setTextFill(Color.RED);
                }
                
            } else if (logic.confirmPassword(password, confirm) == false) {
                studentCreationMessage.setText("Syöttämäsi salasanat eivät täsmänneet!");
                studentCreationMessage.setTextFill(Color.RED);
            }
        });
        
        newStudentPane.getChildren().addAll(studentCreationMessage, newStudentIdPane, newNamePane, uniPane, newPasswordPane, confirmPane, createStudentButton);
        
        newStudentScene = new Scene(newStudentPane, 800, 800);
        
        ScrollPane courseScrollBar = new ScrollPane();
        BorderPane mainPane = new BorderPane(courseScrollBar);
        listScene = new Scene(mainPane, 800, 800);
        
        HBox menuPane = new HBox(20);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("Kirjaudu ulos");
        Button listNotPassed = new Button("Suorittamattomat");
        Button listPassed = new Button("Suoritetut");
        Button listAll = new Button("Kaikki");
        menuPane.getChildren().addAll(menuLabel, menuSpacer, listAll, listNotPassed, listPassed, logoutButton);
        
        logoutButton.setOnAction(e->{
            logic.logOut();
            primary.setScene(loginScene);
        });
        
        listNotPassed.setOnAction(e->{
            drawCoursesNotPassed(logic.getLoggedInStudent());
        });
        
        listPassed.setOnAction(e->{
            drawCoursesPassed(logic.getLoggedInStudent());
        });
        
        listAll.setOnAction(e->{
            redrawCourseList(logic.getLoggedInStudent());
        });
        
        Label createCourseMessage = new Label();
        VBox createCourseForm = new VBox(20);
        Button createCourse = new Button("Lisää kurssi");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        TextField courseIdInput = new TextField();
        Label courseIdLabel = new Label("Kurssinumero");
        courseIdLabel.setMinWidth(Region.USE_PREF_SIZE);
        HBox courseIdPane = new HBox(20);
        courseIdPane.setPadding(new Insets(20));
        courseIdPane.getChildren().addAll(courseIdLabel, courseIdInput);
        
        TextField courseNameInput= new TextField();
        Label courseNameLabel = new Label("Kurssin nimi");
        courseNameLabel.setMinWidth(Region.USE_PREF_SIZE);
        HBox courseNamePane = new HBox(20);
        courseNamePane.setPadding(new Insets(20));
        courseNamePane.getChildren().addAll(courseNameLabel, courseNameInput);
        
        TextField courseCreditInput = new TextField();
        Label courseCreditLabel = new Label("Opintopisteet");
        courseCreditLabel.setMinWidth(Region.USE_PREF_SIZE);
        HBox courseCreditPane = new HBox(20);
        courseCreditPane.setPadding(new Insets(20));
        courseCreditPane.getChildren().addAll(courseCreditLabel, courseCreditInput);
        
        TextField courseProfInput = new TextField();
        Label courseProfLabel = new Label("Kurssin vastuuopettaja"); 
        courseProfLabel.setMinWidth(Region.USE_PREF_SIZE);
        HBox courseProfPane = new HBox(20);
        courseProfPane.setPadding(new Insets(20));
        courseProfPane.getChildren().addAll(courseProfLabel, courseProfInput);

        
        ChoiceBox degreeBox = new ChoiceBox();
        Label courseDegreeLabel = new Label("Kurssi kuuluu tutkintoon"); 
        courseDegreeLabel.setMinWidth(Region.USE_PREF_SIZE);
        HBox courseDegreePane = new HBox(20);
        degreeBox.getItems().add("Kandidaatti");
        degreeBox.getItems().add("Maisteri");
        courseDegreePane.getChildren().addAll(courseDegreeLabel, degreeBox);
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
                redrawCourseList(logic.getLoggedInStudent());
                createCourseForm.getChildren().clear();
            } else {
                createCourseMessage.setText("Tarkista, että kaikki kentät on täytetty!");
                createCourseMessage.setTextFill(Color.RED);
            }
        });
        
        createCourseForm.getChildren().addAll(createCourseMessage, spacer, courseIdPane, courseNamePane, courseCreditPane, courseProfPane, courseDegreePane, passed, createCourse);
        
        courseNodes = new VBox(20);
        courseNodes.setMaxWidth(800);
        courseNodes.setMaxHeight(500);
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
        logic.logOut();
        System.out.println("Suljetaan sovellus");
        Platform.exit();
        System.exit(0);
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
