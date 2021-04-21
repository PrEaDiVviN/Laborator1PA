package compulsory.gui;

import compulsory.dao.Employees;
import compulsory.dao.Movies;
import compulsory.exceptions.NoEmployeeException;
import compulsory.exceptions.NoMovieException;
import compulsory.model.Employee;
import compulsory.model.Movie;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class userVisualInteraction extends Application {

    @Override
    public void start(Stage primaryStage) {


        primaryStage.setTitle("Database Access Interface");
        javafx.scene.control.Button btnMovie = new Button();

        javafx.scene.control.Button btnEmployee = new Button();
        javafx.scene.control.Button btnExecuteSearchForMovie = new Button();
        javafx.scene.control.Button btnExecuteInsertForMovie = new Button();
        javafx.scene.control.Button btnExecuteSearchForEmployee = new Button();
        javafx.scene.control.Button btnExecuteInsertForEmployee = new Button();

        btnMovie.setText("Acceseaza filmele");
        btnMovie.setMinWidth(400);
        btnMovie.setMinHeight(50);
        btnMovie.setLayoutX(10);
        btnMovie.setId("btn2");

        btnEmployee.setText("Acceseaza Angajatii");
        btnEmployee.setMinWidth(400);
        btnEmployee.setLayoutX(420);
        btnEmployee.setMinHeight(50);
        btnEmployee.setId("btn2");


        btnExecuteSearchForMovie.setText("Executa cautarea!");
        btnExecuteSearchForMovie.setLayoutX(100);
        btnExecuteSearchForMovie.setLayoutY(600);
        btnExecuteSearchForMovie.setId("btnsrc");
        btnExecuteSearchForMovie.setMinWidth(200);
        btnExecuteSearchForMovie.setMinHeight(35);

        btnExecuteInsertForMovie.setText("Executa inserarea!");
        btnExecuteInsertForMovie.setLayoutX(500);
        btnExecuteInsertForMovie.setLayoutY(600);
        btnExecuteInsertForMovie.setId("btnins");
        btnExecuteInsertForMovie.setMinWidth(200);
        btnExecuteInsertForMovie.setMinHeight(35);


        btnExecuteSearchForEmployee.setText("Executa cautarea!");
        btnExecuteSearchForEmployee.setLayoutX(100);
        btnExecuteSearchForEmployee.setLayoutY(600);
        btnExecuteSearchForEmployee.setId("btnsrc");
        btnExecuteSearchForEmployee.setMinWidth(200);
        btnExecuteSearchForEmployee.setMinHeight(35);

        btnExecuteInsertForEmployee.setText("Executa inserarea!");
        btnExecuteInsertForEmployee.setLayoutX(500);
        btnExecuteInsertForEmployee.setLayoutY(600);
        btnExecuteInsertForEmployee.setId("btnins");
        btnExecuteInsertForEmployee.setMinWidth(200);
        btnExecuteInsertForEmployee.setMinHeight(35);


        javafx.scene.text.Text labelHelp = new Text();
        labelHelp.setLayoutX(100);
        labelHelp.setLayoutY(100);

        javafx.scene.text.Text labelHelpInsertId = new Text();
        labelHelpInsertId.setLayoutX(500);
        labelHelpInsertId.setLayoutY(100);
        javafx.scene.control.TextArea txtInsertId = new TextArea();
        txtInsertId.setMaxWidth(200);
        txtInsertId.setMaxHeight(25);
        txtInsertId.setLayoutX(500);
        txtInsertId.setLayoutY(120);

        //title
        javafx.scene.text.Text labelHelpInsertTitle = new Text();
        labelHelpInsertTitle.setLayoutX(500);
        labelHelpInsertTitle.setLayoutY(200);
        javafx.scene.control.TextArea txtInsertTitle = new TextArea();
        txtInsertTitle.setMaxWidth(200);
        txtInsertTitle.setMaxHeight(25);
        txtInsertTitle.setLayoutX(500);
        txtInsertTitle.setLayoutY(220);

        //releasedate yyyy-mm-dd
        javafx.scene.text.Text labelHelpInsertRelease = new Text();
        labelHelpInsertRelease.setLayoutX(500);
        labelHelpInsertRelease.setLayoutY(300);
        javafx.scene.control.TextArea txtInsertRelease = new TextArea();
        txtInsertRelease.setMaxWidth(200);
        txtInsertRelease.setMaxHeight(25);
        txtInsertRelease.setLayoutX(500);
        txtInsertRelease.setLayoutY(320);

        //duration hh-mm-ss
        javafx.scene.text.Text labelHelpInsertDuration = new Text();
        labelHelpInsertDuration.setLayoutX(500);
        labelHelpInsertDuration.setLayoutY(400);
        javafx.scene.control.TextArea txtInsertDuration = new TextArea();
        txtInsertDuration.setMaxWidth(200);
        txtInsertDuration.setMaxHeight(25);
        txtInsertDuration.setLayoutX(500);
        txtInsertDuration.setLayoutY(420);

        //score-double
        javafx.scene.text.Text labelHelpInsertScore = new Text();
        labelHelpInsertScore.setLayoutX(500);
        labelHelpInsertScore.setLayoutY(500);
        javafx.scene.control.TextArea txtInsertScore = new TextArea();
        txtInsertScore.setMaxWidth(200);
        txtInsertScore.setMaxHeight(25);
        txtInsertScore.setLayoutX(500);
        txtInsertScore.setLayoutY(520);

        javafx.scene.control.TextArea txt = new TextArea();
        txt.setMaxWidth(200);
        txt.setLayoutY(120);
        txt.setMaxHeight(25);
        txt.setLayoutX(100);

        javafx.scene.text.Text labelForAnswer = new Text();
        labelForAnswer.setLayoutY(200);
        labelForAnswer.setLayoutX(100);
        labelForAnswer.setWrappingWidth(200);
        labelForAnswer.setText("Raspuns-ul oferit pentru operatia anterioara este urmatorul: ");


        javafx.scene.text.Text labelAnswer = new Text();
        labelAnswer.setLayoutX(100);
        labelAnswer.setLayoutY(250);
        labelAnswer.setWrappingWidth(200);


        String context = new String("");
        int newline = 0;

        labelAnswer.setText(context);

        Pane root = new Pane();

        InputStream stream = null;
        try {
            stream = new FileInputStream("C:\\Users\\PrEaD\\Desktop\\Anul2_semestrul2\\PA\\Laboratul8\\src\\main\\java\\compulsory\\gui\\database.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(127);
        imageView.setY(100);
        imageView.setFitWidth(575);
        imageView.setPreserveRatio(true);

        btnEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(root.getChildren().contains(imageView))
                    root.getChildren().remove(imageView);
                btnMovie.setId("btn2");
                btnEmployee.setId("btn1");
                labelAnswer.setText("");
                if(!root.getChildren().contains(labelHelp)) {
                    root.getChildren().add(labelForAnswer);
                    btnExecuteSearchForMovie.setText("Executa cautarea!");
                    btnExecuteInsertForMovie.setText("Executa inserarea!");
                    labelHelp.setText("Introduceti un id sau un nume de angajat: ");
                    labelHelpInsertId.setText("Introduceti id-ul angajatului: ");
                    labelHelpInsertTitle.setText("Introduceti numele angajatului: ");
                    labelHelpInsertRelease.setText("Introduceti inaltimea angajatului(cm): ");
                    labelHelpInsertDuration.setText("Introduceti data de nastere a angajului: ");
                    labelHelpInsertScore.setText("Introduceti tipul angajatului ");


                    root.getChildren().add(labelHelp);
                    root.getChildren().add(labelHelpInsertId);
                    root.getChildren().add(txtInsertId);
                    root.getChildren().add(labelHelpInsertTitle);
                    root.getChildren().add(txtInsertTitle);

                    root.getChildren().add(labelHelpInsertRelease);
                    root.getChildren().add(txtInsertRelease);

                    root.getChildren().add(labelHelpInsertDuration);
                    root.getChildren().add(txtInsertDuration);

                    root.getChildren().add(labelHelpInsertScore);
                    root.getChildren().add(txtInsertScore);

                    root.getChildren().add(labelAnswer);
                    root.getChildren().add(btnExecuteSearchForEmployee);
                    root.getChildren().add(btnExecuteInsertForEmployee);
                    root.getChildren().add(txt);
                }
                else {
                    if(!root.getChildren().contains(btnExecuteInsertForEmployee)) {
                        root.getChildren().add(btnExecuteInsertForEmployee);
                        root.getChildren().add(btnExecuteSearchForEmployee);
                    }
                    root.getChildren().remove(btnExecuteSearchForMovie);
                    root.getChildren().remove(btnExecuteInsertForMovie);
                    btnExecuteSearchForMovie.setText("Executa cautarea!");
                    btnExecuteInsertForMovie.setText("Executa inserarea!");
                    labelHelp.setText("Introduceti un id sau un nume de angajat: ");
                    labelHelpInsertId.setText("Introduceti id-ul angajatului: ");
                    labelHelpInsertTitle.setText("Introduceti numele angajatului: ");
                    labelHelpInsertRelease.setText("Introduceti inaltimea angajatului(cm): ");
                    labelHelpInsertDuration.setText("Introduceti data de nastere a angajului: ");
                    labelHelpInsertScore.setText("Introduceti tipul angajatului ");
                }
            }
        });

        btnMovie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(root.getChildren().contains(imageView))
                    root.getChildren().remove(imageView);
                btnMovie.setId("btn1");
                btnEmployee.setId("btn2");
                labelAnswer.setText("");
                if(!root.getChildren().contains(labelHelp)) {
                    root.getChildren().add(labelForAnswer);
                    btnExecuteSearchForMovie.setText("Executa cautarea!");
                    btnExecuteInsertForMovie.setText("Executa inserarea!");
                    labelHelp.setText("Introduceti un id sau un titlu de film: ");
                    labelHelpInsertId.setText("Introduceti id-ul filmului: ");
                    labelHelpInsertTitle.setText("Introduceti titlul filmului: ");
                    labelHelpInsertRelease.setText("Introduceti data de lansare a filmului(yyyy-mm-dd): ");
                    labelHelpInsertDuration.setText("Introduceti durata filmului(hh:mm:ss): ");
                    labelHelpInsertScore.setText("Introduceti scor-ul filmului: ");

                    root.getChildren().add(labelHelp);
                    root.getChildren().add(labelHelpInsertId);
                    root.getChildren().add(txtInsertId);
                    root.getChildren().add(labelHelpInsertTitle);
                    root.getChildren().add(txtInsertTitle);

                    root.getChildren().add(labelHelpInsertRelease);
                    root.getChildren().add(txtInsertRelease);

                    root.getChildren().add(labelHelpInsertDuration);
                    root.getChildren().add(txtInsertDuration);

                    root.getChildren().add(labelHelpInsertScore);
                    root.getChildren().add(txtInsertScore);

                    root.getChildren().add(labelAnswer);
                    root.getChildren().add(btnExecuteSearchForMovie);
                    root.getChildren().add(btnExecuteInsertForMovie);
                    root.getChildren().add(txt);
                }
                else {
                    root.getChildren().remove(btnExecuteInsertForEmployee);
                    root.getChildren().remove(btnExecuteSearchForEmployee);
                    if (!root.getChildren().contains(btnExecuteInsertForMovie)) {
                        root.getChildren().add(btnExecuteSearchForMovie);
                        root.getChildren().add(btnExecuteInsertForMovie);
                    }
                    btnExecuteSearchForMovie.setText("Executa cautarea!");
                    btnExecuteInsertForMovie.setText("Executa inserarea!");
                    labelHelp.setText("Introduceti un id sau un titlu de film: ");
                    labelHelpInsertId.setText("Introduceti id-ul filmului: ");
                    labelHelpInsertTitle.setText("Introduceti titlul filmului: ");
                    labelHelpInsertRelease.setText("Introduceti data de lansare a filmului(yyyy-mm-dd): ");
                    labelHelpInsertDuration.setText("Introduceti durata filmului(hh:mm:ss): ");
                    labelHelpInsertScore.setText("Introduceti scor-ul filmului: ");
                }
            }
        });

        btnExecuteInsertForMovie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Movies moviesDao = new Movies();
                Movie movie = new Movie();
                try {
                    movie = new Movie(Integer.parseInt(txtInsertId.getText()), txtInsertTitle.getText(), Date.valueOf(txtInsertRelease.getText()),
                            Time.valueOf(txtInsertDuration.getText()), Integer.parseInt(txtInsertScore.getText()));
                }
                catch (Exception e) {
                    labelAnswer.setText("Oops! The movie could not be inserted! Why: " + e.getMessage());
                    labelAnswer.setFill(Color.RED);
                }
                if(movie.getTitle() != null) {
                    try {
                        moviesDao.InsertMovie(movie);
                        labelAnswer.setText("The movie was successfully inserted!");
                        labelAnswer.setFill(Color.GREEN);
                    } catch (NoMovieException e) {
                        labelAnswer.setText("Oops! The movie could not be inserted! Why: " + e.getMessage());
                        labelAnswer.setFill(Color.RED);
                    }
                }
            }
        });

        btnExecuteSearchForMovie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Movies moviesDao = new Movies();
                Movie movie = new Movie();
                if(txt.getText().matches("[0-9]+")) {
                    try {
                        movie = moviesDao.getMovieById(Integer.parseInt(txt.getText()));
                        labelAnswer.setText(movie.toString());
                        labelAnswer.setFill(Color.GREEN);
                    } catch (NoMovieException e) {
                        labelAnswer.setText("Oops! The database does not contain the movie you are looking for..");
                        labelAnswer.setFill(Color.RED);
                    }
                }
                else {
                    try {
                        movie = moviesDao.getMovieByTitle(txt.getText());
                        labelAnswer.setText(movie.toString());
                        labelAnswer.setFill(Color.GREEN);
                    } catch (NoMovieException e) {
                        labelAnswer.setText("Oops! The database does not contain the movie you are looking for..");
                        labelAnswer.setFill(Color.RED);
                    }
                }

            }
        });

        btnExecuteSearchForEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Employees employeesDao = new Employees();
                Employee employee = new Employee();
                if(txt.getText().matches("[0-9]+")) {
                    try {
                        employee = employeesDao.getEmployeeById(Integer.parseInt(txt.getText()));
                        labelAnswer.setText(employee.toString());
                        labelAnswer.setFill(Color.GREEN);
                    } catch (NoEmployeeException e) {
                        labelAnswer.setText("Oops! The database does not contain the employee you are looking for..");
                        labelAnswer.setFill(Color.RED);
                    }
                }
                else {
                    try {
                        employee = employeesDao.getEmployeeByName(txt.getText());
                        labelAnswer.setText(employee.toString());
                        labelAnswer.setFill(Color.GREEN);
                    } catch (NoEmployeeException e) {
                        labelAnswer.setText("Oops! The database does not contain the employee you are looking for..");
                        labelAnswer.setFill(Color.RED);
                    }
                }
            }
        });

        btnExecuteInsertForEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Employees employeesDao = new Employees();
                Employee employee = new Employee();
                try {
                    employee = new Employee(Integer.parseInt(txtInsertId.getText()), txtInsertTitle.getText(), txtInsertTitle.getText(), Integer.parseInt(txtInsertRelease.getText()),
                            txtInsertDuration.getText(), txtInsertScore.getText());
                }
                catch (Exception e) {
                    labelAnswer.setText("Oops! The employee could not be inserted! Why: " + e.getMessage());
                    labelAnswer.setFill(Color.RED);
                }
                if(employee.getName() != null) {
                    try {
                        employeesDao.InsertEmployee(employee);
                        labelAnswer.setText("The employee was successfully inserted!");
                        labelAnswer.setFill(Color.GREEN);
                    } catch (NoEmployeeException e) {
                        labelAnswer.setText("Oops! The employee could not be inserted! Why: " + e.getMessage());
                        labelAnswer.setFill(Color.RED);
                    }
                }
            }
        });



        root.getChildren().add(btnMovie);
        root.getChildren().add(btnEmployee);
        root.getChildren().add(imageView);


        Scene scene = new Scene(root, 830, 700);
        // apply stylesheet to the scene graph
        scene.getStylesheets().add(this.getClass().getResource("/stylesheet.css").toExternalForm());


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static  void main(String args[]) {
        launch();
    }
}
