/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java.animals;

import proj.java.*;

import java.awt.*;

public class Fox extends Animal {
    public Fox(){}
    public Fox(World _world, Coords _pos, int _age, int _power, int _init){
        super(_world,_pos,_age,_power,_init);
    }
    public Fox(World _world, Coords _pos){
        super(_world, _pos);
        power = 3;
        initiative = 7;
    }
    @Override
    public char GetSign(){
        return 'F';
    }
    
    @Override
    public String GetName(){
        return "Fox";
    }

    @Override
    public Color Color() {
        return new Color(244,92,11);
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
                        world.toAdd(new Fox(world, new Coords(position.x,position.y-1)));
                        break;
                    case DOWN:
                        world.toAdd(new Fox(world, new Coords(position.x,position.y+1)));
                        break;
                    case RIGHT:
                        world.toAdd(new Fox(world, new Coords(position.x+1,position.y)));
                        break;
                    case LEFT:
                        world.toAdd(new Fox(world, new Coords(position.x-1,position.y)));
                        break;
                }
            }
        }
    }
    @Override
    public void Action(){
        Direction dir=  NewDir();

        if (Next(dir, 1) instanceof Ground) {
            Move(dir);
        }
        else {
            if (Next(dir,1).power < this.power) {
                Next(dir,1).Collision(this);
                if (Next(dir, 1) instanceof Ground) Move(dir);
            }

        }
    }
}