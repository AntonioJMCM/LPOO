package maze.cli;
import java.util.Scanner;

import maze.basic.Maze1st;
import maze.logic.*;

public class Game 
{
	public static void main(String[] args) 
	{
		System.out.println("Qual a estrategia de movimentacao dragao pretende?");
		System.out.println("1 - Dragao com movimentacao aleatoria intercalada com adormecimento");
		System.out.println("2 - Dragao movimentacao aleatoria");
		System.out.println("3 - Dragao estatico\n");
		System.out.println(">> ");
		
		
		Scanner in = new Scanner(System.in);
		int num = in.nextInt();
		
		if ( num == 3) // BASICO
		{
			Maze1st m1st = new Maze1st();
			m1st.main(args);
			return;
		}
		
		MazeBuilder mbuilder = new MazeBuilder();//-----//
		char[][] maze = mbuilder.getMaze();
		GameState g = new GameState(maze);
		g.displayGame();
		
		if (num == 1) // DRAGAO DORME
		{
			while (g.gameEnd == false)
			{
				Scanner reader = new Scanner(System.in);
				char x = reader.next().charAt(0);
				
				g.randomMoveDragonSleep();
				g.moveHero(x);
				
				if (g.gameEnd == true)
					return;
				
				if (g.isSwordOnTopDragon())
				{
					g.setDragonChar('F');
				}
				
				if (g.isDragonSleep())
				{
					g.setDragonChar('L');
				}
				
				if (!g.isSwordOnTopDragon() && ! g.isDragonSleep())
					g.setDragonChar('D');
				
				if ((g.dragonAdjacentHero() == true || g.dragonTopHero() == true) && g.heroArmed() == true)
					g.killDragon();
				
				g.displayGame();
				
				if ((g.dragonAdjacentHero() == true || g.dragonTopHero() == true) && g.heroArmed() == false)
				{
					System.out.print("\n");
					System.out.print("\n");
					System.out.print("\n");
					System.out.print("Perdeu o jogo.");
					return;
				}
				
				}	
		}
		
		if (num == 2) // DRAGAO MOVE-SE ALEATORIAMENTE
		{
			//g.StartGame(num);
		}	
	}
}
