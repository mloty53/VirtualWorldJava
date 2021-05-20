/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java.animals;

import proj.java.*;

import java.awt.*;

public class Sheep extends Animal {
    public Sheep(){}
    public Sheep(World _world, Coords _pos, int _age, int _power, int _init){
        super(_world,_pos,_age,_power,_init);
    }
    public Sheep(World _world, Coords _pos){
        super(_world, _pos);
        power = 4;
        initiative = 4;
    }
    @Override
    public char GetSign(){
        return 'S';
    }
    
    @Override
    public String GetName(){
        return "Sheep";
    }

    @Override
    public Color Color() {
        return Color.LIGHT_GRAY;
    }
    protected void Split(){
        Direction dir = RandDir();

        if (dir == Direction.UP && position.y > 0 ||
                dir == Direction.DOWN && position.y < (world.GetHeight() - 1) ||
                dir == Direction.RIGHT && position.x < (world.GetWidth() - 1) ||
                dir == Direction.LEFT && position.x > 0) {
            if (Next(dir, 1) instanceof Ground) {
                switch (dir) {
                    case UP:
                        world.toAdd(new Sheep(world, new Coords(position.x,position.y-1)));
                        break;
                    case DOWN:
                        world.toAdd(new Sheep(world, new Coords(position.x,position.y+1)));
                        break;
                    case RIGHT:
                        world.toAdd(new Sheep(world, new Coords(position.x+1,position.y)));
                        break;
                    case LEFT:
                        world.toAdd(new Sheep(world, new Coords(position.x-1,position.y)));
                        break;
                }
            }
        }
    }

}