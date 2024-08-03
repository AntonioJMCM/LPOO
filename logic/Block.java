package maze.logic;

import java.util.Random;

public class Block 
{
	protected Point position;
	protected char ch;
	protected boolean dead;
	
	/*public Block(int x, int y) 
	{
		position = new Point(x,y);
		dead = false;
	}*/

	public int getPosX()
	{
		return position.getPointX();
	}
	
	public int getPosY()
	{
		return position.getPointY();
	}
	
	public void setPosX(int x)
	{
		this.position.setPointX(x);
	}
	
	public void setPosY(int y)
	{
		this.position.setPointY(y);
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public char getCh()
	{
		return ch;
	}
	
	public void setCh(char c)
	{
		this.ch = c;
	}
	
	public boolean isDead()
	{
		return this.dead;
	}
	
	public void killBlock()
	{
		this.dead = true;
	}
	
	public int randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public void randomPosition(MazeBuilder maze)
	{
		while( (maze.getMaze()[this.getPosX()][this.getPosY()] == 'X') || (maze.getMaze()[this.getPosX()][this.getPosY()] == 'S') || 
				(maze.getMaze()[this.getPosX()][this.getPosY()] == 'D'))
		{
			int a = randInt(0,maze.getDim()-1);
			int b = randInt(0,maze.getDim()-1);
			
			this.setPosX(a);
			this.setPosY(b);
		}	
	}
	
	public void moveUp()
	{
		this.setPosX(this.getPosX()-1);
	}
	
	public void moveDown()
	{
		this.setPosX(this.getPosX()+1);
	}
	
	public void moveLeft()
	{
		this.setPosX(this.getPosY()-1);
	}
	
	public void moveRight()
	{
		this.setPosX(this.getPosY()+1);
	}
}
