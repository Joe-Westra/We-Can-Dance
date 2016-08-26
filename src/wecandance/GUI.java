
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
        this(900,500);
    }
    GUI(int width, int height){
        this.width = width;
        this.height = height;
        setSize(width,height);
        pg = new PlayGrid(width, height);
        pg.PopulateGrid();
        setContentPane(pg);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pg.addComponentListener(this);
        
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
        pg.Resize(this.getWidth(), this.getHeight());
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
