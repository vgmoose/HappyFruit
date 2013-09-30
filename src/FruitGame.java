import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class FruitGame extends JPanel implements ActionListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 1L;
	LinkedList<Fruit> field = new LinkedList<Fruit>();
	LinkedList<Fruit> fallingQueue = new LinkedList<Fruit>();
	Fruit[][] matrix = new Fruit[10][10];

	Timer timer;
	int counter = 0;

	int mouseCoorX, mouseCoorY;
	private Fruit chosenFruit;
	
	FruitGame(int h, int w)
	{
		super();
		setSize(h, w);
		setBackground(Color.white);

		timer = new Timer(17, this);	
		timer.start();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
//		for (int x=0; x<10; x++)
//			if (x!=5)
//				createNewFruit(x);
	}	

	public void createNewFruit(int row)
	{
		Fruit f = new Fruit(row);
		field.add(f);
		fallingQueue.add(f);
	}

	public void drawAllFruit(Graphics g)
	{
		for( Iterator<Fruit> it = field.descendingIterator(); it.hasNext(); )
		{
			Fruit f = it.next();

			g.setColor(f.type);

			g.fillRect(f.x, f.y, 40, 40);
		}
	}

	public void paintComponent(Graphics g)
	{			
		super.paintComponent(g);

		drawAllFruit(g);
	}
	
	public void moveRowLeft(int row)
	{
		Fruit temp = matrix[0][row];

		for (int x=0; x<10; x++)
		{
			if (matrix[x][row] != null)
			{
				matrix[x][row].moveHorizontal(-40);
			}
			
			startFalling(matrix[x][row], x, row);


		}

	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if (counter%50 == 0)
		{
			createNewFruit((int)(Math.random()*10));

		}

		counter++;

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


	public void mouseClicked(MouseEvent arg0) 
	{

	}

	private void startFalling(Fruit fruit, int x, int y) 
	{

		if (matrix[x][y] != null)
		{
			fallingQueue.add(fruit);
			matrix[x][y] = null;
		}

		if (matrix[x][y-1] != null)
			startFalling(matrix[x][y-1], x, y-1);

	}

	private void removeFruit(Fruit fruit, int x, int y) {
		field.remove(fruit);
		matrix[x][y] = null;

		startFalling(matrix[x][y-1], x, y-1);
	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void mousePressed(MouseEvent arg0) 
	{
		mouseCoorX = arg0.getX();
		mouseCoorY = arg0.getY();
		
		moveRowLeft(arg0.getY()/40);
		
		
	}


	public void mouseReleased(MouseEvent arg0) {


	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
//
//		int mouseDiffX = arg0.getX() - mouseCoorX;
//		int mouseDiffY = arg0.getY() - mouseCoorY;
//		
//		if (Math.abs(mouseDiffX) > Math.abs(mouseDiffY))
//			mouseDiffY = 0;
//		else
//			mouseDiffX = 0;
//		
//		for (int x=0; x<10; x++)
//		{
//			if (mouseDiffY == 0)
//			{
//				if (matrix[x][mouseCoorY/40] != null)
//				{
//					matrix[x][mouseCoorY/40].applyDiff(mouseDiffX, mouseDiffY, matrix);
//					startFalling(matrix[x][mouseCoorY/40], x, mouseCoorY/40);
//				}
//			}
//			else 
//			{
//				if (matrix[mouseCoorX/40][x] != null)
//				{
//					//matrix[mouseCoorX/40][x].applyDiff(mouseDiffX, mouseDiffY, matrix);
//					//startFalling(matrix[mouseCoorX/40][x], x, mouseCoorY/40);
//				}
//			}
//		}
//		
//		mouseCoorX = arg0.getX();
//		mouseCoorY = arg0.getY();
//
//		
////		int x = arg0.getX()/40;
////		int y = arg0.getY()/40;
//
//		//removeFruit(matrix[x][y], x, y);		
//		

		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}