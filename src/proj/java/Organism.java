/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public abstract class Organism {
    public int age;
    public int power;
    public int initiative;
    public World world;
    public Coords position;
    public Organism(){}
    public Organism(World _world,Coords _coords){
        world = _world;
        position = _coords;
        age=0;
    }
    public Organism(World _world, Coords _coords,int _age, int _init, int _power){
        world = _world;
        position = _coords;
        power = _power;
        age = _age;
        initiative = _init;
    }
    void Action(){}
    public void Collision(Organism collider){
        world.Log(this.GetSign() + " collides with " + collider.GetSign());
        if(collider.power > this.power){
            world.Log(collider.GetSign() + " kills " + this.GetSign());
            world.board[position.y][position.x] = new Ground();
        }
        else {
            world.Log(this.GetSign() + " kills " + collider.GetSign());
        }
    }
    abstract public Color Color();
    abstract public char GetSign();
    abstract public String GetName();
    protected Direction RandDir(){
        Direction dir = null;
        Random randGen = new Random();
        int temp = randGen.nextInt(4);
        switch(temp){
            case 0:
                dir = Direction.UP;
                break;
            case 1:
                dir = Direction.DOWN;
                break;
            case 2:
                dir = Direction.LEFT;
                break;
            case 3:
                dir = Direction.RIGHT;
                break;
        }
        return dir;
    }
    protected Organism Next(Direction dir, int distance){
        switch (dir) {
            case UP:
                return world.board[position.y - distance][position.x];
            case DOWN:
                return world.board[position.y + distance][position.x];
            case RIGHT:
                return world.board[position.y][position.x + distance];
            case LEFT:
                return world.board[position.y][position.x - distance];

        }
        return this;
    }
}