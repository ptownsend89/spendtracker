package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.text.Position;
import java.sql.SQLException;


public class Main extends Application {
    // -------------------------------------------------------------------
// set global variables for actions:
    GridPane grid = new GridPane();


    // -------------------------------------------------------------------------
    // -------------Set Stage for starting application -------------------------
    @Override
    public void start(Stage primaryStage) throws Exception { // stage is the full window  -- scene is what happens inside it
        // setting window layout:
        VBox layout = new VBox();
        layout.setFillWidth(false);
        layout.setVisible(true);
        VBox layoutScene2 = new VBox();
        layout.setAlignment(Pos.CENTER);
        layoutScene2.setAlignment(Pos.CENTER);

        //
        Button login = new Button();
        // maxSize of 100.0, 5.0 large enough for "Click me" text:
        login.setMaxSize(100.0,5.0);
        login.setText("Log in");

        //
        Button verify = new Button();
        verify.setMaxSize(100.0,5.0);
        verify.setAlignment(Pos.BOTTOM_RIGHT);
        verify.setText("Verify");

        TextField tField = new TextField();
        tField.setAlignment(Pos.CENTER_RIGHT);
        tField.setMaxWidth(100);

        Label title = new Label("Login to Banking App:");
        title.setFont(new Font("Arial",30));
        title.setAlignment(Pos.TOP_CENTER);
        layout.setMargin(title,new Insets(10,10,10,10)); // sets the margin around specified Node i.e. title

        Label label1 = new Label("Enter Username: ");
        label1.setAlignment(Pos.CENTER_LEFT);

        PasswordField pw = new PasswordField();
        pw.setAlignment(Pos.BOTTOM_RIGHT);
        pw.setMaxWidth(100);

        Label label2 = new Label("Enter Password: ");
        label2.setAlignment(Pos.BOTTOM_LEFT);

        Button buttonTwo = new Button();
        buttonTwo.setMaxSize(100.0,5.0);
        buttonTwo.setAlignment(Pos.BASELINE_RIGHT);
        buttonTwo.setText("Verify User");

        // set scene sizing and inserting layout
        Scene scene1 = new Scene(layout, 500, 400);
        Scene scene2 = new Scene(layoutScene2,500, 400);

        layout.getChildren().addAll(label1,login);
        layout.setMargin(login, new Insets(10,10,10,10));
        layoutScene2.getChildren().addAll(label2,buttonTwo);

        // action event to set what happens when button pressed:
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                layout.getChildren().remove(label1);
                layout.getChildren().remove(login);
                layout.getChildren().addAll(title,label1,tField,label2,pw,buttonTwo);
                layout.setMargin(buttonTwo, new Insets(10,10,10,10));
                //primaryStage.setScene(scene2);
            }
        };

        // call to action event for when first button pressed:
        login.setOnAction(event);

        // second action event to set when 2nd button pressed:
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    MySQLConn sqlConn = new MySQLConn();
                    sqlConn.verifyUser()
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                primaryStage.setScene(scene1);
            }
        };

        // when 2nd button pressed:
        buttonTwo.setOnAction(event2);

        primaryStage.setTitle("NEW APP");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    public static void main (String[]args){
        launch(args);
    }
}

