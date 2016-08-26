
package wecandance;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import javax.swing.*;

public class PlayGrid extends JPanel{
    private int xSize;
    private int ySize;
    private int xUnitSize;
    private int yUnitSize;
    private int xDimensions;
    private int yDimensions;
    Square[][] squares;
    
            
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
    }
  
    void Resize(int width, int height){
        xSize = width;
        ySize = height;
        SetUnitSize();
        PopulateGrid();
    }
    @Override
 public void paint (Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
                    g2.drawRect(100,100,xUnitSize,yUnitSize);
                    for(int x = 0; x < xDimensions; x++){
            for(int y = 0; y < yDimensions; y++){
                g2.setColor(squares[x][y].getColour());
                    g2.draw(squares[x][y].getShape());
                    g2.fill(squares[x][y].getShape());
            }}
}
    /*
    Determines the dimensions (in pixels) of a single square. 
    */
    private void SetUnitSize() {
        setxUnitSize(getxSize() / getxDimensions());
        setyUnitSize(getySize() / getyDimensions());
        System.out.printf("a square is %d by %d pixels", getxUnitSize(), getyUnitSize());
    }
    
    
    public void PopulateGrid(){
        
        for(int x = 0; x < xDimensions; x++){
            for(int y = 0; y < yDimensions; y++){
                int xloc = x*xUnitSize;
                int yloc = y*yUnitSize;
                
                
                
                
                squares[x][y] = (Math.random() <.5)? new Wall(xloc,yloc,xUnitSize,yUnitSize): new Food(xloc,yloc,xUnitSize,yUnitSize);  // this is a test
               


                
                
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * @return the xSize
     */
    public int getxSize() {
        return xSize;
    }

    /**
     * @param xSize the xSize to set
     */
    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    /**
     * @return the ySize
     */
    public int getySize() {
        return ySize;
    }

    /**
     * @param ySize the ySize to set
     */
    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    /**
     * @return the xUnitSize
     */
    public int getxUnitSize() {
        return xUnitSize;
    }

    /**
     * @param xUnitSize the xUnitSize to set
     */
    public void setxUnitSize(int xUnitSize) {
        this.xUnitSize = xUnitSize;
    }

    /**
     * @return the yUnitSize
     */
    public int getyUnitSize() {
        return yUnitSize;
    }

    /**
     * @param yUnitSize the yUnitSize to set
     */
    public void setyUnitSize(int yUnitSize) {
        this.yUnitSize = yUnitSize;
    }

    /**
     * @return the xDimensions
     */
    public int getxDimensions() {
        return xDimensions;
    }

    /**
     * @param xDimensions the xDimensions to set
     */
    public void setxDimensions(int xDimensions) {
        this.xDimensions = xDimensions;
    }

    /**
     * @return the yDimensions
     */
    public int getyDimensions() {
        return yDimensions;
    }

    /**
     * @param yDimensions the yDimensions to set
     */
    public void setyDimensions(int yDimensions) {
        this.yDimensions = yDimensions;
    }


    
}
