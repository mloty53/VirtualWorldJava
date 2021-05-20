/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java.plants;

import proj.java.*;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Guarana extends Grass {
    public Guarana(){}
    public Guarana(World _world, Coords _pos, int _age, int _power, int _init){
        super(_world,_pos,_age,_power,_init);
    }
    public Guarana(World _world, Coords _pos){
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
        return "Guarana";
    }

    @Override
    public Color Color() {
        return new Color(237, 26, 38);
    }
    @Override
    protected void Divide(List<Direction>chances){
        Random gen = new Random();

        if (chances.size() == 0) return;
        else {
            int temp = gen.nextInt(chances.size());
            for (int i = 0; i < temp; i++) chances.remove(0);
            switch (chances.get(0)) {
                case UP: world.toAdd(new Guarana(world, new Coords(position.x,position.y-1))); 	break;
                case DOWN: world.toAdd(new Guarana(world, new Coords(position.x,position.y+1)));  break;
                case RIGHT: world.toAdd(new Guarana(world, new Coords(position.x+1,position.y)));  break;
                case LEFT: world.toAdd(new Guarana(world, new Coords(position.x-1,position.y)));  break;
            }
        }
    }
    @Override
    public void Collision(Organism collider){
        collider.power+=3;
        super.Collision(collider);
        world.Log("The power of " + collider.GetName() + " rises by 3. Now it is at " + collider.power);
    }
}