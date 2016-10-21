
package wecandance;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

import wecandance.Cell.TYPES;

public class PlayGrid extends JPanel{
	private int xSize;
	private int ySize;
	private int wIDTH;
	private int hEIGHT;
	private int xDimensions;
	private int yDimensions;
	private int xBorderSize;
	private int yBorderSize;
	Cell[][] cells;
	private boolean gameInSession = false;
	Players[] players;
	java.util.Timer gameTime;
	enum Direction{NORTH,SOUTH,EAST,WEST,NULL}


	PlayGrid(){
		this(500,500);
	}
	PlayGrid(int xSize, int ySize){
		this(xSize,ySize, 20,20);
	}
	PlayGrid(int xSize, int ySize, int xDimensions, int yDimensions){
		this(xSize,ySize,xDimensions,yDimensions,1);
	}
	PlayGrid(int xSize, int ySize, int xDimensions, int yDimensions, int players){
		this.xDimensions = xDimensions;
		this.yDimensions = yDimensions;
		this.players = new Players[players];
		cells = new Cell[xDimensions][yDimensions];
		Resize(xSize, ySize);
		//display any "get ready messages"
		InitGame();
		gameTime = new Timer();

	}

	TimerTask test = new TimerTask() { public void run(){
		if(gameInSession)
			MoveSnake();
		repaint();
	}};

	void Resize(int width, int height){
		xSize = width;
		ySize = height;
		wIDTH = Math.floorDiv(xSize, xDimensions);
		hEIGHT = Math.floorDiv(ySize, yDimensions);
		PopulateGrid();
		repaint();
	}

	private void MoveSnake() {
		for(Players p : players){
			if(p.nextD != Direction.NULL){
				p.setD(p.nextD);
				p.nextD = Direction.NULL;
			}
			ArrayList<Point> lastlocations = new ArrayList<Point>((Collection) p.body.clone());
			int x = (int)(p.GetHead().getX());
			int y = (int)(p.GetHead().getY());

			switch(p.d){//Moving the snake, ie. add the next square to the body, then collision detection
			case NORTH:
				p.body.add(0,new Point(x,y-1));
				break;
			case SOUTH:
				p.body.add(0,new Point(x,y+1));
				break;
			case EAST:
				p.body.add(0,new Point(x+1,y));
				break;
			case WEST:
				p.body.add(0,new Point(x-1,y));
				break;
			}
			//collision detection
			x = (int)(p.GetHead().getX());
			y = (int)(p.GetHead().getY());

			if(cells[x][y].type == TYPES.FOOD){
				p.changeBody++;
			}else if(cells[x][y].type == TYPES.WALL || cells[x][y].type == TYPES.PLAYER){
				gameInSession = false;
				System.out.println("Ouch, " + p.name +"!!!" + "\nYou are at " + p.score + " points!");
				p.score --;
			}

			if(p.changeBody > 0){
				p.changeBody -= 1;
			}else{
				p.body.remove(p.body.size()-1);
				if(p.changeBody < 0){
					try{
						p.body.remove(p.body.size()-1);
					}catch(Exception e){
						System.out.println("NO BODY TO DELETE"); //game over for that player!  the body is too small
					}
					p.changeBody +=1;
				}
			}//EO IF/ELSE
			//actually move the snake

			for(Point oldloc : lastlocations){
				cells[oldloc.x][oldloc.y].type = TYPES.GROUND;
			}
			for(Point pp : p.body){
				cells[pp.x][pp.y].type = TYPES.PLAYER;
			}



		}//EO FE players
		//   repaint();
	}//EO MoveSanke();


	public void togglePause(){
		gameInSession = ! gameInSession;
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
				g2.setColor(cells[x][y].type.colour);
				g2.fillRect(x*xDimensions, y * yDimensions,wIDTH,hEIGHT );
			}
		}
	}

	public void InitGame(){

		for(int i = 0; i < players.length;i++){
			players[i] = new Players();
		}   

		for(Players p : players){
			int rnd = (int)(Math.random()*4)+1;
			switch(rnd){
			case 1:
				p.setD(Direction.NORTH);
				break;
			case 2:
				p.setD(Direction.SOUTH);
				break;
			case 3:
				p.setD(Direction.EAST);
				break;
			case 4:
				p.setD(Direction.WEST);
				break;
			}//EO switch


			p.Grow(new Point(((int)(Math.random()*xDimensions)+1),((int)(Math.random()*yDimensions)+1)));

		}//EO FE players loop
		for(int x = 0; x < xDimensions; x++){
			for(int y = 0; y < yDimensions; y++){
				if(x == 0 || y == 0 || x == xDimensions-1 || y == yDimensions - 1)
					cells[x][y] = new Cell(TYPES.WALL);
				else{
					int random = (int)(Math.random()*100)+1;
					if(random <5)
						cells[x][y] = new Cell(TYPES.FOOD);
					else if(random < 20)
						cells[x][y] = new Cell(TYPES.WALL);
					else
						cells[x][y] = new Cell(TYPES.GROUND);
				}
			}
		}// end of for loops
		for(Players p : players){
			int rndx = (int)(Math.random()*xDimensions);
			int rndy = (int)(Math.random()*yDimensions);
			cells[rndx][rndy].type = TYPES.PLAYER;
		}//end of FE players loop 
		MoveSnake();   

	}

	public void PopulateGrid(){
		if(! gameInSession){
			gameInSession = true;
}
	}

	class Players{
		ArrayList<Point> body;
		Direction d;
		Direction nextD = Direction.NULL;
		int changeBody = 3;
		String name;
		int score = 0;

		Players(){
			body = new ArrayList<>();
			name = "";
		}

		void Grow(Point p){
			body.add(p);
		}

		Point GetHead(){
			return body.get(0);
		}

		/**
		 * @return the d
		 */
		public Direction getD() {
			return d;
		}

		/**
		 * @param d the d to set
		 */
		public void setD(Direction d) {
			this.d = d;
		}


		public void turnLeft(){
			switch(d){
			case NORTH:
				nextD = Direction.WEST;
				break;
			case WEST:
				nextD = Direction.SOUTH;
				break;
			case SOUTH:
				nextD = Direction.EAST;
				break;
			case EAST:
				nextD = Direction.NORTH;
				break;
			}
		}

		public void turnRight(){
			switch(d){
			case NORTH:
				nextD = Direction.EAST;
				break;
			case WEST:
				nextD = Direction.NORTH;
				break;
			case SOUTH:
				nextD = Direction.WEST;
				break;
			case EAST:
				nextD = Direction.SOUTH;
				break;
			}
		}


	}//EO class Player


	public void StartGame(){
		gameTime.schedule(test, 500, 500);

	}


}