/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java;

import java.awt.*;

public class Ground extends Organism {
    public Ground(){}
    public Ground(World _world, Coords _pos){
        world=_world;
        position=_pos;
    }
    @Override
    public Color Color(){
        return new Color(210,180,140);
    }

    @Override
    public char GetSign() {
        return ' ';
    }
    
     @Override
    public String GetName() {
        return "Ground";
    }
}