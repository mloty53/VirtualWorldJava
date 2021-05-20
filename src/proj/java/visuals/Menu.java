/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java.visuals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import proj.java.Direction;
import proj.java.Grass;
import proj.java.World;
import proj.java.plants.*;
import proj.java.animals.*;
public class Menu extends JPanel implements KeyListener, ActionListener {
    private World world;
    private Screen s;
    public Menu(World _world, Screen _s) {
        world = _world;
        s= _s;
        repaint();
        AddButtons();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        PrintColors(g);
        PrintLabels(g);
        System.out.println(world.log);

    }
    private void PrintColors(Graphics g){
        g.setColor(new Grass().Color());
        g.fillRect(200,50, 30,30);
        g.setColor(new Guarana().Color());
        g.fillRect(200, 100, 30,30);
        g.setColor(new Sosnowski().Color());
        g.fillRect(200, 150, 30,30);
        g.setColor(new SowThistle().Color());
        g.fillRect(200, 200, 30,30);
        g.setColor(new WolfBerries().Color());
        g.fillRect(200, 250, 30,30);
        g.setColor(new Antylope().Color());
        g.fillRect(200, 300, 30,30);
        g.setColor(new Fox().Color());
        g.fillRect(200, 350, 30,30);
        g.setColor(new Sheep().Color());
        g.fillRect(200, 400, 30,30);
        g.setColor(new Turtle().Color());
        g.fillRect(200, 450, 30,30);
        g.setColor(new Wolf().Color());
        g.fillRect(200, 500, 30,30);
        g.setColor(new Human().Color());
        g.fillRect(200,550, 30,30);

    }
    private void PrintLabels(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN,25));
        g.drawString("Grass", 250, 75);
        g.drawString("Guarana", 250, 125);
        g.drawString("Sosnowski", 250, 175);
        g.drawString("Sow thistle",250,225);
        g.drawString("Wolf Berries",250,275);
        g.drawString("Antylope",250,325);
        g.drawString("Fox",250,375);
        g.drawString("Sheep",250,425);
        g.drawString("Turtle",250,475);
        g.drawString("Wolf",250,525);
        g.drawString("Human",250,575);
    }
    private void AddButtons() {

        //Button's initializations
        JButton nextT = new JButton("Next turn");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");

        //Action listeners
        nextT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.NextTurn(null);
                System.out.println("Next turn button clicked!");
                s.requestFocus();
            }
        });
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                world.Save();
                world.frame.setVisible(false);
                System.out.println("Save button");
                s.requestFocus();
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.Load();
                System.out.println("Load button");
                s.requestFocus();
            }
        });
        //button's sizes
        //nextT.setPreferredSize(new Dimension(100, 50));
        nextT.setBounds(500,500,0,0);
        //save.setPreferredSize(new Dimension(100, 50));
        save.setBounds(100,100,1000,100);
        //load.setPreferredSize(new Dimension(100, 50));
        load.setBounds(100,50,0,50);
        //logs.setPreferredSize(new Dimension(100,50));
        //Adding buttons to menu
        add(nextT);
        add(save);
        add(load);
        
        nextT.setVisible(true);
        save.setVisible(true);
        load.setVisible(true);
        //logs.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){
        repaint();
    }
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                world.NextTurn(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                world.NextTurn(Direction.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                world.NextTurn(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                world.NextTurn(Direction.LEFT);
                break;
            case KeyEvent.VK_ENTER:
                world.StartHumanAbility();
                break;

        }
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}