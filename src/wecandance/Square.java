
package wecandance;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;


class Square{
    Shape shape;
    Color colour;
    

    
    
    public Square(Shape shape, Color colour){
        this.shape = shape;
        this.colour = colour;
    }

    /**
     * @return the shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * @param shape the shape to set
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * @return the colour
     */
    public Color getColour() {
        return colour;
    }

    /**
     * @param colour the colour to set
     */
    public void setColour(Color colour) {
        this.colour = colour;
    }
    
    
    
}


class Wall extends Square{
    static final Color DC = new Color(200,18,22);
    public Wall(int x, int y, int w, int h) {
        super(new Rectangle(x,y,w,h),  new Color(200,18,22));
    }
        public Wall(int x, int y, int w, int h, Color c) {
        super(new Rectangle(x,y,w,h), c);
    }
       

}

class Food extends Square{
    public Food(int x, int y, int w, int h) {
        super(new Rectangle(x,y,w,h),  new Color(10,208,22));
            colour = new Color(10,208,22);

    }
    public Food(int x, int y, int w, int h, Color c) {
        super(new Rectangle(x,y,w,h), c);
        colour = c;
    }
  }

class Ground extends Square{

    public Ground(int x, int y, int w, int h) {
        super(new Rectangle(x,y,w,h),new Color(25,25,25));
    }
    public Ground(int x, int y, int w, int h, Color c) {
        super(new Rectangle(x,y,w,h), c);
        colour = c;
    }
  
}

