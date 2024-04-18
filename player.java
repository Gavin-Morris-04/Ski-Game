/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ski;

import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.images.Picture;
import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author gavinmorris
 */
public class player extends Sprite {
    public Picture initialPic;
    /**
     * Initializes the sprite, setting its picture,
     * position, and speed. It also adds it to the
     * SpriteComponent.
     * 
     * @param sc
     */
    
    public player(SpriteComponent sc) {
        super(sc);
            initialPic = new Picture("villager.jpeg");
            setPicture(initialPic);
            Dimension d = sc.getSize();
            setX(0);
            setY(0);
            setVel(0, 0);
    }
    @Override 
        public void processEvent(SpriteCollisionEvent ev) {
        SpriteComponent sc1 = getSpriteComponent();
        if(ev.xlo) {
            JOptionPane.showMessageDialog(sc1, "You ran out the gyn.");
            System.exit(0);
        } else if (ev.xhi) {
            JOptionPane.showMessageDialog(sc1, "You ran out the gyn.");
            System.exit(0);
        } else if (ev.ylo) {
            JOptionPane.showMessageDialog(sc1, "You ran out the gyn.");
            System.exit(0);
        }  else if (ev.yhi) {
            JOptionPane.showMessageDialog(sc1, "You ran out the gym");
            System.exit(0);
        }
    }
    
}

