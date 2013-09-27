import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Display extends JPanel implements ActionListener
	{
		LinkedList<Fruit> field = new LinkedList<Fruit>();
		LinkedList<Fruit> fallingQueue = new LinkedList<Fruit>();
		Fruit[][] matrix = new Fruit[10][10];
		
		Timer timer;
		int counter = 0;
		
		
		Display(int h, int w)
		{
			super();
			setSize(h, w);
			setBackground(Color.white);
			
			timer = new Timer(17, this);	
			timer.start();
		}
		
		public void createNewFruit(int row)
		{
			Fruit f = new Fruit(row);
			field.add(f);
			fallingQueue.add(f);
		}
		
		public void drawAllFruit(Graphics g)
		{
			for (Fruit f : field)
			{
				if (f!=null)
				{
					g.setColor(f.type);
					g.fillRect(f.x, f.y, 40, 40);
				}
			}
		}
		
		public void paintComponent(Graphics g)
		{			
			super.paintComponent(g);

			drawAllFruit(g);
		}
		
		public void actionPerformed(ActionEvent arg0) 
		{
			if (counter%50 == 0)
				createNewFruit((int)(Math.random()*10));
			
			counter ++;
			
			LinkedList<Fruit> doneFalling = new LinkedList<Fruit>();
			
			for (Fruit f : fallingQueue)
			{
				if (f.fall(matrix))
					doneFalling.add(f);
			}
			
			for (Fruit f : doneFalling)
			{
				fallingQueue.remove(f);
				//field.remove(f);
			}

			repaint();

		}
	}