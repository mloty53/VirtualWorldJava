/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java.animals;

import proj.java.plants.*;

import proj.java.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Human extends Animal {
    private int id;
    private int turnsLeft = 0;
    public Human(){}
    public Human(World _world, Coords _pos, int _age, int _power, int _init, int _id, int _turnsLeft){
        super(_world,_pos,_age,_power,_init);
        id = _id;
        turnsLeft = _turnsLeft;
    }
    public Human(World _world, Coords _pos){
        super(_world, _pos);
        power = 5;
        initiative = 4;
    }
    @Override
    public char GetSign(){return 'H';}
    @Override
    public String GetName(){
        return "Human";
    }
    @Override
    public Color Color(){return Color.WHITE
            ;}
    public int GetId(){return id;}
    public int TurnsLeft(){return turnsLeft;}
    public void Action(Direction dir){
        if (dir == Direction.UP && position.y > 0 ||
        dir == Direction.DOWN && position.y < world.GetHeight() - 1 ||
        dir == Direction.LEFT && position.x > 0 ||
        dir == Direction.RIGHT && position.x < world.GetWidth() - 1){
            if(Next(dir,1) instanceof Ground) Move(dir);
            else{
                if(turnsLeft>0){
                    if(Next(dir,1).power>this.power) Immortality();
                    else Next(dir,1).Collision(this);
                }
                else Next(dir,1).Collision(this);
                if(Next(dir,1) instanceof Ground) Move(dir);
            }
        }
        if(turnsLeft>0){
            turnsLeft--;
        }
    }
    public void UseAbility(){
        if(turnsLeft==0) turnsLeft = 5;
    }
    public void Immortality(){
        world.Log("Human is immortal!");
        if (position.x == 0) {
		if (position.y == 0) {
			if (world.board[position.y + 1][position.x ] instanceof Ground)Move(Direction.DOWN) ;
                        else if (world.board[position.y][position.x +1] instanceof Ground )Move(Direction.RIGHT);
		}
		else if (position.y == world.GetHeight() - 1) {
			if (world.board[position.y - 1][position.x ] instanceof Ground ) Move(Direction.UP);
                        else if (world.board[position.y ][position.x +1] instanceof Ground ) Move(Direction.RIGHT);
		}
		else {
			if (world.board[position.y - 1][position.x ] instanceof Ground ) Move(Direction.UP);
                        else if (world.board[position.y + 1][position.x ] instanceof Ground )Move(Direction.DOWN);
                        else if (world.board[position.y][position.x +1] instanceof Ground ) Move(Direction.RIGHT);
		}
	}
	else if (position.x == world.GetWidth() - 1) {
		if (position.y == 0) {
			if (world.board[position.y + 1][position.x ] instanceof Ground ) Move(Direction.DOWN);
                        else if (world.board[position.y ][position.x -1] instanceof Ground ) Move(Direction.LEFT);
		}
		else if (position.y == world.GetHeight() - 1) {
			if (world.board[position.y - 1][position.x ] instanceof Ground ) Move(Direction.UP);
                        else if (world.board[position.y ][position.x -1] instanceof Ground ) Move(Direction.LEFT);
		}
		else {
			if (world.board[position.y - 1][position.x ] instanceof Ground ) Move(Direction.UP);
                        else if (world.board[position.y + 1][position.x ] instanceof Ground ) Move(Direction.DOWN);
                        else if (world.board[position.y ][position.x -1] instanceof Ground ) Move(Direction.LEFT);
		}
	}
	else if (position.y == 0) {
		if (world.board[position.y + 1][position.x ] instanceof Ground ) Move(Direction.DOWN);
                else if (world.board[position.y][position.x -1] instanceof Ground ) Move(Direction.LEFT);
                else if (world.board[position.y ][position.x +1] instanceof Ground ) Move(Direction.RIGHT);
	}
	else if (position.y == world.GetHeight() - 1) {
		if (world.board[position.y - 1][position.x ] instanceof Ground ) Move(Direction.UP);
                else if (world.board[position.y][position.x -1]instanceof Ground ) Move(Direction.LEFT);
                else if (world.board[position.y][position.x +1]instanceof Ground ) Move(Direction.RIGHT);
	}
	else {
		if (world.board[position.y - 1][position.x ]instanceof Ground ) Move(Direction.UP);
                else if (world.board[position.y + 1][position.x ]instanceof Ground ) Move(Direction.DOWN);
                else if (world.board[position.y ][position.x -1]instanceof Ground ) Move(Direction.LEFT);
                else if (world.board[position.y][position.x +1]instanceof Ground ) Move(Direction.RIGHT);
	}
        
    }
    
}