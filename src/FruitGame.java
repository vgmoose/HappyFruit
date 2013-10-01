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
	LinkedList<Fruit> moveRightQueue = new LinkedList<Fruit>();
	LinkedList<Fruit> moveLeftQueue = new LinkedList<Fruit>();


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
				createNewFruit(5);
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
	
	public void moveColDown(int col)
	{
		for (int x=0; x<10; x++)
		{
			if (matrix[col][x] != null)
			{
				matrix[col][x].moveVertical(40);
			}
			
			startFalling(matrix[col][x], col, x);
		}
	}
	
	public void moveRowLeft(int row)
	{
		for (int x=0; x<10; x++)
		{
			if (matrix[x][row] != null)
			{
				moveLeftQueue.add(matrix[x][row]);
			}
			
			startFalling(matrix[x][row], x, row);
		}
	}
	
	public void moveRowRight(int row)
	{
		for (int x=0; x<10; x++)
		{
			if (matrix[x][row] != null)
			{
				moveRightQueue.add(matrix[x][row]);
			}
			
			startFalling(matrix[x][row], x, row);
		}
	}


	public void actionPerformed(ActionEvent arg0) 
	{
		if (counter%50 == 0)
		{
			createNewFruit((int)(Math.random()*10));
//			createNewFruit(3);


		}

		counter++;

		LinkedList<Fruit> doneFalling = new LinkedList<Fruit>();
		LinkedList<Fruit> doneMovingHorizontally = new LinkedList<Fruit>();


		for (Fruit f : moveRightQueue)
		{
			if (f.moveRight(matrix))
				doneMovingHorizontally.add(f);
		}
		
		for (Fruit f : moveLeftQueue)
		{
			if (f.moveLeft(matrix))
				doneMovingHorizontally.add(f);
		}
		
		
		for (Fruit f : doneMovingHorizontally)
		{
			moveRightQueue.remove(f);
			moveLeftQueue.remove(f);

			//field.remove(f);
		}
		
		
		System.out.println("Fall: "+fallingQueue);
		System.out.println("Right: "+moveRightQueue);
		System.out.println("Left: "+moveLeftQueue);


		for (Fruit f : fallingQueue)
		{
			if (!moveRightQueue.contains(f) && !moveLeftQueue.contains(f) && f.fall(matrix))
			{
				doneFalling.add(f);
			}
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

		if (y>0 && matrix[x][y-1] != null)
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
//		mouseCoorX = arg0.getX();
//		mouseCoorY = arg0.getY();
//		
//		moveRowLeft(arg0.getY()/40);
		mouseCoorX = arg0.getX()/40;
		mouseCoorY = arg0.getY()/40;
		
		
	}


	public void mouseReleased(MouseEvent arg0) {


	}

	@Override
	public void mouseDragged(MouseEvent arg0) 
	{
		int mouseClickX = arg0.getX()/40;
		int mouseClickY = arg0.getY()/40;
		
		if (mouseClickX < mouseCoorX)
			moveRowLeft(mouseClickY);
		else if (mouseClickX > mouseCoorX)
			moveRowRight(mouseClickY);
		else if (mouseClickY > mouseCoorY)
			moveColDown(mouseClickX);
			
		mouseCoorX = mouseClickX;
		mouseCoorY = mouseClickY;

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