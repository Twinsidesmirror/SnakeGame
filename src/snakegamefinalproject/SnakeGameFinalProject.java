/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegamefinalproject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author nattakarnpl
 */
public class SnakeGameFinalProject extends Application {
    
    static int block_size = 10;
    int width = 30;
    int height = 35;
    int il = 5;
    long then = System.nanoTime();
    
    boolean changed = false;
    int nextUpdate;
    boolean hasNext = false;
    Field f;
    int userId;
    
    @Override
    public void start(Stage primaryStage) {

        /*Register Scene*/
        
        GridPane Reg = new GridPane();
        Reg.setAlignment(Pos.CENTER);
        Reg.setHgap(10);
        Reg.setVgap(10);
        Reg.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitleReg = new Text("Register");
        scenetitleReg.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        Reg.add(scenetitleReg, 0, 0, 2, 1);

        Label userNameReg = new Label("User Name:");
        Reg.add(userNameReg, 0, 1);

        TextField userTextFieldReg = new TextField();
        Reg.add(userTextFieldReg, 1, 1);

        Label pwReg = new Label("Password:");
        Reg.add(pwReg, 0, 2);

        PasswordField pwBoxReg = new PasswordField();
        Reg.add(pwBoxReg, 1, 2);
        Button backReg = new Button("Back");
        Button signUpBtn = new Button("SignUp");
        HBox hbRegBtn = new HBox(10);
        hbRegBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbRegBtn.getChildren().addAll(backReg,signUpBtn);
        Reg.add(hbRegBtn, 1, 4);
        final Text actiontarget = new Text();
        Reg.add(actiontarget, 1, 6);
        Scene sceneReg = new Scene(Reg,width*block_size,height*block_size);
        final Text errReg = new Text();
        Reg.add(errReg, 1, 6);

        /*History Scene*/
        GridPane profile = new GridPane();
        profile.setAlignment(Pos.CENTER);
        profile.setHgap(10);
        profile.setVgap(10);
        profile.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitleProfile = new Text("Nattakarn");
        scenetitleProfile.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        HBox headerProfile = new HBox();
        Button editProfile = new Button("Edit");
        headerProfile.getChildren().addAll(scenetitleProfile,editProfile);
        profile.add(headerProfile, 0, 0, 2, 1);

        TableView tableView = new TableView();
        profile.add(tableView,1, 1);
        Button logoutBtn = new Button("Logout");
        Button startBtn = new Button("Start");
        HBox hbProfileBtn = new HBox(10);
        hbProfileBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbProfileBtn.getChildren().addAll(logoutBtn,startBtn);
        profile.add(hbProfileBtn, 1, 4);
        Scene sceneProfile = new Scene(profile,width*block_size,height*block_size);

        /*Edit Scene*/
        GridPane Edit = new GridPane();
        Edit.setAlignment(Pos.CENTER);
        Edit.setHgap(10);
        Edit.setVgap(10);
        Text scenetitleEdit = new Text("Edit account");
        scenetitleEdit.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        HBox headerEdit = new HBox();
        Hyperlink linkDel = new Hyperlink("Delete my account");
        headerEdit.getChildren().addAll(scenetitleEdit,linkDel);
        Edit.add(headerEdit, 0, 0, 2, 1);
        
        Label userNameEdit = new Label("User Name:");
        Edit.add(userNameEdit, 0, 1);

        TextField userTextFieldEdit = new TextField();
        Edit.add(userTextFieldEdit, 1, 1);

        Label pwEdit = new Label("Password:");
        Edit.add(pwEdit, 0, 2);

        PasswordField pwBoxEdit = new PasswordField();
        Edit.add(pwBoxEdit, 1, 2);
        Button backEdit = new Button("Back");
        Button confirmBtn = new Button("Confirm");
        HBox hbEditBtn = new HBox(10);
        hbEditBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbEditBtn.getChildren().addAll(backEdit,confirmBtn);
        Edit.add(hbEditBtn, 1, 4);
        Scene sceneEdit = new Scene(Edit,width*block_size,height*block_size);
        final Text errEdit = new Text();
        Edit.add(errEdit, 1, 6);
        backEdit.setOnAction(e->primaryStage.setScene(sceneProfile));
        editProfile.setOnAction(e->primaryStage.setScene(sceneEdit));
        
        /*LogIn Scene*/
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("Login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        Button btn = new Button("Login");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        Hyperlink linkReg = new Hyperlink("SignUp");
        hbBtn.getChildren().addAll(linkReg,btn);
        grid.add(hbBtn, 1, 4);
        final Text err = new Text();
        grid.add(err, 1, 6);
        linkReg.setOnAction(e->primaryStage.setScene(sceneReg));
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(true){
                    primaryStage.setScene(sceneProfile);

                }else{
                    err.setFill(Color.FIREBRICK);
                    err.setText("Incorrect username or password!");
                }
            }
        });
        Scene sceneLogin = new Scene(grid,width*block_size,height*block_size);
        backReg.setOnAction(e->primaryStage.setScene(sceneLogin));
        logoutBtn.setOnAction(e->primaryStage.setScene(sceneLogin));
        signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if(true){
                    primaryStage.setScene(sceneLogin);
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setContentText("Success Registration");
                    a.show();
                }else{
                    errReg.setFill(Color.FIREBRICK);
                    errReg.setText("This username is used");
                }
            }
        });
        linkDel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                    primaryStage.setScene(sceneLogin);
                    Alert a = new Alert(AlertType.INFORMATION);
                    a.setContentText("Account Deleted");
                    a.show();
            }
        });
       
        /*Game Scene*/
        
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        
        f = new Field(width,height);
        f.addSnake(new Snake(il,f));
        
        Label score = new Label("Score: 0");
        
        root.getChildren().addAll(f, score);

        AnimationTimer timer = new AnimationTimer(){
            public void handle(long now){
                if(now - then > 1000000000/20){
                    f.update();
                    then = now;
                    score.setText("Score: "+ f.score);
                    changed = false;
                    if(hasNext){
                        setDirection(f.snake, nextUpdate);
                        hasNext = false;
                    }
                    if(f.isDead()){
                        stop();
                        Alert al = new Alert(AlertType.INFORMATION);
                        al.setHeaderText("You lost");
                        al.setContentText("Your score is "+ f.score);
                        Platform.runLater(al::showAndWait);
                        al.setOnHidden(e -> {
                            primaryStage.setScene(sceneProfile);
                            root.getChildren().clear();
                            f = new Field(width, height);
                            f.addSnake(new Snake(il, f));
                            score.setText("Score: 0");
                            root.getChildren().addAll(f, score);
                            start();
                        });
                    }
                }
            }
        };
        timer.start();


        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if(event.getCode().equals(KeyCode.UP) && 
                    f.snake.getDirection() != Block.DOWN){
                setDirection(f.snake, Block.UP);
            }
            if(event.getCode().equals(KeyCode.RIGHT) && 
                    f.snake.getDirection() != Block.LEFT){
                setDirection(f.snake, Block.RIGHT);
            }
            if(event.getCode().equals(KeyCode.DOWN) && 
                    f.snake.getDirection() != Block.UP){
                setDirection(f.snake, Block.DOWN);
            }
            if(event.getCode().equals(KeyCode.LEFT) && 
                    f.snake.getDirection() != Block.RIGHT){
                setDirection(f.snake, Block.LEFT);
            }
        });
        
        startBtn.setOnAction(e->primaryStage.setScene(scene));
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("SNAKE GAME");
        primaryStage.setScene(sceneLogin);
        primaryStage.show();
    }
    
    public void setDirection(Snake s, int d){
        if(!changed){
            s.setDirection(d);
            changed = true;
        }else{
            hasNext = true;
            nextUpdate =d;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
