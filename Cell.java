package wecandance;

import java.awt.Color;

public class Cell {
    TYPES type;
    enum TYPES{
    	WALL (new Color(200,18,22)),
    	FOOD(new Color(200,188,22)),
    	PLAYER(new Color(25,125,205)),
    	GROUND(new Color(25,25,25));
    protected Color colour;
    TYPES(Color colour){
    	this.colour = colour;
    }};

    public Cell(TYPES type){
    	this.type = type;
    }
    
    
}
