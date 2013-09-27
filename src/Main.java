import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.*;

public class Main extends JFrame
{
	public static void main(String[] args)
	{
		Display d = new Display(400, 400);
		Main m = new Main(d);

	}
	
	public Main(Display d)
	{
		this.add(d);
		this.pack();
		setSize(400, 400);
		this.setVisible(true);
		
	}
	
	public static class Display extends JPanel implements ActionListener
	{
		ArrayList<Fruit> field = new ArrayList<Fruit>();
		Queue<Fruit> fallingQueue = new LinkedList<Fruit>();
		
		Timer timer;
		int counter = 0;
		
		
		Display(int h, int w)
		{
			super();
			setSize(h, w);
			
			createNewFruit(3);
			timer = new Timer(5, this);	
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
					g.drawRect(f.x, f.y, 40, 40);
			}
		}
		
		public void paintComponent(Graphics g)
		{			
			super.paintComponent(g);

			drawAllFruit(g);
		}
		
		public void actionPerformed(ActionEvent arg0) 
		{
			if (counter%100 == 0)
				createNewFruit((int)(Math.random()*8));
			
			counter ++;
			
			ArrayList<Fruit> doneFalling = new ArrayList<Fruit>();
			
			for (Fruit f : fallingQueue)
			{
				if (f.fall())
					doneFalling.add(f);
			}
			
			for (Fruit f : doneFalling)
				fallingQueue.remove(f);

			repaint();

		}
	}
}
