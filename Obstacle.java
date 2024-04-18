/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ski;

import basicgraphics.BasicFrame;
import basicgraphics.ClockWorker;
import basicgraphics.CollisionEventType;
import basicgraphics.Sprite;
import basicgraphics.SpriteCollisionEvent;
import basicgraphics.SpriteComponent;
import basicgraphics.Task;
import basicgraphics.images.Picture;
import static java.awt.Color.black;
import static java.awt.Color.blue;
import static java.awt.Color.orange;
import static java.awt.Color.red;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author gavinmorris
 */
class Obstacle extends Sprite {
    Picture basePic;
    Obstacle(SpriteComponent sc) {
        super(sc);
        Picture obstacle;
        
        BufferedImage img2 = BasicFrame.createImage(50, 50);
        Graphics2D g = (Graphics2D) img2.getGraphics();
        int w = img2.getWidth();
        int h = img2.getHeight();
        g.setColor(red);
        g.fillOval(5, 5, 20, 20);
        obstacle = new Picture(img2);
        setPicture(obstacle);
        freezeOrientation = true;
        setX(w/2);
        setY(h/2);


        final double del = .1;
        ClockWorker.addTask(new Task() {
            @Override
            public void run() {
                count++;
                if (count == 100) {
                    setHeadingOffset(Math.PI+del);
                } else if (count == 200) {
                    setHeadingOffset(Math.PI-del);
                    count = 0;
                }
            }
        });
    }
    int count = 0;
    @Override
    public void processEvent(SpriteCollisionEvent ev) {
        if(ev.eventType == CollisionEventType.WALL_INVISIBLE) {
            setVel(-getVelX(), 0);
            setPicture(getPicture().flipUpDown());
        }
    }
}