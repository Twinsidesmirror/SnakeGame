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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    
    @Override
    public void start(Stage primaryStage) {
       
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        
        f = new Field(width,height);
        f.addSnake(new Snake(il,f));
        
        Label score = new Label("Score: 0");
        
        root.getChildren().addAll(f, score);

        AnimationTimer timer = new AnimationTimer(){
            public void handle(long now){
                if(now - then > 100000000/8){
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
                            root.getChildren().clear();
                            Field f = new Field(width, height);
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
        
        primaryStage.setResizable(false);
        primaryStage.setTitle("SNAKE GAME");
        primaryStage.setScene(scene);
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
