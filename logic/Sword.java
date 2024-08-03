package maze.logic;

public class Sword extends Block 
{	
	/*public Sword(int x, int y)
	{
		super(x,y,'E');
	}*/
	
	private boolean equipped;
	private MazeBuilder maze;
	
	public Sword(MazeBuilder maze,int x, int y)
	{
		this.setPosX(x);
		this.setPosY(y);
		this.maze=maze;
		this.equipped = false;
	}
	
	public Sword(MazeBuilder maze)
	{
		this.randomPosition(maze);
		this.equipped=false;
		this.maze=maze;
		
	}
	
	public boolean getEquipped()
	{
		return equipped;
	}
	
	public void setEquipped(boolean equipped)
	{
		this.equipped=equipped;
	}
	
	public void displaySword(MazeBuilder maze){
		if(this.equipped == true){
			maze.getMaze()[this.getPosX()][this.getPosY()]=' ';
		}else{
			maze.getMaze()[this.getPosX()][this.getPosY()]='E';
		}
	}
}
