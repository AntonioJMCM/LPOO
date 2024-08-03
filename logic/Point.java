package maze.logic;

public class Point 
{
	private int x;
	private int y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getPointX()
	{
		return this.x;
	}
	
	public int getPointY()
	{
		return this.y;
	}
	
	public void setPointX(int newx)
	{
		this.x = newx;
	}
	
	public void setPointY(int newy)
	{
		this.y = newy;
	}
	
	public boolean equals(Point p)
	{
		return (getPointX() == p.getPointX() && getPointY() == p.getPointY());
	}
}
