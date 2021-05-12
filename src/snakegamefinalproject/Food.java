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
public class Food extends Rectangle{

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    private int posX;
    private int posY;
    
    public Food(int x, int y){
        super(SnakeGameFinalProject.block_size,SnakeGameFinalProject.block_size);
        posX = x;
        posY = y;
        setTranslateX(posX*SnakeGameFinalProject.block_size);
        setTranslateY(posY*SnakeGameFinalProject.block_size);
        setFill(Color.YELLOW);
    }
}
