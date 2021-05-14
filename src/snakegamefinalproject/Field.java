/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegamefinalproject;

import java.util.ArrayList;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author nattakarnpl
 */
public class Field extends Pane{
    private int width;
    private int height;
    
    ArrayList<Block> blocks = new ArrayList<Block>();
    int score = 0;
    Food f;
    Snake snake;
    
    
    public Field(int width, int height){
        this.width = width;
        this.height = height;
        
        setMinSize(width * SnakeGameFinalProject.block_size,height * 
                SnakeGameFinalProject.block_size);
        setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        setBorder(new Border(new BorderStroke(Color.WHITE, 
                BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
        addFood();
        
    }

    
    public void update(){
        for(Block b:blocks){
            b.update();
        }
        if (isEaten(f)){
            addFood();
            addNewBlock();
            score += 20;
        }
    }
    
    public boolean isDead(){
        for(Block b:blocks){
            if(b != snake.head){
                if(b.posX == snake.head.posX && b.posY == snake.head.posY){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void addNewBlock(){
        Block b = new Block(snake.tail.oldPosX,snake.tail.oldPosY,snake.tail,this);
        snake.tail = b;
        addBlock(b);
    }
    
    public void addSnake(Snake snake){
        this.snake = snake;
        for (Block b : snake.blocks){
            addBlock(b);
        }
    }
    
    private void addBlock(Block b){
        getChildren().add(b);
        blocks.add(b);
    }
    
    private void addFood(){
        int randomX = (int)(Math.random() * width);
        int randomY = (int)(Math.random() * height);  
        
        Food food = new Food(randomX, randomY);
        getChildren().remove(f);
        getChildren().add(food);
        f = food;
    }
    
    public boolean isEaten(Food f){
        if(f == null){
            return false;
        }
        return f.getPosX() == snake.head.posX && f.getPosY() == snake.head.posY;
    }
            
    public int getW() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getH() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
