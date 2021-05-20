/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.List;
public class Grass extends Organism {
    public Grass(){}
    public Grass(World _world, Coords _pos, int _age, int _power, int _init){
        super(_world,_pos,_age,_power,_init);
    }
    public Grass(World _world, Coords _pos){
        super(_world, _pos);
        power = 0;
        initiative = 0;
    }
    @Override
    public char GetSign(){
        return 'g';
    }
    @Override
    public String GetName(){
        return "Grass";
    }

    @Override
    public Color Color() {
        return Color.GREEN;
    }
    @Override
    public void Action(){
        Random gen = new Random();
        int temp = gen.nextInt(10);
        if(temp==0){
            List<Direction> chances = new LinkedList<>();
            if (position.x == 0) {
                if (position.y == 0) {
                    if (world.board[position.y+1][position.x] instanceof Ground) chances.add(Direction.DOWN);
                    if (world.board[position.y][position.x+1] instanceof Ground) chances.add(Direction.RIGHT);
                }
                else if (position.y == world.GetHeight() - 1) {
                    if (world.board[position.y-1][position.x] instanceof Ground) chances.add(Direction.UP);
                    if (world.board[position.y][position.x+1] instanceof Ground) chances.add(Direction.RIGHT);
                }
                else {
                    if (world.board[position.y-1][position.x] instanceof Ground) chances.add(Direction.UP);
                    if (world.board[position.y+1][position.x] instanceof Ground) chances.add(Direction.DOWN);
                    if (world.board[position.y][position.x+1] instanceof Ground) chances.add(Direction.RIGHT);
                }
            }
            else if (position.x == world.GetWidth() - 1) {
                if (position.y == 0) {
                    if (world.board[position.y+1][position.x] instanceof Ground) chances.add(Direction.DOWN);
                    if (world.board[position.y][position.x-1] instanceof Ground) chances.add(Direction.LEFT);
                }
                else if (position.y == world.GetHeight() - 1) {
                    if (world.board[position.y-1][position.x] instanceof Ground) chances.add(Direction.UP);
                    if (world.board[position.y][position.x-1] instanceof Ground) chances.add(Direction.LEFT);
                }
                else {
                    if (world.board[position.y-1][position.x] instanceof Ground) chances.add(Direction.UP);
                    if (world.board[position.y+1][position.x] instanceof Ground) chances.add(Direction.DOWN);
                    if (world.board[position.y][position.x-1] instanceof Ground) chances.add(Direction.LEFT);
                }
            }
            else if (position.y == 0) {
                if (world.board[position.y+1][position.x] instanceof Ground) chances.add(Direction.DOWN);
                if (world.board[position.y][position.x-1] instanceof Ground) chances.add(Direction.LEFT);
                if (world.board[position.y][position.x+1] instanceof Ground) chances.add(Direction.RIGHT);
            }
            else if (position.y == world.GetHeight() - 1) {
                if (world.board[position.y-1][position.x] instanceof Ground) chances.add(Direction.UP);
                if (world.board[position.y][position.x-1] instanceof Ground) chances.add(Direction.LEFT);
                if (world.board[position.y][position.x+1] instanceof Ground) chances.add(Direction.RIGHT);
            }
            else {
                if (world.board[position.y-1][position.x] instanceof Ground) chances.add(Direction.UP);
                if (world.board[position.y+1][position.x] instanceof Ground) chances.add(Direction.DOWN);
                if (world.board[position.y][position.x-1] instanceof Ground) chances.add(Direction.LEFT);
                if (world.board[position.y][position.x+1] instanceof Ground) chances.add(Direction.RIGHT);
            }
            Divide(chances);
        }
    }
    protected void Divide(List<Direction> chances){
        Random gen = new Random();

        if (chances.size() == 0) return;
        else {
            int temp = gen.nextInt(chances.size());
            for (int i = 0; i < temp; i++) chances.remove(0);
            switch (chances.get(0)) {
                case UP:
                    world.toAdd(new Grass(world, new Coords(position.x, position.y - 1)));
                    break;
                case DOWN:
                    world.toAdd(new Grass(world, new Coords(position.x, position.y + 1)));
                    break;
                case RIGHT:
                    world.toAdd(new Grass(world, new Coords(position.x + 1, position.y)));
                    break;
                case LEFT:
                    world.toAdd(new Grass(world, new Coords(position.x - 1, position.y)));
                    break;
            }
        }
    }
    @Override
    public void Collision(Organism collider){
        world.Log(collider.GetName() + " eats " + this.GetName());
        world.board[position.y][position.x] = new Ground();
    }
}