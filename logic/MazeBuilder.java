package maze.logic;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class MazeBuilder implements IMazeBuilder 
{
	private char[][] maze;
	private int dim;
	
	public MazeBuilder(int size) 
	{
		maze = buildRandomMaze(size); // exemplo
	};
	
	public int randInt(int min, int max) {

	    Random rand = new Random();

	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public MazeBuilder(char[][] maze)
	{
		this.maze=maze;
	}
	
	public char[][] buildRandomMaze(int size) throws IllegalArgumentException{

		setDim(size);
		this.setMaze(new char[size][size]);
		
		char[][] visitedCell= new char[(size-1)/2][(size-1)/2];
		Stack<Point> pahtHist = new Stack<Point>();
		
		for (int i = 0 ; i < visitedCell.length; i++)
		{
			for (int j = 0; j < visitedCell[i].length; j++)
				visitedCell[i][j]='.';
		}
		
		
		for (int i = 0; i < size; i++) 
		{
			for (int j = 0; j < size; j++)
				maze[i][j] = 'X';
		}

		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				if ((i % 2 != 0) && (j % 2 != 0))
					maze[i][j] = ' ';
			}
		}
		
		int exitX=0;
		int exitY=0;		
		
		int rand= randInt(0,3);
		
		if(rand==0){
			exitY=0;
			exitX=randInt(1,size-2);
		}
		
		if(rand==1){
			exitY=size-1;
			exitX=randInt(1,size-2);
		}
		
		if(rand==2){
			exitX=0;
			exitY=randInt(1,size-2);
		}
		
		if(rand==3){
			exitX=size-1;
			exitY=randInt(1,size-2);;
		}


		Point cellNextToExit = new Point(exitX, exitY);
		if (exitX == 0)
			cellNextToExit.setPointX(cellNextToExit.getPointX() + 1);
		else if (exitX == size - 1)
			cellNextToExit.setPointX(cellNextToExit.getPointX() - 1);
		else if (exitY == 0)
			cellNextToExit.setPointY(cellNextToExit.getPointY() + 1);
		else
			cellNextToExit.setPointY(cellNextToExit.getPointY() - 1);

		int guideCellX = (cellNextToExit.getPointX() - 1) / 2;
		int guideCellY = (cellNextToExit.getPointY() - 1) / 2;
		Point guideCell = new Point(guideCellX, guideCellY);

		visitedCell[guideCell.getPointY()][guideCell.getPointX()] ='+';
		pahtHist.push(new Point(guideCell.getPointX(), guideCell.getPointY()));

		while (!pahtHist.empty()) {
			if ((guideCell.getPointX()+1 >= (size-1)/2 || visitedCell[guideCell.getPointY()][guideCell.getPointX()+1] == '+')
					&& (guideCell.getPointX()-1 < 0 || visitedCell[guideCell.getPointY()][guideCell.getPointX()-1] == '+') &&
					(guideCell.getPointY()+1 >= (size-1)/2 || visitedCell[guideCell.getPointY()+1][guideCell.getPointX()] == '+')
					&& (guideCell.getPointY()-1 < 0 || visitedCell[guideCell.getPointY()-1][guideCell.getPointX()] == '+')) {

				pahtHist.pop();

				if (pahtHist.empty())
					break;
				else
					guideCell = pahtHist.peek();
			}

			Random r = new Random();
			switch (r.nextInt(4)) {
			// esquerda
			case 0:
				if (guideCell.getPointY()-1 >= 0
				&& visitedCell[guideCell.getPointY()-1][guideCell.getPointX()] == '.') {
					getMaze()[guideCell.getPointY()*2][guideCell.getPointX()*2+1] = ' ';

					guideCell.setPointY(guideCell.getPointY()-1);
					pahtHist.push(new Point(guideCell.getPointX(), guideCell.getPointY()));

					visitedCell[guideCell.getPointY()][guideCell.getPointX()] = '+';
				}
				break;
				// baixo
			case 1:
				if (guideCell.getPointX()+1 < (size-1)/2 && visitedCell[guideCell.getPointY()][guideCell.getPointX()+1] == '.'){
					getMaze()[guideCell.getPointY()*2+1][(guideCell.getPointX()+1)*2] = ' ';

					guideCell.setPointX(guideCell.getPointX()+1);
					pahtHist.push(new Point(guideCell.getPointX(), guideCell.getPointY()));

					visitedCell[guideCell.getPointY()][guideCell.getPointX()] = '+';
				}
				break;
				// direita
			case 2:
				if (guideCell.getPointY()+1 < (size-1)/2 && visitedCell[guideCell.getPointY()+1][guideCell.getPointX()] == '.'){
					getMaze()[(guideCell.getPointY()+1)*2][guideCell.getPointX()*2+1] = ' ';

					guideCell.setPointY(guideCell.getPointY()+1);
					pahtHist.push(new Point(guideCell.getPointX(), guideCell.getPointY()));

					visitedCell[guideCell.getPointY()][guideCell.getPointX()] = '+';
				}
				break;
				// cima
			case 3:
				if (guideCell.getPointX()-1 >= 0 && visitedCell[guideCell.getPointY()][guideCell.getPointX()-1] == '.'){
					getMaze()[guideCell.getPointY()*2+1][guideCell.getPointX()*2] = ' ';

					guideCell.setPointX(guideCell.getPointX()-1);
					pahtHist.push(new Point(guideCell.getPointX(), guideCell.getPointY()));

					visitedCell[guideCell.getPointY()][guideCell.getPointX()] = '+';
				}
				break;
			}
		}

		return maze;
	}

	
	
	public char[][] getMaze()
	{
		return maze;
	}
	
	public int getDim()
	{
		return dim;
	}
	
	
	public void setMaze(char[][] maze)
	{
		this.maze=maze;
	}
	
	public void setDim(int dim)
	{
		this.dim=dim;
	}
	
	public char[][] Default_maze(){
		setDim(10);
		char[][] maze = {
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','S'},
				{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
		return maze;
	}
}