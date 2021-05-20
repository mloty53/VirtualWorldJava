/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java.animals;

import proj.java.*;

import java.awt.*;
import java.util.Random;

public class Turtle extends Animal {
    public Turtle(){}
    public Turtle(World _world, Coords _pos, int _age, int _power, int _init){
        super(_world,_pos,_age,_power,_init);
    }
    public Turtle(World _world, Coords _pos){
        super(_world, _pos);
        power = 2;
        initiative = 1;
    }
    @Override
    public char GetSign(){
        return 'T';
    }
    
    @Override
    public String GetName(){
        return "Turtle";
    }

    @Override
    public Color Color() {
        return new Color(0, 51, 0);
    }
    @Override
    protected void Split(){
        Direction dir = RandDir();

        if (dir == Direction.UP && position.y > 0 ||
                dir == Direction.DOWN && position.y < (world.GetHeight() - 1) ||
                dir == Direction.RIGHT && position.x < (world.GetWidth() - 1) ||
                dir == Direction.LEFT && position.x > 0) {
            if (Next(dir, 1) instanceof Ground) {
                switch (dir) {
                    case UP:
                        world.toAdd(new Turtle(world, new Coords(position.x,position.y-1)));
                        break;
                    case DOWN:
                        world.toAdd(new Turtle(world, new Coords(position.x,position.y+1)));
                        break;
                    case RIGHT:
                        world.toAdd(new Turtle(world, new Coords(position.x+1,position.y)));
                        break;
                    case LEFT:
                        world.toAdd(new Turtle(world, new Coords(position.x-1,position.y)));
                        break;
                }
            }
        }
    }
    @Override
    public void Action(){
        Random randGen = new Random();
        int temp = randGen.nextInt(4);
        if(temp==0){
            super.Action();
        }
    }
    @Override
    public void Collision(Organism collider){
        if(collider instanceof Turtle){
            Split();
        }
        else if (collider.power < 5){
            world.Log("Turtle blocks " + collider.GetName());
            return;
        }
        else if( collider.power > this.power){
            world.Log(collider.GetName() + " kills " + this.GetName());
            world.board[position.y][position.x] = new Ground();
        }
        else {
            world.Log(this.GetName() + " kills " + collider.GetName());
            world.board[collider.position.y][collider.position.x] = new Ground();
        }
    }
}