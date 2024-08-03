 package maze.logic;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class GameState 
{
	private boolean gameEnd = false;
	private MazeBuilder maze;
	private Sword espada= new Sword(getMaze());
	private Hero heroi= new Hero(getMaze());
	private Vector<Dragon> dragao = new Vector<Dragon>();
	
	
	/*Hero h = new Hero(1,3);
	Dragon d= new Dragon(3,3);
	Sword s= new Sword(3,1);
	Exit e =new Exit(1,4);*/

	/*public GameState(char[][] labirinto)
	{
		game=labirinto;
	}*/
	
	
	public GameState(MazeBuilder maze)
	{
		this.maze=maze;
	}
	
	
	public GameState(MazeBuilder maze, Sword espada, Hero heroi, Vector<Dragon> dragao)
	{
		this.maze=maze;
		this.espada=espada;
		this.heroi=heroi;
		this.dragao=dragao;
	}
	
	public void displayGameState(int numberDragons)
	{
		getEspada().displaySword(getMaze());
		getHeroi().displayHero(getMaze());
		
		for(int i=0; i<getDragao().size(); i++){
			getDragao().get(i).displayDragon(getMaze(), espada);
		}
	}
	
	public Sword getEspada()
	{
		return espada;
	}
	
	public Hero getHeroi()
	{
		return heroi;
	}
	
	public Vector<Dragon> getDragao()
	{
		return dragao;
	}
	
	public boolean getGameEnd()
	{
		return this.gameEnd;
	}
	
	public MazeBuilder getMaze() {
		
		return maze;
	}
	
	public void setHeroi(Hero heroi)
	{
		this.heroi=heroi;
	}
	
	public void setEspada(Sword espada)
	{
		this.espada=espada;
	}
	
	public void setDragao(Vector<Dragon> dragao)
	{
		this.dragao=dragao;
	}
	
	public void setMaze(MazeBuilder maze)
	{
		this.maze=maze;
	}
	
	/*public void displayGame()
	{
		getMaze()[h.getHeroPosition().getPointX()][h.getHeroPosition().getPointY()]=h.getCh();
		
		if (!h.isHeroArmed())
			game[s.getPosX()][s.getPosY()]= s.getCh();
		
		game[e.getPosX()][e.getPosY()]=e.getCh();
		
		if (d.isDead() == false)
			game[d.getPosX()][d.getPosY()]=d.getCh();
		
		for (int i = 0; i < game.length; i++)
		{
			for (int j = 0; j < game[i].length; j++) 
			{
				System.out.print(game[i][j] + " ");
			}
			System.out.print("\n");
		}
	}*/
	
	public Point getHeroPosition() 
	{ return heroi.getPosition();}
	
	public void moveHeroLeft()
	{
		heroi.setPosY(heroi.getPosY()-1);
	}
	
/*	public void moveUp(Block b)
	{
		b.setPosX(b.getPosX()-1);
	}
	
	public void moveDown(Block b)
	{
		b.setPosX(b.getPosX()+1);
	}
	
	public void moveLeft(Block b)
	{
		b.setPosY(b.getPosY()-1);
	}
	
	public void moveRight(Block b)
	{
		b.setPosY(b.getPosY()+1);
	}*/
	
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
	
	/*public boolean isDragonSleep()
	{
		return dragao.isSleep();
	}*/
	
	
	
	public boolean isSwordOnTopDragon(Dragon d)
	{
		if (d.getPosition().getPointX() == espada.getPosition().getPointX() && d.getPosition().getPointY() == espada.getPosition().getPointY())
			return true;
		return false;
	}
	
	public boolean heroArmed()
	{
		return heroi.isHeroArmed();
	}
	
	public void killDragon(Dragon d)
	{
		d.killDragon();
		d.setCh(' ');
	}
	
	public boolean dragonAdjacentHero(Dragon d)
	{
		if ( d.getPosX()+1 == heroi.getPosX() || 
				d.getPosX()-1 == heroi.getPosX() || 
				d.getPosY()-1 == heroi.getPosY() || 
				d.getPosY()+1 == heroi.getPosY())
			return true;
		return false;
	}
	
	public boolean dragonTopHero(Dragon d)
	{
		if (heroi.getHeroPosition().getPointX() == d.getPosition().getPointX() && heroi.getHeroPosition().getPointY() == d.getPosition().getPointY())
			return true;
		return false;
	}
	
	public boolean heroisDead()
	{
		return heroi.isDead();
	}
	
	public boolean dragonisDead(Dragon d)
	{
		return d.isDead();
	}
	
	public void moveHero(char mov)
	{
		if (mov == 'w' || mov == 'W')
		{
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()-1][heroi.getHeroPosition().getPointY()] == ' ')
			{
				this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()] = ' ';
				heroi.moveUp();
				
				for(int i=0; i<dragao.size();i++)
				{
				if (dragonAdjacentHero(dragao.elementAt(i)))
				{
					if (heroi.isHeroArmed())
					{
						dragao.elementAt(i).killBlock();
						this.getMaze().getMaze()[dragao.elementAt(i).getPosX()][dragao.elementAt(i).getPosY()] = ' ';
					}
					heroi.killBlock();
				}
				return;
				}
			}
			
			if ( this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()-1][heroi.getHeroPosition().getPointY()] == 'X')
				return;
			
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()-1][heroi.getHeroPosition().getPointY()] == 'E')
			{
				this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()] = ' ';
				heroi.moveUp();
				heroi.pickSword();
				
				for(int i=0; i<dragao.size();i++)
				{
				if (dragonAdjacentHero(dragao.elementAt(i)))
				{
					if (heroi.isHeroArmed())
					{
						dragao.elementAt(i).killBlock();
						this.getMaze().getMaze()[dragao.elementAt(i).getPosX()][dragao.elementAt(i).getPosY()] = ' ';
					}
					heroi.killBlock();
				}
				return;
				}
			}
			
			for(int i=0; i<dragao.size();i++)
			{
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()-1][heroi.getHeroPosition().getPointY()] == 'S' && !dragao.elementAt(i).isDead() == true)
			{
				gameEnd = false;
			}
			else
			{
				gameEnd = true;
				return;
				}
			}	
		}
		
		if ( mov == 's' || mov == 'S')
		{
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()+1][heroi.getHeroPosition().getPointY()] == ' ')
			{
				this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()] = ' ';
				heroi.moveDown();
				
				for(int i=0; i<dragao.size();i++)
				{
				if (dragonAdjacentHero(dragao.elementAt(i)))
				{
					if (heroi.isHeroArmed())
					{
						dragao.elementAt(i).killBlock();
						this.getMaze().getMaze()[dragao.elementAt(i).getPosX()][dragao.elementAt(i).getPosY()] = ' ';
					}
					heroi.killBlock();
				}
				return;
				}
			}
			
			if ( this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()+1][heroi.getHeroPosition().getPointY()] == 'X')
				return;
			
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()+1][heroi.getHeroPosition().getPointY()] == 'E')
			{
				this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()] = ' ';
				heroi.moveDown();
				heroi.pickSword();
				
				for(int i=0; i<dragao.size();i++)
				{
				if (dragonAdjacentHero(dragao.elementAt(i)))
				{
					if (heroi.isHeroArmed())
					{
						dragao.elementAt(i).killBlock();
						this.getMaze().getMaze()[dragao.elementAt(i).getPosX()][dragao.elementAt(i).getPosY()] = ' ';
					}
					heroi.killBlock();
				}
				return;
				}
			}
			
			for(int i=0; i<dragao.size();i++)
			{
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()-1][heroi.getHeroPosition().getPointY()] == 'S' && !dragao.elementAt(i).isDead() == true)
			{
				gameEnd = false;
			}
			else
			{
				gameEnd = true;
				return;
				}
			}
		}
		
		if (mov == 'a' || mov == 'A')
		{
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()-1]==' ')
			{
				this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()] = ' ';
				heroi.moveLeft();
				
				for(int i=0; i<dragao.size();i++)
				{
				if (dragonAdjacentHero(dragao.elementAt(i)))
				{
					if (heroi.isHeroArmed())
					{
						dragao.elementAt(i).killBlock();
						this.getMaze().getMaze()[dragao.elementAt(i).getPosX()][dragao.elementAt(i).getPosY()] = ' ';
					}
					heroi.killBlock();
				}
				return;
				}
			}
			
			if ( this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()-1] == 'X')
				return;
			
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()-1] == 'E')
			{
				this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()] = ' ';
				heroi.moveLeft();
				heroi.pickSword();
				
				for(int i=0; i<dragao.size();i++)
				{
				if (dragonAdjacentHero(dragao.elementAt(i)))
				{
					if (heroi.isHeroArmed())
					{
						dragao.elementAt(i).killBlock();
						this.getMaze().getMaze()[dragao.elementAt(i).getPosX()][dragao.elementAt(i).getPosY()] = ' ';
					}
					heroi.killBlock();
				}
				return;
				}
			}
			
			for(int i=0; i<dragao.size();i++)
			{
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()-1][heroi.getHeroPosition().getPointY()] == 'S' && !dragao.elementAt(i).isDead() == true)
			{
				gameEnd = false;
			}
			else
			{
				gameEnd = true;
				return;
				}
			}
		}
		
		if (mov == 'd' || mov == 'D')
		{
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()+1] == ' ')
			{
				this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()] = ' ';
				heroi.moveRight();
				
				for(int i=0; i<dragao.size();i++)
				{
				if (dragonAdjacentHero(dragao.elementAt(i)))
				{
					if (heroi.isHeroArmed())
					{
						dragao.elementAt(i).killBlock();
						this.getMaze().getMaze()[dragao.elementAt(i).getPosX()][dragao.elementAt(i).getPosY()] = ' ';
					}
					heroi.killBlock();
				}
				return;
				}
			}
			
			if ( this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()+1] == 'X')
				return;
			
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()+1] == 'E')
			{
				this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()][heroi.getHeroPosition().getPointY()] = ' ';
				heroi.moveRight();
				heroi.pickSword();
				
				for(int i=0; i<dragao.size();i++)
				{
				if (dragonAdjacentHero(dragao.elementAt(i)))
				{
					if (heroi.isHeroArmed())
					{
						dragao.elementAt(i).killBlock();
						this.getMaze().getMaze()[dragao.elementAt(i).getPosX()][dragao.elementAt(i).getPosY()] = ' ';
					}
					heroi.killBlock();
				}
				return;
				}
			}
			
			for(int i=0; i<dragao.size();i++)
			{
			if (this.getMaze().getMaze()[heroi.getHeroPosition().getPointX()-1][heroi.getHeroPosition().getPointY()] == 'S' && !dragao.elementAt(i).isDead() == true)
			{
				gameEnd = false;
			}
			else
			{
				gameEnd = true;
				return;
				}
			}
		}
	}
	
	public static void main(String[] args) 
	{
		
		System.out.print("1");
	}
	
}