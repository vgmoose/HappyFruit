import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class FruitGame extends JPanel implements ActionListener, MouseListener
	{
		LinkedList<Fruit> field = new LinkedList<Fruit>();
		LinkedList<Fruit> fallingQueue = new LinkedList<Fruit>();
		Fruit[][] matrix = new Fruit[10][10];
		
		Timer timer;
		int counter = 0;
		
		
		FruitGame(int h, int w)
		{
			super();
			setSize(h, w);
			setBackground(Color.white);
			
			timer = new Timer(17, this);	
			timer.start();
			this.addMouseListener(this);
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

		@Override
		public void mouseClicked(MouseEvent arg0) 
		{
			int x = arg0.getX()/40;
			int y = arg0.getY()/40;
			
			//matrix[x/40][y/40].type = Color.black;
			removeFruit(matrix[x][y], x, y);			
			
		}

		private void startFalling(Fruit fruit, int x, int y) {
			fallingQueue.add(fruit);
			matrix[x][y] = null;
			
			if (matrix[x][y-1] != null)
				startFalling(matrix[x][y-1], x, y-1);
			
		}

		private void removeFruit(Fruit fruit, int x, int y) {
			field.remove(fruit);
			matrix[x][y] = null;
			
			
			if (matrix[x][y-1] != null)
				startFalling(matrix[x][y-1], x, y-1);
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}