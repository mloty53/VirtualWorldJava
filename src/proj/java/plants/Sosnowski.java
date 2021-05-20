/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java.plants;

import proj.java.*;
import proj.java.animals.Human;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Sosnowski extends Grass {
    public Sosnowski(){}
    public Sosnowski(World _world, Coords _pos, int _age, int _power, int _init){
        super(_world,_pos,_age,_power,_init);
    }
    public Sosnowski(World _world, Coords _pos){
        super(_world, _pos);
        power = 10;
        initiative = 0;
    }
    @Override
    public char GetSign(){
        return 's';
    }
    @Override
    public String GetName(){
        return "Sosnowski";
    }
    @Override
    public void Action(){
        super.Action();
        Burn();
    }
    @Override
    public Color Color() {
        return new Color(53,185,86);
    }
    @Override
    protected void Divide(List<Direction> chances){
        Random gen = new Random();

        if (chances.size() == 0) return;
        else {
            int temp = gen.nextInt(chances.size());
            for (int i = 0; i < temp; i++) chances.remove(0);
            switch (chances.get(0)) {
                case UP: world.toAdd(new Sosnowski(world, new Coords(position.x,position.y-1))); 	break;
                case DOWN: world.toAdd(new Sosnowski(world, new Coords(position.x,position.y+1)));  break;
                case RIGHT: world.toAdd(new Sosnowski(world, new Coords(position.x+1,position.y)));  break;
                case LEFT: world.toAdd(new Sosnowski(world, new Coords(position.x-1,position.y)));  break;
            }
        }
    }
    @Override
    public void Collision(Organism collider){
            world.board[position.y][position.x] = new Ground();
    }
    public void Burn(){
        if (position.x == 0) {
            if (position.y == 0) {//top left
                if(world.board[position.y + 1][position.x] instanceof Animal){
                    if(world.board[position.y + 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y + 1][position.x]).TurnsLeft()==0){
                            world.board[position.y + 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y + 1][position.x] = new Ground();
                    }
                }
                if(world.board[position.y + 1][position.x+1] instanceof Animal){
                    if(world.board[position.y + 1][position.x+1] instanceof Human){
                        if(((Human)world.board[position.y + 1][position.x+1]).TurnsLeft()==0){
                            world.board[position.y + 1][position.x+1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y + 1][position.x+1] = new Ground();
                    }
                }
                if(world.board[position.y ][position.x+ 1] instanceof Animal){
                    if(world.board[position.y ][position.x+ 1] instanceof Human){
                        if(((Human)world.board[position.y ][position.x+ 1]).TurnsLeft()==0){
                            world.board[position.y ][position.x+ 1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y ][position.x+ 1] = new Ground();
                    }
                }
            }
            else if (position.y == world.GetHeight() - 1) {//bot left
                if(world.board[position.y - 1][position.x] instanceof Animal){
                    if(world.board[position.y - 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y - 1][position.x]).TurnsLeft()==0){
                            world.board[position.y - 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y - 1][position.x] = new Ground();
                    }
                }
                if(world.board[position.y - 1][position.x+1] instanceof Animal){
                    if(world.board[position.y - 1][position.x+1] instanceof Human){
                        if(((Human)world.board[position.y - 1][position.x+1]).TurnsLeft()==0){
                            world.board[position.y - 1][position.x+1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y - 1][position.x+1]= new Ground();
                    }
                }
                if(world.board[position.y ][position.x+ 1] instanceof Animal){
                    if(world.board[position.y ][position.x+ 1] instanceof Human){
                        if(((Human)world.board[position.y ][position.x+ 1]).TurnsLeft()==0){
                            world.board[position.y ][position.x+ 1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y ][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y ][position.x+ 1] = new Ground();
                    }
                }
            }
            else {//left edge
                if(world.board[position.y - 1][position.x] instanceof Animal){
                    if(world.board[position.y - 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y - 1][position.x]).TurnsLeft()==0){
                            world.board[position.y - 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y - 1][position.x] = new Ground();
                    }
                }
                if(world.board[position.y - 1][position.x+1] instanceof Animal){
                    if(world.board[position.y - 1][position.x+1] instanceof Human){
                        if(((Human)world.board[position.y - 1][position.x+1]).TurnsLeft()==0){
                            world.board[position.y - 1][position.x+1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y - 1][position.x+1] = new Ground();
                    }
                }
                if(world.board[position.y ][position.x+ 1] instanceof Animal){
                    if(world.board[position.y ][position.x+ 1]  instanceof Human){
                        if(((Human)world.board[position.y ][position.x+ 1] ).TurnsLeft()==0){
                            world.board[position.y ][position.x+ 1]  = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y ][position.x+ 1]  = new Ground();
                    }
                }
                if(world.board[position.y + 1][position.x] instanceof Animal){
                    if(world.board[position.y + 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y + 1][position.x]).TurnsLeft()==0){
                            world.board[position.y + 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y + 1][position.x] = new Ground();
                    }
                }
                if(world.board[position.y + 1][position.x+1] instanceof Animal){
                    if(world.board[position.y + 1][position.x+1] instanceof Human){
                        if(((Human)world.board[position.y + 1][position.x+1]).TurnsLeft()==0){
                            world.board[position.y + 1][position.x+1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y + 1][position.x+1] = new Ground();
                    }
                }

            }
        }
        else if (position.x == world.GetWidth() - 1) {
            if (position.y == 0) {//top right
                if(world.board[position.y + 1][position.x] instanceof Animal){
                    if(world.board[position.y + 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y + 1][position.x]).TurnsLeft()==0){
                            world.board[position.y + 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y + 1][position.x] = new Ground();
                    }
                }
                if(world.board[position.y + 1][position.x-1] instanceof Animal){
                    if(world.board[position.y + 1][position.x-1] instanceof Human){
                        if(((Human)world.board[position.y + 1][position.x-1]).TurnsLeft()==0){
                            world.board[position.y + 1][position.x-1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y + 1][position.x-1] = new Ground();
                    }
                }
                if(world.board[position.y ][position.x- 1] instanceof Animal){
                    if(world.board[position.y ][position.x- 1]instanceof Human){
                        if(((Human)world.board[position.y ][position.x- 1]).TurnsLeft()==0){
                            world.board[position.y ][position.x- 1]= new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y ][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y ][position.x- 1] = new Ground();
                    }
                }
            }
            else if (position.y == world.GetHeight() - 1) {//bot right
                if(world.board[position.y - 1][position.x] instanceof Animal){
                    if(world.board[position.y - 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y - 1][position.x]).TurnsLeft()==0){
                            world.board[position.y - 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y - 1][position.x]= new Ground();
                    }
                }
                if(world.board[position.y - 1][position.x-1] instanceof Animal){
                    if(world.board[position.y - 1][position.x-1] instanceof Human){
                        if(((Human)world.board[position.y - 1][position.x-1]).TurnsLeft()==0){
                            world.board[position.y - 1][position.x-1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y - 1][position.x-1] = new Ground();
                    }
                }
                if(world.board[position.y ][position.x- 1] instanceof Animal){
                    if(world.board[position.y ][position.x- 1]instanceof Human){
                        if(((Human)world.board[position.y ][position.x- 1]).TurnsLeft()==0){
                            world.board[position.y ][position.x- 1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y ][position.x- 1] = new Ground();
                    }
                }
            }
            else {//right edge
                if(world.board[position.y - 1][position.x] instanceof Animal){
                    if(world.board[position.y - 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y - 1][position.x]).TurnsLeft()==0){
                           world.board[position.y - 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y - 1][position.x] = new Ground();
                    }
                }
                if(world.board[position.y -1][position.x-1] instanceof Animal){
                    if(world.board[position.y -1][position.x-1] instanceof Human){
                        if(((Human)world.board[position.y -1][position.x-1]).TurnsLeft()==0){
                            world.board[position.y -1][position.x-1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y -1][position.x-1] = new Ground();
                    }
                }
                if(world.board[position.y ][position.x- 1] instanceof Animal){
                    if(world.board[position.y ][position.x- 1] instanceof Human){
                        if(((Human)world.board[position.y ][position.x- 1]).TurnsLeft()==0){
                            world.board[position.y ][position.x- 1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y ][position.x- 1]= new Ground();
                    }
                }
                if(world.board[position.y + 1][position.x] instanceof Animal){
                    if(world.board[position.y + 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y + 1][position.x]).TurnsLeft()==0){
                            world.board[position.y + 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y + 1][position.x] = new Ground();
                    }
                }
                if(world.board[position.y + 1][position.x-1] instanceof Animal){
                    if(world.board[position.y + 1][position.x-1] instanceof Human){
                        if(((Human)world.board[position.y + 1][position.x-1]).TurnsLeft()==0){
                            world.board[position.y + 1][position.x-1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y + 1][position.x-1] = new Ground();
                    }
                }

            }
        }
        else if (position.y == 0) {//top without corners
            if(world.board[position.y + 1][position.x] instanceof Animal){
                    if(world.board[position.y + 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y + 1][position.x]).TurnsLeft()==0){
                            world.board[position.y + 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y + 1][position.x] = new Ground();
                    }
                }
            if(world.board[position.y +1][position.x+1] instanceof Animal){
                    if(world.board[position.y +1][position.x+1]  instanceof Human){
                        if(((Human)world.board[position.y +1][position.x+1] ).TurnsLeft()==0){
                            world.board[position.y +1][position.x+1]  = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y +1][position.x+1] = new Ground();
                    }
                }
            if(world.board[position.y+1 ][position.x- 1] instanceof Animal){
                    if(world.board[position.y+1 ][position.x- 1]  instanceof Human){
                        if(((Human)world.board[position.y+1 ][position.x- 1] ).TurnsLeft()==0){
                            world.board[position.y+1 ][position.x- 1]  = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y+1 ][position.x- 1]  = new Ground();
                    }
                }
            if(world.board[position.y ][position.x+1] instanceof Animal){
                    if(world.board[position.y ][position.x+1] instanceof Human){
                        if(((Human)world.board[position.y ][position.x+1]).TurnsLeft()==0){
                            world.board[position.y ][position.x+1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y ][position.x+1]= new Ground();
                    }
                }
            if(world.board[position.y ][position.x-1] instanceof Animal){
                    if(world.board[position.y ][position.x-1]  instanceof Human){
                        if(((Human)world.board[position.y ][position.x-1] ).TurnsLeft()==0){
                            world.board[position.y ][position.x-1]  = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y ][position.x-1]  = new Ground();
                    }
                }
        }
        else if (position.y == world.GetHeight() - 1) {//bot without corners
            if(world.board[position.y - 1][position.x] instanceof Animal){
                    if(world.board[position.y - 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y - 1][position.x]).TurnsLeft()==0){
                            world.board[position.y - 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y - 1][position.x] = new Ground();
                    }
                }
            if(world.board[position.y -1][position.x+1] instanceof Animal ){
                    if(world.board[position.y -1][position.x+1] instanceof Human){
                        if(((Human)world.board[position.y -1][position.x+1] ).TurnsLeft()==0){
                            world.board[position.y -1][position.x+1]  = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y -1][position.x+1]  = new Ground();
                    }
                }
            if(world.board[position.y-1 ][position.x- 1] instanceof Animal){
                    if(world.board[position.y-1 ][position.x- 1]  instanceof Human){
                        if(((Human)world.board[position.y-1 ][position.x- 1] ).TurnsLeft()==0){
                            world.board[position.y-1 ][position.x- 1]  = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y-1 ][position.x- 1]  = new Ground();
                    }
                }
            if(world.board[position.y][position.x+1] instanceof Animal){
                    if(world.board[position.y][position.x+1] instanceof Human){
                        if(((Human)world.board[position.y][position.x+1]).TurnsLeft()==0){
                           world.board[position.y][position.x+1]= new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y][position.x+1] = new Ground();
                    }
                }
            if(world.board[position.y ][position.x-1] instanceof Animal){
                    if(world.board[position.y ][position.x-1]  instanceof Human){
                        if(((Human)world.board[position.y ][position.x-1] ).TurnsLeft()==0){
                            world.board[position.y ][position.x-1]  = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y ][position.x-1]  = new Ground();
                    }
                }
        }
        else {
            if(world.board[position.y - 1][position.x] instanceof Animal ){
                    if(world.board[position.y - 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y - 1][position.x]).TurnsLeft()==0){
                            world.board[position.y - 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y - 1][position.x] = new Ground();
                    }
                }
            if(world.board[position.y -1][position.x+1] instanceof Animal){
                    if(world.board[position.y -1][position.x+1] instanceof Human){
                        if(((Human)world.board[position.y -1][position.x+1]).TurnsLeft()==0){
                            world.board[position.y -1][position.x+1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y -1][position.x+1] = new Ground();
                    }
                }
            if(world.board[position.y-1 ][position.x- 1] instanceof Animal){
                    if(world.board[position.y-1 ][position.x- 1] instanceof Human){
                        if(((Human)world.board[position.y-1 ][position.x- 1]).TurnsLeft()==0){
                            world.board[position.y-1 ][position.x- 1]= new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y - 1][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y-1 ][position.x- 1] = new Ground();
                    }
                }
            if(world.board[position.y][position.x+1] instanceof Animal){
                    if(world.board[position.y][position.x+1]instanceof Human){
                        if(((Human)world.board[position.y][position.x+1]).TurnsLeft()==0){
                            world.board[position.y][position.x+1]= new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y ][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y][position.x+1]= new Ground();
                    }
                }
            if(world.board[position.y ][position.x-1] instanceof Animal){
                    if(world.board[position.y ][position.x-1]instanceof Human){
                        if(((Human)world.board[position.y ][position.x-1]).TurnsLeft()==0){
                            world.board[position.y ][position.x-1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y ][position.x-1] = new Ground();
                    }
                }
            if(world.board[position.y + 1][position.x] instanceof Animal){
                    if(world.board[position.y + 1][position.x] instanceof Human){
                        if(((Human)world.board[position.y + 1][position.x]).TurnsLeft()==0){
                            world.board[position.y + 1][position.x] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y + 1][position.x] = new Ground();
                    }
                }
            if(world.board[position.y +1][position.x+1] instanceof Animal){
                    if(world.board[position.y +1][position.x+1] instanceof Human){
                        if(((Human)world.board[position.y +1][position.x+1]).TurnsLeft()==0){
                            world.board[position.y +1][position.x+1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x+1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y +1][position.x+1] = new Ground();
                    }
                }
            if(world.board[position.y+1 ][position.x- 1] instanceof Animal){
                    if(world.board[position.y+1 ][position.x- 1] instanceof Human){
                        if(((Human)world.board[position.y+1 ][position.x- 1]).TurnsLeft()==0){
                            world.board[position.y+1 ][position.x- 1] = new Ground();
                        }
                        else{
                            world.Log("Immortality!");
                            ((Human)world.board[position.y + 1][position.x-1]).Immortality();
                        }
                    }
                    else{
                    world.board[position.y+1 ][position.x- 1] = new Ground();
                    }
                }
        }
    }
}