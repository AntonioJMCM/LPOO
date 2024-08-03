package maze.basic;
import java.util.Scanner;

public class Maze1st 
{
	public static char[][] maze = new char[][] { 
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', 'D', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' }, 
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', 'E', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }, 
			};
			
	private boolean dDead = false;
	public boolean gameEnd = false;
	private boolean hArmed = false;
	private boolean hDead = false;

	public Maze1st() {
	}

	public String displayMaze() 
	{
		String blet = "";
		
		for (int i = 0; i < maze.length; i++) 
		{
			for (int j = 0; j < maze[i].length; j++) 
			{
				blet += (maze[i][j] + " ");
			}
			blet += "\n";
		}
		return blet;
	}

	public int heroPosXY(char pos) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if ((maze[i][j] == 'A' || maze[i][j] == 'H') && pos == 'y')
					return j;

				if ((maze[i][j] == 'A' || maze[i][j] == 'H') && pos == 'x')
					return i;
			}
		}
		return -1;
	}

	public void heroMove(char mov) 
	{
		if (mov == 'w' || mov == 'W') 
		{
			int x = heroPosXY('x');
			int y = heroPosXY('y');

			if (maze[x - 1][y] == 'X')
				return;

			if (hArmed == true) 
			{
				maze[x - 1][y] = 'A';
				maze[x][y] = ' ';
				return;
			}
			else
			{
				maze[x - 1][y] = 'H';
				maze[x][y] = ' ';
			}
		}

		if (mov == 'd' || mov == 'D') 
		{
			int x = heroPosXY('x');
			int y = heroPosXY('y');

			if ((y + 1) != -1)
			{
				if (maze[x][y + 1] == 'S' && dDead == true) 
				{
					System.out.print("\n");
					System.out.print("\n");
					System.out.print("\n");
					System.out.print("GANHOU O JOGO!");
					gameEnd = true;
					return;
				}
				else
					if (maze[x][y + 1] == 'S' && dDead == false)
						return;

				if (maze[x][y + 1] != 'X') 
				{
					if (hArmed == true) 
					{
						maze[x][y + 1] = 'A';
						maze[x][y] = ' ';
						return;
					} 
					else 
					{
						maze[x][y + 1] = 'H';
						maze[x][y] = ' ';
						return;
					}
				}
			}
		}

		if (mov == 'a' || mov == 'A') {
			int x = heroPosXY('x');
			int y = heroPosXY('y');

			if ((y - 1) != -1) {
				if (maze[x][y - 1] != 'X') {
					if (hArmed == true) {
						maze[x][y - 1] = 'A';
						maze[x][y] = ' ';
						return;
					} else {
						maze[x][y - 1] = 'H';
						maze[x][y] = ' ';
						return;
					}
				}
			}
		}

		if (mov == 's' || mov == 'S') 
		{
			int x = heroPosXY('x');
			int y = heroPosXY('y');

			if (maze[x + 1][y] != 'X') 
			{
				if (maze[x + 1][y] == 'E') // EQUIPOU A ARMA!
				{
					maze[x + 1][y] = 'A';
					maze[x][y] = ' ';
					hArmed = true;
					return;
				}

				if (hArmed == true) 
				{
					maze[x + 1][y] = 'A';
					maze[x][y] = ' ';
					return;
				} else {
					maze[x + 1][y] = 'H';
					maze[x][y] = ' ';
					return;
				}
			}
		}
	}

	public boolean adjacentDragonHero() {
		int x = heroPosXY('x');
		int y = heroPosXY('y');

		if (maze[x + 1][y] == 'D' || maze[x - 1][y] == 'D' || maze[x][y + 1] == 'D' || maze[x][y - 1] == 'D')
			return true;
		else
			return false;
	}

	public int dragonKillHero() // 1 = dragao matou hero; 0 = hero matou dragao // ; -1 = nao aconteceu nada
	{
		if (adjacentDragonHero() == true && hArmed == true)
			return 0;

		if (adjacentDragonHero() == true && hArmed == false)
			return 1;

		return -1;
	}

	public void loopGame()
	{		
			Scanner reader = new Scanner(System.in);
			char c = reader.next().charAt(0);
			heroMove(c);
			
			if (gameEnd == true)
				return;
			displayMaze();
			
			if (gameEnd == true)
				return;
			
			if (dragonKillHero() == 1)
			{
				return;
			}
			
			if (dragonKillHero() == 0)
				dDead = true;
		}
	

	public static void main(String[] args) 
	{
		Maze1st m = new Maze1st();
		
		while (m.gameEnd == false)
		{
			m.displayMaze();
			m.loopGame();
		}
	}
}