/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegamefinalproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author nattakarnpl
 */
public class Block extends Rectangle{
    int posX, posY, oldPosX, oldPosY;
    static final int UP = 0, RIGHT =1, DOWN = 2, LEFT = 3;
    
    Block previous;
    
    int direction = LEFT;
    
    int maxX, maxY;
    
    public Block(int x, int y, Block p, Field f){
        super(SnakeGameFinalProject.block_size,SnakeGameFinalProject.block_size);
        posX = x;
        posY = y;
        setTranslateX(posX*SnakeGameFinalProject.block_size);
        setTranslateY(posY*SnakeGameFinalProject.block_size);
        previous = p;
        maxX = f.getW();
        maxY = f.getH();
        setFill(Color.WHITE);
    }
    
    public void update(){
//        System.out.println(previous);
        oldPosX = posX;
        oldPosY = posY;
        if(previous == null){
            switch(direction){
                case UP: 
                    moveUp();
                    break;
                case RIGHT: 
                    moveRight();
                    break;
                case DOWN: 
                    moveDown();
                    break;
                case LEFT: 
                    moveLeft();
                    break;
            }
        }else{
            posX = previous.oldPosX;
            posY = previous.oldPosY;
        }
        updatePosition();
    }
    public void moveUp(){
        posY--;
        if (posY < 0){
            posY = maxY - 1;
        }
    }
    public void moveRight(){
        posX++;
        if (posX >= maxX){
            posX = 0;
        }
    }
    public void moveDown(){
        posY++;
        if (posY >= maxY){
            posY = 0;
        }
    }
    public void moveLeft(){
        posX--;
        if (posX < 0 ){
            posX = maxX - 1;
        }
    }
    public void updatePosition(){
//        System.out.println(posX+" / "+posY);
        setTranslateX(posX*SnakeGameFinalProject.block_size);
        setTranslateY(posY*SnakeGameFinalProject.block_size);
    }
}
