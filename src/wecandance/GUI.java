
package wecandance;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GUI extends JFrame implements KeyListener, ComponentListener {
PlayGrid pg;
int width, height;
    GUI(){
        this(400,400);
    }
    GUI(int width, int height){
        this.width = width;
        this.height = height;
        setSize(width,height);
        pg = new PlayGrid(width-20, height-40, 50,50);
        pg.PopulateGrid();
        setContentPane(pg);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pg.addComponentListener(this);
        pg.start();
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
        pg.Resize(this.getWidth()-20, this.getHeight()-40);
        pg.repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
    
}
