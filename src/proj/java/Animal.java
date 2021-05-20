/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Animal extends Organism{
    public Animal(){}
    public Animal(World _world, Coords _pos, int _age, int _power, int _init){
        super(_world,_pos,_age,_power,_init);
    }
    public Animal(World _world, Coords _pos){
        super(_world, _pos);
    }
    @Override
    public void Action(){
        Direction dir = NewDir();
        if(dir == Direction.UP && position.y >0 ||
            dir == Direction.DOWN && position.y < world.GetHeight()-1 ||
            dir == Direction.LEFT && position.x > 0 ||
            dir == Direction.RIGHT && position.x < world.GetWidth()-1){
            if(Next(dir,1) instanceof Ground){
                Move(dir);
            }
            else{
                Next(dir,1).Collision(this);
                if(Next(dir,1) instanceof Ground) Move(dir);
            }
        }
    }
    protected Direction NewDir() {
        Direction dir = null;
        Random randGen = new Random();
        int temp;
        //left side
        if (position.x == 0) {
            //top left corner
            if (position.y == 0) {
                temp = randGen.nextInt(2);
                dir = temp == 0 ? Direction.DOWN : Direction.RIGHT;

            }
            //bottom left corner
            else if (position.y == world.GetHeight() - 1) {
                temp = randGen.nextInt(2);
                dir = temp == 0 ? Direction.UP : Direction.RIGHT;
            }
            //elsewhere on left side
            else {
                temp = randGen.nextInt(3);
                switch (temp) {
                    case 0:
                        dir = Direction.UP;
                        break;
                    case 1:
                        dir = Direction.DOWN;
                        break;
                    case 2:
                        dir = Direction.RIGHT;
                        break;
                }
            }
        }
        //right side
        else if (position.x == world.GetWidth() - 1) {
            //top right
            if (position.y == 0) {
                temp = randGen.nextInt(2);
                dir = temp == 0 ? Direction.DOWN : Direction.LEFT;
            }
            //bot right
            else if (position.y == world.GetHeight() - 1) {
                temp = randGen.nextInt(2);
                dir = temp == 0 ? Direction.UP : Direction.LEFT;
            }
            //elsewhere right
            else {
                temp = randGen.nextInt(3);
                switch (temp) {
                    case 0:
                        dir = Direction.UP;
                        break;
                    case 1:
                        dir = Direction.DOWN;
                        break;
                    case 2:
                        dir = Direction.LEFT;
                        break;
                }
            }
        }
        //top side without corners
        else if (position.y == 0) {
            temp = randGen.nextInt(3);
            switch (temp) {
                case 0:
                    dir = Direction.RIGHT;
                    break;
                case 1:
                    dir = Direction.DOWN;
                    break;
                case 2:
                    dir = Direction.LEFT;
                    break;
            }
        }
        //bot side without corners
        else if (position.y == world.GetHeight() - 1) {
            temp = randGen.nextInt(3);
            switch (temp) {
                case 0:
                    dir = Direction.UP;
                    break;
                case 1:
                    dir = Direction.RIGHT;
                    break;
                case 2:
                    dir = Direction.LEFT;
                    break;
            }
        }
        //middle
        else {
            dir=RandDir();
        }
        return dir;
    }
    protected void Move(Direction dir){
        switch (dir){
            case UP:
                if(position.y == 0) break;
                world.board[position.y-1][position.x] = world.board[position.y][position.x];
                world.board[position.y][position.x] = new Ground();
                position.y--;
                break;
            case DOWN:
                if(position.y == world.GetHeight()-1) break;
                world.board[position.y+1][position.x] = world.board[position.y][position.x];
                world.board[position.y][position.x] = new Ground();
                position.y++;
                break;
            case LEFT:
                if(position.x == 0) break;
                world.board[position.y][position.x-1] = world.board[position.y][position.x];
                world.board[position.y][position.x] = new Ground();
                position.x--;
                break;
            case RIGHT:
                if(position.x == world.GetWidth()-1) break;
                world.board[position.y][position.x+1] = world.board[position.y][position.x];
                world.board[position.y][position.x] = new Ground();
                position.x++;
                break;
        }
    }

    @Override
    public char GetSign(){
        return 'a';
    }
        @Override
    public String GetName(){
        return "Animal";
    }
    @Override
    public Color Color(){
       return Color.RED;
    }
    public void Collision(Organism collider) {
        //world.Log(this.GetSign() + " collides with " + collider.GetSign());
        if(collider.GetSign() == this.GetSign()){
            //System.out.println("spliting!");
            Split();
        }
        else if (collider.power > this.power){
            world.Log(collider.GetName() + " kills " + this.GetName());
            world.board[position.y][position.x]=new Ground();
        }
        else{
            world.Log(this.GetName() + " kills " + collider.GetName());
            world.board[collider.position.y][collider.position.x]=new Ground();
        }
    }
    protected void Split(){
    }

}