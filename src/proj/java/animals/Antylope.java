/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java.animals;

import proj.java.*;

import java.awt.*;
import java.util.Random;

public class Antylope extends Animal {
    public Antylope(){}
    public Antylope(World _world, Coords _pos, int _age, int _power, int _init){
        super(_world,_pos,_age,_power,_init);
    }
   public Antylope(World _world, Coords _pos){
        super(_world, _pos);
        power = 4;
        initiative = 4;
    }
    @Override
    public char GetSign(){
        return 'A';
    }
    
    @Override
    public String GetName(){
        return "Antylope";
    }

    @Override
    public Color Color() {
        return new Color(254,232,129);
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
                        world.toAdd(new Antylope(world, new Coords(position.x,position.y-1)));
                        break;
                    case DOWN:
                        world.toAdd(new Antylope(world, new Coords(position.x,position.y+1)));
                        break;
                    case RIGHT:
                        world.toAdd(new Antylope(world, new Coords(position.x+1,position.y)));
                        break;
                    case LEFT:
                        world.toAdd(new Antylope(world, new Coords(position.x-1,position.y)));
                        break;
                }
            }
        }
    }
    @Override
    public void Action(){
        Direction dir= NewDir();
        if (dir == Direction.UP && position.y > 1 ||
                dir == Direction.DOWN && position.y < world.GetHeight() - 2 ||
                dir == Direction.RIGHT && position.x < world.GetWidth() - 2 ||
                dir == Direction.LEFT && position.x > 1) {

            if (Next(dir, 1)instanceof Ground) {
                if (Next(dir, 2) instanceof Ground) {
                    Move(dir);
                    Move(dir);
                }
			else {
                    Move(dir);
                    Next(dir, 1).Collision(this);
                    if (Next(dir, 1) instanceof Ground) Move(dir);
                }
            }
		else {
                Next(dir, 1).Collision(this);
                if (Next(dir, 1) instanceof Ground) Move(dir);
            }
        }
    }
    @Override
    protected Direction NewDir(){
        Random gen = new Random();
        int temp;
        Direction dir = null;
        //left side
        if (position.x == 0 || position.x == 1) {
            //top left corner
            if (position.y == 0) {
                temp = gen.nextInt(2);
                dir = temp == 0 ? Direction.DOWN : Direction.RIGHT;
            }
            // bottom left corner
            else if (position.y == world.GetHeight() - 1) {
                temp = gen.nextInt(2);
                dir = temp == 0 ? Direction.UP : Direction.RIGHT;
            }
            //elsewhere on left side
            else {
                temp = gen.nextInt(3);
                switch (temp) {
                    case 0: dir = Direction.UP; break;
                    case 1: dir = Direction.DOWN; break;
                    case 2: dir = Direction.RIGHT; break;
                }
            }
        }
        //right side
        else if (position.x == world.GetWidth() - 1 || position.x == world.GetWidth()-2) {
            //top right
            if (position.y == 0) {
                temp = gen.nextInt(2);
                dir = temp == 0 ? Direction.DOWN : Direction.LEFT;
            }
            //bot right
            else if (position.y == world.GetHeight() - 1) {
                temp = gen.nextInt(2);
                dir = temp == 0 ? Direction.UP : Direction.LEFT;
            }
            //elsewhere right
            else
            {
                temp = gen.nextInt(3);
                switch (temp) {
                    case 0: dir = Direction.UP; break;
                    case 1: dir = Direction.DOWN; break;
                    case 2: dir = Direction.LEFT; break;
                }
            }
        }
        //top side without corners
        else if (position.y == 0 || position.y==1) {
            temp = gen.nextInt(3);
            switch (temp) {
                case 0: dir = Direction.RIGHT; break;
                case 1: dir = Direction.DOWN; break;
                case 2: dir = Direction.LEFT; break;
            }
        }
        //bot side without corners
        else if (position.y == world.GetHeight() - 1 || position.y == world.GetHeight()-2) {
            temp = gen.nextInt(2);
            switch (temp) {
                case 0: dir = Direction.UP; break;
                case 1: dir = Direction.RIGHT; break;
                case 2: dir = Direction.LEFT; break;
            }
        }
        //middle
        else {
            temp = gen.nextInt(4);
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
                case 4:
                    dir = Direction.RIGHT;
                    break;
            }
        }
        return dir;
    }
    @Override
    public void Collision(Organism collider){
        Random gen = new Random();
        int temp = gen.nextInt(2);
        if(collider instanceof Antylope){
            Split();
        }
        else if (temp == 0){
            world.Log("Antylope tries to run away!");
            if(position.x > 0 && Next(Direction.LEFT,1) instanceof Ground) Move(Direction.LEFT);
            else if (position.x < world.GetWidth()-1 && Next(Direction.RIGHT, 1) instanceof Ground) Move(Direction.RIGHT);
            else if (position.y > 0 && Next(Direction.UP,1) instanceof Ground) Move(Direction.UP);
            else if (position.y < world.GetHeight() -1 && Next(Direction.DOWN,1) instanceof Ground) Move(Direction.DOWN);
        }
        else if(collider.power > this.power){
            world.Log(collider.GetName() + " kills " + this.GetName());
            world.board[position.y][position.x] = new Ground();
        }
        else {
            world.Log(this.GetName() + " kills " + collider.GetName());
            world.board[collider.position.y][collider.position.x] = new Ground();
        }
    }
}