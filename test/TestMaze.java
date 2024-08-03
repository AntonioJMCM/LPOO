package maze.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import maze.logic.Dragon;
import maze.logic.GameState;
import maze.logic.Hero;
import maze.logic.MazeBuilder;
import maze.logic.Point;
import maze.logic.Sword;

public class TestMaze //WithStaticDragon
{
	char[][] m1 = { 
			{ 'X', 'X', 'X', 'X', 'X' }, 
			{ 'X', ' ', ' ', 'H', 'S' }, 
			{ 'X', ' ', 'X', ' ', 'X' },
			{ 'X', 'E', ' ', 'D', 'X' }, 
			{ 'X', 'X', 'X', 'X', 'X' }
			};

	@Test
	public void testMoveHeroToFreeCell() // a) 
	{
		MazeBuilder maze = new MazeBuilder(m1);
		GameState game= new GameState(maze);
		Hero h= new Hero(maze,1,3);
		Sword espada= new Sword(maze,3,1);
		Vector<Dragon> dragao= new Vector<Dragon>();
		Dragon d= new Dragon(maze,3,3);
		dragao.addElement(d);
		game.setHeroi(h);
		game.setDragao(dragao);
		game.setEspada(espada);
		Point p = new Point(1, 3);
		
		
		
		assertEquals(p.getPointX(), h.getPosX());
		assertEquals(p.getPointY(), h.getPosY());
		assertEquals(game.getHeroPosition().getPointX(), h.getPosX());
		assertEquals(game.getHeroPosition().getPointY(), h.getPosY());
		
		h.moveLeft();
		p.setPointY(2);
		
		assertEquals(p.getPointX(), h.getPosX());
		assertEquals(p.getPointY(), h.getPosY());
		assertEquals(game.getHeroPosition().getPointX(), h.getPosX());
		assertEquals(game.getHeroPosition().getPointY(), h.getPosY());
	}
	
	@Test
	public void testMoveWall() //b)
	{
		MazeBuilder maze = new MazeBuilder(m1);
		GameState game= new GameState(maze);
		Hero h= new Hero(maze,1,3);
		Sword espada= new Sword(maze,3,1);
		Vector<Dragon> dragao= new Vector<Dragon>();
		Dragon d= new Dragon(maze,3,3);
		dragao.addElement(d);
		game.setHeroi(h);
		game.setDragao(dragao);
		game.setEspada(espada);
		Point p = new Point(1, 3);
		
		assertEquals(p.getPointX(), h.getPosX());
		assertEquals(p.getPointY(),h.getPosY());
		assertEquals(game.getHeroPosition().getPointX(), h.getPosX());
		assertEquals(game.getHeroPosition().getPointY(), h.getPosY());
		
		h.moveLeft();
		
		//assertEquals(p.getPointX(), maze.getHeroPosition().getPointX());
		//assertEquals(p.getPointY(), maze.getHeroPosition().getPointY());
		assertEquals(game.getHeroPosition().getPointX(), h.getPosX());
		assertEquals(game.getHeroPosition().getPointY(), h.getPosY());
	}
	
	@Test //c)
	public void testCatchSword()
	{
		MazeBuilder maze = new MazeBuilder(m1);
		GameState game= new GameState(maze);
		Hero h= new Hero(maze,1,3);
		Sword espada= new Sword(maze,3,1);
		Vector<Dragon> dragao= new Vector<Dragon>();
		Dragon d= new Dragon(maze,3,3);
		dragao.addElement(d);
		game.setHeroi(h);
		game.setDragao(dragao);
		game.setEspada(espada);
		Point p = new Point(1, 3);
		
		assertFalse(h.isHeroArmed());
		
		h.moveLeft();
		h.moveLeft();
		h.moveDown();
		h.moveDown();
		
		assertTrue(h.isHeroArmed());
	}

	@Test
	public void testHeroDies() // d)
	{
		MazeBuilder maze = new MazeBuilder(m1);
		GameState game= new GameState(maze);
		Hero h= new Hero(maze,1,3);
		Sword espada= new Sword(maze,3,1);
		Vector<Dragon> dragao= new Vector<Dragon>();
		Dragon d= new Dragon(maze,3,3);
		dragao.addElement(d);
		game.setHeroi(h);
		game.setDragao(dragao);
		game.setEspada(espada);
		Point p = new Point(1, 3);
		
		assertFalse(h.isHeroArmed());
		h.moveDown();;
		assertTrue(h.isDead());
	}
	
	@Test // e)
	public void testHeroKillDragon()
	{
		MazeBuilder maze = new MazeBuilder(m1);
		GameState game= new GameState(maze);
		Hero h= new Hero(maze,1,3);
		Sword espada= new Sword(maze,3,1);
		Vector<Dragon> dragao= new Vector<Dragon>();
		Dragon d= new Dragon(maze,3,3);
		dragao.addElement(d);
		game.setHeroi(h);
		game.setDragao(dragao);
		game.setEspada(espada);
		Point p = new Point(1, 3);
		
		assertFalse(h.isHeroArmed());
		
		h.moveLeft();
		h.moveLeft();
		h.moveDown();
		h.moveDown();
		h.moveRight();
		
				
		assertTrue(d.isDead());
	}
	
	@Test
	public void testEndGame() //f)
	{
		MazeBuilder maze = new MazeBuilder(m1);
		GameState game= new GameState(maze);
		Hero h= new Hero(maze,1,3);
		Sword espada= new Sword(maze,3,1);
		Vector<Dragon> dragao= new Vector<Dragon>();
		Dragon d= new Dragon(maze,3,3);
		dragao.addElement(d);
		game.setHeroi(h);
		game.setDragao(dragao);
		game.setEspada(espada);
		Point p = new Point(1, 3);
		
		assertFalse(h.isHeroArmed());
		
		h.moveLeft();
		h.moveLeft();
		h.moveDown();
		h.moveDown();
		h.moveRight();
		
		assertTrue(d.isDead());
		
		h.moveRight();
		h.moveUp();
		h.moveUp();
		h.moveRight();
		
		assertTrue(game.getGameEnd());
	}
	
	@Test
	public void testExitWithoutSword()
	{
		MazeBuilder maze = new MazeBuilder(m1);
		GameState game= new GameState(maze);
		Hero h= new Hero(maze,1,3);
		Sword espada= new Sword(maze,3,1);
		Vector<Dragon> dragao= new Vector<Dragon>();
		Dragon d= new Dragon(maze,3,3);
		dragao.addElement(d);
		game.setHeroi(h);
		game.setDragao(dragao);
		game.setEspada(espada);
		Point p = new Point(1, 3);
		
		
		h.moveRight();
		
		assertFalse(h.isHeroArmed());
		
	}
	
	@Test
	public void testExitWithSwordDragonAlive()
	{
		MazeBuilder maze = new MazeBuilder(m1);
		GameState game= new GameState(maze);
		Hero h= new Hero(maze,1,3);
		Sword espada= new Sword(maze,3,1);
		Vector<Dragon> dragao= new Vector<Dragon>();
		Dragon d= new Dragon(maze,3,3);
		dragao.addElement(d);
		game.setHeroi(h);
		game.setDragao(dragao);
		game.setEspada(espada);
		Point p = new Point(1, 3);
		
		h.moveLeft();
		h.moveLeft();
		h.moveDown();
		h.moveDown();
		
		assertTrue(h.isHeroArmed());
		
		h.moveUp();
		h.moveUp();
		h.moveRight();
		h.moveRight();
		
		assertTrue(h.isHeroArmed());
		assertFalse(game.getGameEnd());
	}
	
	@Test
	public void testDisplayGame()
	{
		MazeBuilder maze = new MazeBuilder(m1);
		GameState game= new GameState(maze);
		Hero h= new Hero(maze,1,3);
		Sword espada= new Sword(maze,3,1);
		Vector<Dragon> dragao= new Vector<Dragon>();
		Dragon d= new Dragon(maze,3,3);
		dragao.addElement(d);
		game.setHeroi(h);
		game.setDragao(dragao);
		game.setEspada(espada);
		Point p = new Point(1, 3);
		
		game.displayGameState(1);
	}
}