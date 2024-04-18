package ski;

import basicgraphics.BasicContainer;
import basicgraphics.BasicFrame;
import basicgraphics.ClockWorker;
import basicgraphics.SpriteComponent;
import basicgraphics.sounds.ReusableClip;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author gavinmorris
 */

public class dodgeball {
    
    public static void main (String[] args) {
        
        //Part 1: The First Frame
        BasicFrame bf = new BasicFrame("Ski Game");
        Container content = bf.getContentPane();
        CardLayout cards = new CardLayout();
        content.setLayout(cards);
        BasicContainer bc1 = new BasicContainer();
        bc1.setPreferredSize(new Dimension(300, 200));
        content.add(bc1, "Splash");
        String[][] splashLayout = {
            {"Title"},
            {"Button"},
            {"Button1"},
            {"Button2"},
            {"Button 3"},
        };
        bc1.setStringLayout(splashLayout);
        bc1.add("Title", new JLabel("Ski Game"));
        JButton jstart = new JButton("Start");
        bc1.add("Button", jstart);
        JButton jstart1 = new JButton("Choose Team");
        bc1.add("Button2", jstart1);
        JButton link = new JButton("SkiGame Website");
        bc1.add("Button 3", link);

       
        
        //Part 2: The "Level Choice" Frame
        BasicContainer bc2 = new BasicContainer();
        content.add(bc2, "LC");

        String[][] splashLayout1 = {
            {"Title1"},
            {"lvl1"},
            {"lvl2"},
            {"lvl3"},  
            {"lvl4"},
            {"lvl5"},
            {"lvl6"},
            {"lvl7"},
            {"lvl8"},
            {"lvl9"},
            {"lvl10"},
            {"Back"}
        };
        
        bc2.setStringLayout(splashLayout1);
        bc2.add("Title1",new JLabel("Select Level"));
        JButton lvl1 = new JButton("Play Level 1");
        bc2.add("lvl1", lvl1);
        JButton lvl2 = new JButton("Play Level 2");
        bc2.add("lvl2", lvl2);
        JButton lvl3 = new JButton("Play Level 3");
        bc2.add("lvl3", lvl3);
        JButton lvl4 = new JButton("Play Level 4");
        bc2.add("lvl4", lvl4);
        JButton lvl5 = new JButton("Play Level 5");
        bc2.add("lvl5", lvl5);
        JButton lvl6 = new JButton("Play Level 6");
        bc2.add("lvl6", lvl6);
        JButton lvl7 = new JButton("Play Level 7");
        bc2.add("lvl7", lvl7);
        JButton lvl8 = new JButton("Play Level 8");
        bc2.add("lvl8", lvl8);
        JButton lvl9 = new JButton("Play Level 9");
        bc2.add("lvl9", lvl9);
        JButton lvl10 = new JButton("Play Level 10");
        bc2.add("lvl10", lvl10);
        JButton back = new JButton("Back");
        bc2.add("Back", back);
        
        //Part 3: The "Choose Color" Frame
        BasicContainer bc3 = new BasicContainer();
        content.add(bc3, "CC");
        String[][] splashLayout2 = {
            {"Red Team Button"},
            {"Blue Team Button"},
            {"Green Team Button"},
            {"Back1"}
        };
        bc3.setStringLayout(splashLayout2);
        bc3.add("Red Team Button", new JButton("Red Player"));
        bc3.add("Blue Team Button", new JButton("Blue Player"));
        bc3.add("Green Team Button", new JButton("Green Player"));
        JButton back1 = new JButton("Back");
        bc3.add("Back1", back1);
        
        //Part 4: The Fourth Frame
        final BasicContainer level1 = new BasicContainer();
        content.add(level1,"Game");
        final SpriteComponent sc = new SpriteComponent() {
            @Override
            public void paintBackground(Graphics g) {
                Dimension d = getSize();
                g.setColor(Color.white);
                g.fillRect(0, 0, d.width, d.height);
                final int NUM_STARS = 30;
                Random rand = new Random();
                rand.setSeed(0);
                g.setColor(Color.black);
                for(int i=0;i<NUM_STARS;i++) {
                    int diameter = rand.nextInt(5)+1;
                    int xpos = rand.nextInt(d.width);
                    int ypos = rand.nextInt(d.height);
                    g.fillOval(xpos, ypos, diameter, diameter);
                }
            }
        };
        
        sc.setPreferredSize(new Dimension(800,400));
        level1.add(sc);
        final player f = new player(sc);
        final Obstacle f1 = new Obstacle(sc);
     
        

        //Part 5: Frames of the game
        lvl1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "Game");
                level1.requestFocus();
            }
        });

        //Part 6: Give the Buttons Life
        jstart.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "LC");
                bc2.requestFocus();
            }  
        });
        jstart1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "CC");
                bc3.requestFocus();
            } 
        });
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "Splash");
                bc1.requestFocus();
            } 
        });
        back1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "Splash");
                bc1.requestFocus();
            }
        });
        
        link.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URL("https://gavin-morris-04.github.io/Ski-Game-real/").toURI());
                } catch(Exception i) {
                    
                }
            }
        });
        
        ClockWorker.addTask(sc.moveSprites());
        ClockWorker.initialize(10);
        
        
        level1.addKeyListener(new KeyAdapter(){
           @Override
           public void keyReleased(KeyEvent e) {
               f.setPicture(f.initialPic);
           }
           @Override
           public void keyPressed(KeyEvent e) {
              if(KeyEvent.VK_UP == e.getKeyCode()) {
                  f.setVel(f.getVelX(),f.getVelY() - 1);
              } 
              else if (KeyEvent.VK_RIGHT == e.getKeyCode()) {
                   f.setVel(f.getVelX()+1,f.getVelY());
              }
              else if (KeyEvent.VK_LEFT == e.getKeyCode()) {
                  f.setVel(f.getVelX()-1,f.getVelY());
              }
              else if (KeyEvent.VK_DOWN == e.getKeyCode()) {
                  f.setVel(f.getVelX(),f.getVelY() + 1);
              }
           }
       });
        
       f1.setVel(10, 0);
       f1.setY(100);
       f1.setX(800);
       f1.setHeadingOffset(Math.PI);
       
       //Part ??: We add the collision events
       sc.addSpriteSpriteCollisionListener(player.class, Obstacle.class, (player sp1, Obstacle sp2) -> {
        sp1.setActive(true);
        if(sp1.getVelX()>0.1||sp1.getVelX()<-0.1) {
            JOptionPane.showMessageDialog(sc, "X-Velocity too large! " + sp1.getVelX());
            System.exit(0);

        }
        else if (sp1.getVelY() > 0.4) {
            JOptionPane.showMessageDialog(sc, "Y-velocity too large! " + sp1.getVelY());
            System.exit(0);

        }
        else {
            JOptionPane.showMessageDialog(sc, "You crashed into the obstacle");
            System.exit(0);

        }
    });
        bf.show();
    }
}