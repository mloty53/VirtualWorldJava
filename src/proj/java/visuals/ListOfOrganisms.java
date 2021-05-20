/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java.visuals;

import proj.java.Coords;
import proj.java.Organism;
import proj.java.World;
import proj.java.animals.*;
import proj.java.plants.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListOfOrganisms extends JFrame {
    private JList<String> orgList;
    private World world;
    private Coords pos;
    private JFrame frame;
    public ListOfOrganisms(World _world, Coords _pos){
        world = _world;
        pos = _pos;
        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement("Antylope");
        model.addElement("Fox");
        model.addElement("Sheep");
        model.addElement("Turtle");
        model.addElement("Wolf");
        model.addElement("Guarana");
        model.addElement("Sosnowski");
        model.addElement("Sow thistle");
        model.addElement("Wolf berries");

        orgList = new JList<>(model);
        orgList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orgList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                switch(orgList.getSelectedValue()) {
                    case "Antylope":
                        world.Add(new Antylope(world, pos));
                        break;
                    case "Fox":
                        world.Add(new Fox(world, pos));
                        break;
                    case "Sheep":
                        world.Add(new Sheep(world, pos));
                        break;
                    case "Turtle":
                        world.Add(new Turtle(world, pos));
                        break;
                    case "Wolf":
                        world.Add(new Wolf(world, pos));
                        break;
                    case "Guarana":
                        world.Add(new Guarana(world, pos));
                        break;
                    case "Sosnowski":
                        world.Add(new Sosnowski(world, pos));
                        break;
                    case "Sow thistle":
                        world.Add(new SowThistle(world, pos));
                        break;
                    case "Wolf berries":
                        world.Add(new WolfBerries(world, pos));
                        break;
                }
                world.frame.repaint();
            SwingUtilities.getWindowAncestor(orgList).setVisible(false);
            }

        });
        add(orgList);
        setTitle("List of organisms");
        orgList.setSize(350,250);
        setSize(350,250);
        setVisible(true);

    }
}