
package wecandance;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import javafx.scene.shape.Ellipse;


class Square{
    private Shape shape;
    private Color colour;
    
    public Square(Shape shape,Color colour){
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
    public Wall(int x, int y, int w, int h) {
        super(new Rectangle(x,y,w,h),  new Color(45,18,22));
    }
}

class Food extends Square{
     public Food(int x, int y, int w, int h) {
        super(new Rectangle(x,y,w,h),  new Color(125,48,222));
    }   
}