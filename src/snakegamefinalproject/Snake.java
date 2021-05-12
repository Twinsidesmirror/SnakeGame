/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegamefinalproject;

import java.util.ArrayList;

/**
 *
 * @author nattakarnpl
 */
public class Snake {
    ArrayList<Block> blocks = new ArrayList<Block>();
    Block head;
    Block tail;
    public Snake(int il, Field f){
        int ipx = f.getW()/2;
        int ipy = f.getH()/2;
        
        head = new Block(ipx,ipy,null,f);
        blocks.add(head);
        
        tail = head;
        
        for(int i = 1; i<il; i++){
            Block b = new Block(ipx + i, ipy,tail,f);
            blocks.add(b);
//            previous = b;
            tail = b;
        }
    }    
    
    public int getDirection(){
        return head.direction;
    }
    public void setDirection(int d){
        head.direction = d;
    }
}
