package maze.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.JTextField;

import maze.basic.Maze1st;
import maze.logic.GameState;
import maze.logic.MazeBuilder;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.util.Scanner;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class window 
{
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	public boolean willDragonSleep;
	private GameState gState;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					window window = new window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public window() 
	{
		initialize();
		char[][] maze = new char[7][7];
		GameState g = new GameState(maze);
		this.gState = g;
	}

	

	
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 585, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblDimensaoDoLabirinto = new JLabel("Dimensao do labirinto");
		lblDimensaoDoLabirinto.setBounds(10, 41, 115, 14);
		lblDimensaoDoLabirinto.setAlignmentY(Component.TOP_ALIGNMENT);
		lblDimensaoDoLabirinto.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(lblDimensaoDoLabirinto);

		textField = new JTextField();
		textField.setBounds(131, 38, 60, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Numero de Dragoes");
		lblNewLabel.setBounds(10, 80, 95, 14);
		frame.getContentPane().add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(131, 77, 60, 20);

		frame.getContentPane().add(textField_1);
		textField.setText("11");
		textField_1.setColumns(10);
		textField_1.setText("1");

		JLabel lblTipoDeDragoes = new JLabel("Tipo de Dragoes");
		lblTipoDeDragoes.setBounds(10, 115, 95, 14);
		frame.getContentPane().add(lblTipoDeDragoes);

		JLabel label = new JLabel("Pode gerar novo labirinto!");
		label.setBounds(10, 365, 313, 34);
		frame.getContentPane().add(label);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBounds(10, 140, 313, 214);
		frame.getContentPane().add(textArea);

		
		JButton btnTerminarPrograma = new JButton("Terminar programa");
		btnTerminarPrograma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnTerminarPrograma.setBounds(361, 64, 186, 47);
		frame.getContentPane().add(btnTerminarPrograma);

		JButton btnCima = new JButton("Cima");
		btnCima.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (gState == null)
					return;
				
				if (willDragonSleep == true)
					gState.randomMoveDragonSleep();
				else
					gState.randomMoveDragon();
				
				gState.moveHero('w');

				if (gState.gameEnd == true)
				{
					textArea.setText("Perdeu o jogo.");
					return;
				}
					
				if (gState.isSwordOnTopDragon()) 
				{
					gState.setDragonChar('F');
				}

				if (gState.isDragonSleep()) 
				{
					gState.setDragonChar('L');
				}

				if (!gState.isSwordOnTopDragon() && !gState.isDragonSleep())
					gState.setDragonChar('D');

				if ((gState.dragonAdjacentHero() == true || gState.dragonTopHero() == true) && gState.heroArmed() == true)
					gState.killDragon();

				if ((gState.dragonAdjacentHero() == true || gState.dragonTopHero() == true) && gState.heroArmed() == false) 
				{
					textArea.setText("Perdeu o jogo.");
					return;
				}
				textArea.setText(gState.displayGame());
			}
		});
		btnCima.setBounds(401, 141, 89, 23);
		frame.getContentPane().add(btnCima);
		

		JButton btnBaixo = new JButton("Baixo");
		btnBaixo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (gState == null)
					return;
				
				if (willDragonSleep == true)
					gState.randomMoveDragonSleep();
				else
					gState.randomMoveDragon();
				
				gState.moveHero('s');

				if (gState.gameEnd == true)
				{
					textArea.setText("Perdeu o jogo.");
					return;
				}
					
				if (gState.isSwordOnTopDragon()) 
				{
					gState.setDragonChar('F');
				}

				if (gState.isDragonSleep()) 
				{
					gState.setDragonChar('L');
				}

				if (!gState.isSwordOnTopDragon() && !gState.isDragonSleep())
					gState.setDragonChar('D');

				if ((gState.dragonAdjacentHero() == true || gState.dragonTopHero() == true) && gState.heroArmed() == true)
					gState.killDragon();

				if ((gState.dragonAdjacentHero() == true || gState.dragonTopHero() == true) && gState.heroArmed() == false) 
				{
					textArea.setText("Perdeu o jogo.");
					return;
				}
				textArea.setText(gState.displayGame());
			}
		});
		btnBaixo.setBounds(401, 236, 89, 23);
		frame.getContentPane().add(btnBaixo);

		JButton btnEsquerda = new JButton("Esquerda");
		btnCima.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (gState == null)
					return;
				
				if (willDragonSleep == true)
					gState.randomMoveDragonSleep();
				else
					gState.randomMoveDragon();
				
				gState.moveHero('a');

				if (gState.gameEnd == true)
				{
					textArea.setText("Perdeu o jogo.");
					return;
				}
					
				if (gState.isSwordOnTopDragon()) 
				{
					gState.setDragonChar('F');
				}

				if (gState.isDragonSleep()) 
				{
					gState.setDragonChar('L');
				}

				if (!gState.isSwordOnTopDragon() && !gState.isDragonSleep())
					gState.setDragonChar('D');

				if ((gState.dragonAdjacentHero() == true || gState.dragonTopHero() == true) && gState.heroArmed() == true)
					gState.killDragon();

				if ((gState.dragonAdjacentHero() == true || gState.dragonTopHero() == true) && gState.heroArmed() == false) 
				{
					textArea.setText("Perdeu o jogo.");
					return;
				}
				textArea.setText(gState.displayGame());
			}
		});
		btnEsquerda.setBounds(338, 188, 89, 23);
		frame.getContentPane().add(btnEsquerda);

		JButton btnDireita = new JButton("Direita");
		btnCima.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (gState == null)
					return;
				
				if (willDragonSleep == true)
					gState.randomMoveDragonSleep();
				else
					gState.randomMoveDragon();
				
				gState.moveHero('d');

				if (gState.gameEnd == true)
				{
					textArea.setText("Perdeu o jogo.");
					return;
				}
					
				if (gState.isSwordOnTopDragon()) 
				{
					gState.setDragonChar('F');
				}

				if (gState.isDragonSleep()) 
				{
					gState.setDragonChar('L');
				}

				if (!gState.isSwordOnTopDragon() && !gState.isDragonSleep())
					gState.setDragonChar('D');

				if ((gState.dragonAdjacentHero() == true || gState.dragonTopHero() == true) && gState.heroArmed() == true)
					gState.killDragon();

				if ((gState.dragonAdjacentHero() == true || gState.dragonTopHero() == true) && gState.heroArmed() == false) 
				{
					textArea.setText("Perdeu o jogo.");
					return;
				}
				textArea.setText(gState.displayGame());
			}
		});
		btnDireita.setBounds(470, 188, 89, 23);
		frame.getContentPane().add(btnDireita);

		JComboBox comboBox = new JComboBox();
		comboBox.addItem(makeObj("Movimento aleatorio"));
		comboBox.addItem(makeObj("Movimento aleatorio intercalado com dormir"));
		comboBox.setBounds(131, 112, 192, 20);
		frame.getContentPane().add(comboBox);

		JButton btnGerarNovoLabirinto = new JButton("Gerar novo labirinto");
		btnGerarNovoLabirinto.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//MazeBuilder mbuilder = new MazeBuilder(Integer.parseInt(textField.getText()));
				GameState g = new GameState(mbuilder.getMaze());
				gState = g;
				textArea.setText(g.displayGame());
				label.setText("Pode jogar!");
				
				if (comboBox.getSelectedItem().equals("Movimento aleatorio"))
					willDragonSleep = false;
				
				if (comboBox.getSelectedItem().equals("Movimento aleatorio intercalado com dormir"))
					willDragonSleep = true;
						
			}
		});
		btnGerarNovoLabirinto.setBounds(361, 11, 186, 47);
		frame.getContentPane().add(btnGerarNovoLabirinto);
		
	}

	
	private Object makeObj(String string) {
		return string;
	}
}
