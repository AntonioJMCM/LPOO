package maze.logic;

public interface IMazeBuilder 
{
	public char[][] buildRandomMaze(int size) throws IllegalArgumentException;
}