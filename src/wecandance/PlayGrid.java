
package wecandance;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

public class PlayGrid extends JPanel{
    private int xSize;
    private int ySize;
    private int xUnitSize;
    private int yUnitSize;
    private int xDimensions;
    private int yDimensions;
    private int xBorderSize;
    private int yBorderSize;
    Square[][] squares;
    private boolean gameInSession;
    java.util.Timer gameTime;
    Color gc, fc, wc;
            
    PlayGrid(){
        this(500,500);
    }
    PlayGrid(int xSize, int ySize){
        this(xSize,ySize, 100,100);
    }
    
    PlayGrid(int xSize, int ySize, int xDimensions, int yDimensions){
        this.xDimensions = xDimensions;
        this.yDimensions = yDimensions;
        squares = new Square[xDimensions][yDimensions];
        Resize(xSize, ySize);
        //display any "get ready messages"
        gameTime = new Timer();
        gc = new Color(25,25,25);
        fc = new Color(10,20,22);
        wc = new Color(200,18,22);
    }
    
    
    TimerTask test = new TimerTask() { public void run(){
        if(gameInSession){
            for(int x = 0; x < xDimensions; x++){
                for(int y = 0; y < yDimensions; y++){

                    if(squares[x][y].getClass() == Food.class)
                        squares[x][y].setColour(new Color(0,255,0));
                    else if(squares[x][y].getClass() == Ground.class)
                        squares[x][y].setColour(new Color(255,255,28));
                    else if(squares[x][y].getClass() == Wall.class)
                        squares[x][y].setColour(new Color(255,255,255));


                }
            }    
        }
        repaint();
    }};
        
    void Resize(int width, int height){
        xSize = width;
        ySize = height;
        xUnitSize = Math.floorDiv(xSize, xDimensions);
        yUnitSize = Math.floorDiv(ySize, yDimensions);
        System.out.printf("a square is %d by %d pixels \n", xUnitSize, yUnitSize);
        PopulateGrid();
        repaint();
        
        
    }
    
    //add a fancy border around the playgrid...
    void SteupBorder(){
        xBorderSize = 0;
        yBorderSize = 0;
        
    }
    
    @Override
    public void paint (Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect(0,0,xSize+20,ySize+20);
        for(int x = 0; x < xDimensions; x++){
            for(int y = 0; y < yDimensions; y++){
                g2.setColor(squares[x][y].getColour());
                g2.draw(squares[x][y].getShape());
                g2.fill(squares[x][y].getShape());
            }
        }
}

    
    
public void PopulateGrid(){
        if(! gameInSession){
            for(int x = 0; x < xDimensions; x++){
                for(int y = 0; y < yDimensions; y++){
                    int xloc = x*xUnitSize;
                    int yloc = y*yUnitSize;
                    int random = (int)(Math.random()*100)+1;
                    if(random <5)
                        squares[x][y] = new Food(xloc,yloc,xUnitSize,yUnitSize);
                    else if(random < 20)
                        squares[x][y] = new Wall(xloc,yloc,xUnitSize,yUnitSize);
                    else
                        squares[x][y] = new Ground(xloc,yloc,xUnitSize,yUnitSize);

                }
            }// end of for loops
            gameInSession = true;
        }else{
            for(int x = 0; x < xDimensions; x++){
                for(int y = 0; y < yDimensions; y++){
                    int xloc = x*xUnitSize;
                    int yloc = y*yUnitSize;
                    squares[x][y].setShape(new Rectangle(xloc,yloc,xUnitSize,yUnitSize));
                }
            }
            
            
            
            
        }
    }
    
    
        
    
public void start(){
                gameTime.schedule(test, 5000, 5000);

}
    
 
}