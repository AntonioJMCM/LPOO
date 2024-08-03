package maze.logic;

public class Hero extends Block
{
	private boolean hArmed;
	private boolean hAlive;
	private MazeBuilder maze;
	
	public Hero(MazeBuilder maze,int x, int y)
	{
		this.setPosX(x);
		this.setPosY(y);
		this.maze=maze;
		hArmed = false;
	}
	
	public Hero(MazeBuilder maze)
	{
		this.randomPosition(maze);
		hArmed=false;
		this.maze=maze;
	}
	
	public void displayHero(MazeBuilder maze){
		if(this.getHAlive()){
			if(this.hArmed){
				maze.getMaze()[this.getPosX()][this.getPosY()]='A';
			}else{
				maze.getMaze()[this.getPosX()][this.getPosY()]='H';
			}
		}
		else{
			maze.getMaze()[this.getPosX()][this.getPosY()]=' ';
		}

	}
	
	public Point getHeroPosition()
	{
		return this.position;
	}
	
	public void pickSword()
	{
		hArmed = true;
		setCh('A');
	}
	
	
	
	public void moveDown()
	{
		position.setPointX(position.getPointX()+1);
	}
	
	public void moveUp()
	{
		position.setPointX(position.getPointX()-1);
	}
	
	public void moveLeft()
	{
		position.setPointY(position.getPointY()-1);
	}
	
	public void moveRight()
	{
		position.setPointY(position.getPointY()+1);
	}
	
	public boolean isHeroArmed()
	{
		return this.hArmed;
	}
	
	public boolean getHAlive()
	{
		return hAlive;
	}
	
	public void setHAlive(boolean hAlive)
	{
		this.hAlive=hAlive;
	}
}
