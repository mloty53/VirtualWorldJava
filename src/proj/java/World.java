/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj.java;

import proj.java.animals.*;
import proj.java.plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

import proj.java.animals.Human;
import proj.java.visuals.Menu;
import proj.java.visuals.Screen;

public class World  {
    private String text;
    private int sinceLastSuperAbility = 0;
    private int width, height;
    public List<Organism> organisms = new LinkedList<>();
    private List<Organism>toAdd = new LinkedList<>();
    private List<Organism>toRem = new LinkedList<>();
    public String log, logAdmin;
    private boolean humanAlive;
    public JFrame frame;
    public Organism[][] board;
    World(int _width, int _height){
        width = _width;
        height = _height;
        board = new Organism[height][width];
        log="";
        logAdmin="";
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                board[i][j] = new Ground();
            }
        }
    }

    public void PrintWorld(){

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1,1,0,0));
        frame.setTitle("Virtual World");
        frame.setSize(1300,800);
        Screen s = new Screen(frame, this,board,height,width);
        frame.add(s);
        s.setVisible(true);

        proj.java.visuals.Menu m = new Menu(this,s);
        m.setVisible(true);
        frame.add(m);
        
        frame.setVisible(true);



    }

    public void StartHumanAbility(){
        if(sinceLastSuperAbility==0){
            for(Organism it : organisms){
                if(it instanceof Human){
                    ((Human)it).UseAbility();
                    sinceLastSuperAbility = 10;
                   System.out.println("Super Power!");
                }
            }
        }

}
    public void Add(Organism newO){
        board[newO.position.y][newO.position.x] = newO;
        if(organisms.isEmpty()){
            organisms.add(newO);
        }
        else{
            boolean added = false;
            for(Organism it : organisms){
                if(newO.initiative > it.initiative){
                    organisms.add(organisms.indexOf(it), newO);
                    added = true;
                    break;
                }
            }
            if(!added) {
                organisms.add(newO);
            }
        }
    }
    public void toAdd(Organism newO){
        board[newO.position.y][newO.position.x] = newO;
        toAdd.add(newO);
    }
    public void NextTurn(Direction dir){

        for(Organism it : organisms){//performing actions
            if(it.age>0){
               if(board[it.position.y][it.position.x].GetSign() == it.GetSign()){
                   if(it instanceof Human) ((Human) it).Action(dir);
                   else it.Action();
               }
            }
        }
        for(Organism it : toAdd){
            if(!( it instanceof Ground)){
                Add(it);
            }
        }
        toAdd.clear();
        for(Organism it : organisms){
            if(it instanceof Sosnowski){
                ((Sosnowski) it).Burn();
            }
        }
        for(Organism it : organisms){//removing dead organisms
            if(board[it.position.y][it.position.x] instanceof Ground){
                toRem.add(it);
            }
        }
        for(Organism it : toRem){
            organisms.remove(it);
        }
        toRem.clear();

        for(Organism it : organisms) it.age++;
        for(Organism it : organisms){
            if(it instanceof Human){
                //if(((Human)it).TurnsLeft()>0) ((Human)it).Immortality();
            }
        }
        if(sinceLastSuperAbility>0) sinceLastSuperAbility--;
        frame.invalidate();
        frame.validate();
        frame.repaint();

        //TODO: complete after implementation of more organisms
    }
    public int GetHeight(){ return height;}
    public int GetWidth(){ return width;}
    public void PrintLog(){
        System.out.print(log);
        log="";
    }
    public void PrintLogAdmin(){
        System.out.print(logAdmin);
        logAdmin="";
    }
    public void Log(String _log){
        log += _log;
        log += '\n';
    }
    public boolean isHumanAlive(){return humanAlive;}
    public int AmountOfOrganisms(){ return organisms.size();}
    public void Save() {
        //TODO

        JFrame input = new JFrame();
        JTextField textField  = new JTextField(30);
        input.add(textField);
        input.setSize(100,100);
        input.setVisible(true);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = textField.getText();

                String save = "";
                save+=(width + " " + height + " "+ organisms.size() + " " + sinceLastSuperAbility + "\n");
                for(Organism it : organisms){
                    save+=(it.GetSign() + " ");
                    save+=(it.position.x + " ");
                    save+=(it.position.y + " ");
                    save+=(it.age + " ");
                    save+=(it.power + " ");
                    save+=(it.initiative + " ");
                    if(it instanceof Human){
                        save+=(((Human) it).GetId() + " ");
                        save+=(((Human) it).TurnsLeft() + " ");
                    }
                    save+="\n";
                }
                try {
                   // System.out.print(save);
                    SaveToFile(text, save);
                }catch(IOException ie){
                    ie.printStackTrace();
                }


                input.setVisible(false);
                frame.setVisible(true);
            }
        });



    }
    private void SaveToFile(String fileName, String content) throws IOException{
        try{
            String[] elements = content.split("\n");
            String path = ".\\src\\proj\\java\\saves\\" + text;
           // System.out.print(content);
            File file = new File(path);
            System.out.println("Write to " + text);
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for(String it : elements){
                writer.write(it);
                writer.newLine();
            }
            writer.close();

        }catch(IOException ie){
            ie.printStackTrace();
        }

    }
    public void Load(){
        //TODO
        frame.setVisible(false);
        JFrame input = new JFrame();
        JTextField textField  = new JTextField(30);
        input.add(textField);
        input.setSize(100,100);
        input.setVisible(true);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = textField.getText();
                System.out.println(text);
                try {
                    LoadWorldFromFile(text);
                }catch(IOException ie){
                    ie.printStackTrace();
                }
                input.setVisible(false);

                PrintWorld();
            }
        });

    }
    private void LoadWorldFromFile(String FileName) throws IOException{
        String path = ".\\src\\proj\\java\\saves\\"+FileName;
        File file = new File(path);
        if(file.exists()){
            log="";
            board=null;
            organisms.clear();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            String[] elements = line.split(" ");

            width = Integer.parseInt(elements[0]);
            height = Integer.parseInt(elements[1]);
            int tempSize = Integer.parseInt(elements[2]);
            sinceLastSuperAbility = Integer.parseInt(elements[3]);


            board = new Organism[height][width];
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    board[i][j] = new Ground();
                }
            }
            for(int i = 0; i < tempSize; i++){
                line = reader.readLine();

                elements = line.split(" ");
                switch(elements[0]){
                    case "H":
                        Add(new Human(this,//world
                                new Coords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5]),//initiative
                                Integer.parseInt(elements[6]),//id
                                Integer.parseInt(elements[7])));//turnsLeft
                        humanAlive = true;
                        break;
                    case "A":
                        Add(new Antylope(this,//world
                                new Coords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "W":
                        Add(new Wolf(this,//world
                                new Coords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "F":
                        Add(new Fox(this,//world
                                new Coords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "S":
                        Add(new Sheep(this,//world
                                new Coords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "T":
                        Add(new Turtle(this,//world
                                new Coords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "s":
                        Add(new Sosnowski(this,//world
                                new Coords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "g":
                        Add(new Guarana(this,//world
                                new Coords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "w":
                        Add(new WolfBerries(this,//world
                                new Coords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                    case "t":
                        Add(new SowThistle(this,//world
                                new Coords(Integer.parseInt(elements[1]),Integer.parseInt(elements[2])),//posiiton
                                Integer.parseInt(elements[3]),//age
                                Integer.parseInt(elements[4]),//power
                                Integer.parseInt(elements[5])));//initiative
                        break;
                }
            }

        }

    }

}