package maze.logic;

import java.util.Random;

public class Dragon extends Block
{
	protected boolean sleep;
	private boolean dAlive;
	private MazeBuilder maze;
	
	public Dragon(MazeBuilder maze, int x, int y)
	{
		this.setPosX(x);
		this.setPosY(y);
		this.maze=maze;
		dAlive = true;
	}
	
	public Dragon(MazeBuilder maze){
		this.randomPosition(maze);
		this.dAlive=true;
		this.sleep=false;
		this.maze=maze;
	}
	
	/*public Dragon(int x, int y)
	{
		super(x,y,'D');
		this.sleep = false;
	}*/
	
	public MazeBuilder getMaze()
	{
		return maze;
	}
	
	public void setSleep(boolean bool)
	{
		this.sleep = bool;
	}
	
	public boolean isDead()
	{
		return this.dead;
	}
	
	public boolean isSleep()
	{
		return sleep;
	}
	
	public void killDragon()
	{
		this.dead=true;
		setCh(' ');
	}
	
	public void set_alive(boolean alive){
		this.dAlive=alive;
	}
	
	public boolean get_alive(){
		return this.dAlive;
	}
	
	public void displayDragon(MazeBuilder maze, Sword espada){
		if(this.dAlive){
			if(!this.isSleep()){
				maze.getMaze()[this.getPosX()][this.getPosY()]='D';
			}else{
				maze.getMaze()[this.getPosX()][this.getPosY()]='Z';
			}
		}else{
			maze.getMaze()[this.getPosX()][this.getPosY()]=' ';
		}
	}
	
	public int generateInt()
	{
		Random rn = new Random();
		int answer = rn.nextInt(5);
		return answer;
	}
	
	public int generateInt01()
	{
		Random rn = new Random();
		int answer = rn.nextInt(2);
		return answer;
	}
	
	public void randomMoveDragonSleep()
	{
		int ctrl = generateInt01();
		
		if (ctrl == 0)
		{
			this.setSleep(false);
			this.setCh('D');
			randomMoveDragon();
			return;
		}
		
		if (ctrl == 1)
		{
			this.setCh('L');
			this.setSleep(true);
			System.out.println("adormeceu");
			return;
		}
	}
	
	public void randomMoveDragon() 
	{
			int ctrl = generateInt();			
			
			if (ctrl == 0) // 0-STAY 
				return;
			
			if (ctrl == 1) // 1-CIMA 
			{
				if (this.getMaze().getMaze()[this.getPosX()-1][this.getPosY()] == 'X' || this.getMaze().getMaze()[this.getPosX()-1][this.getPosY()] == 'S')
					return;

				this.getMaze().getMaze()[this.getPosX()][this.getPosY()] = ' ';
				this.moveUp();
			}
				
			if (ctrl == 2) // 2-DIREITA 
			{
				if (this.getMaze().getMaze()[this.getPosX()][this.getPosition().getPointY()+1] == 'X' || 
						this.getMaze().getMaze()[this.getPosition().getPointX()][this.getPosition().getPointY()+1] == 'S')
					return;

				if (this.getMaze().getMaze()[this.getPosition().getPointX()][this.getPosition().getPointY()+1] == 'E')
				{
					this.setCh('F');
					this.getMaze().getMaze()[this.getPosition().getPointX()][this.getPosition().getPointY()] = ' ';
					this.moveRight();
					return;
				}
				this.getMaze().getMaze()[this.getPosition().getPointX()][this.getPosition().getPointY()] = ' ';
				this.moveRight();
			}
				
			if (ctrl == 3) // 3-BAIXO  
			{
				if (this.getMaze().getMaze()[this.getPosition().getPointX()+1][this.getPosition().getPointY()] == 'X' || 
						this.getMaze().getMaze()[this.getPosition().getPointX()+1][this.getPosition().getPointY()] == 'S')
					return;

				if (this.getMaze().getMaze()[this.getPosition().getPointX()+1][this.getPosition().getPointY()] == 'E')
				{
					this.setCh('F');
					this.getMaze().getMaze()[this.getPosition().getPointX()][this.getPosition().getPointY()] = ' ';
					this.moveDown();
					return;
				}
				this.getMaze().getMaze()[this.getPosition().getPointX()][this.getPosition().getPointY()] = ' ';
				this.moveDown();
			}
			
			if (ctrl == 4) // 4-ESQUERDA 
			{
				if (this.getMaze().getMaze()[this.getPosition().getPointX()][this.getPosition().getPointY()-1] == 'X' || 
						this.getMaze().getMaze()[this.getPosition().getPointX()][this.getPosition().getPointY()-1] == 'S')
					return;

				if (this.getMaze().getMaze()[this.getPosition().getPointX()][this.getPosition().getPointY()-1] == 'E')
				{
					this.setCh('F');
					this.getMaze().getMaze()[this.getPosition().getPointX()][this.getPosition().getPointY()] = ' ';
					this.moveLeft();
					return;
				}
				this.getMaze().getMaze()[this.getPosition().getPointX()][this.getPosition().getPointY()] = ' ';
				this.moveLeft();
			}
	}
}
