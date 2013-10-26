import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class Fruit 
{
	int x, y;
	Color type;
	int count;

	static int totalFruit = 0;

	int id;

	int oldX, oldY;

	int animateOffset = 20;

	public Fruit(int col)
	{
		y = -40; 
		x = col*40;

		totalFruit++;
		id = totalFruit;

		type = randomColor(true);

	}

	public Color getColor()
	{
		//		if (type.getRed() >= 250 || type.getBlue() >= 250 || type.getGreen() >= 250)
		if (type == Color.white)
			return randomColor(false);
		else
			return type;
	}

	public Color randomColor(boolean isRandom)
	{
		//		return new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
		int rand = (int)(Math.random()*1000);

		int numberofsizes = isRandom? 4 : 5;

		Color c;
		switch(rand%numberofsizes)
		{
		case 0:
			c = Color.CYAN;
			break;
		case 1:
			c = Color.magenta;
			break;
		case 2:
			c = Color.orange;
			break;
		case 3:
			c = Color.white;
			break;
		case 4:
		default:
			c = Color.YELLOW;
		}

		return c;

	}

	public boolean fall(Fruit[][] field)
	{
		return hasFallen(field, false);
	}

	public boolean fall(Fruit[][] field, boolean fakefall)
	{
		return hasFallen(field, fakefall);
	}

	public void storeOldValues(int x, int y)
	{
		oldX = x;
		oldY = y;
	}

	public boolean hasFallen(Fruit[][] field, boolean fakefall)
	{
		boolean fallen = false;

		fallen = (y >= (320 + animateOffset) || (belowFruit(field) != null && y >= ((int)(y/40))*40 + animateOffset));

		if (fallen)
		{
			y = ((int)(y/40))*40;

			if (field[x/40][y/40] == null)
				field[x/40][y/40] = this;
			else
				fallen = true;

			fallen = true;
			animateOffset = 0;
		}
		else
		{
			if (y/40 > 0)
			{
				if (field[x/40][y/40-1] == this)
					field[x/40][y/40-1] = null;
				//			field[x/40][y/40] = this;
			}
			y+= 5;
			animateOffset = 20;
		}

		//System.out.println("falling");

		return fallen;

	}

	Fruit aboveFruit(Fruit[][] matrix)
	{
		return matrix[x/40][y/40-1];
	}

	Fruit belowFruit(Fruit[][] matrix)
	{
		return matrix[x/40][y/40+1];
	}

	Fruit leftFruit(Fruit[][] matrix)
	{
		return matrix[x/40-1][y/40];
	}

	Fruit rightFruit(Fruit[][] matrix)
	{
		return matrix[x/40+1][y/40];
	}


	public void applyDiff(int mouseDiffX, int mouseDiffY, Fruit[][] matrix) 
	{
		x += mouseDiffX;
		y += mouseDiffY;

	}

	public void moveHorizontal(int i) 
	{
		x += i;

		if (x<0)
			x = 360;

		if (x>360)
			x = 0;

	}

	public void moveVertical(int i) 
	{
		y += i;

		if (y>320)
		{
			y = -40;
		}

	}

	public boolean moveRight(Fruit[][] field) 
	{
		boolean moved = false;

		moved = count>=9;

		if (count==0)
			storeOldValues(x, y);

		if (moved)
		{
			x = ((int)(x/40))*40;


			field[x/40][y/40] = this;

			moved = true;
			animateOffset = 0;
			count = 0;
		}
		else
		{
			x += 5;
			count ++;
			animateOffset = 20;
			if (x > 380)
				x = -20;

			if (x/40 > 0 && x < 360 && count == 1)
			{

				field[x/40+1][y/40] = this;
			}
		}

		return moved;
	}

	public boolean moveLeft(Fruit[][] field) 
	{
		boolean moved = false;

		moved = count>=9;

		if (count==0)
			storeOldValues(x, y);

		if (moved)
		{
			int divisor = (x<20 || x == 360)? 0 : 1;

			x = ((int)(x/40)+divisor)*40;

			field[x/40][y/40] = this;

			moved = true;
			animateOffset = 0;
			count = 0;
		}
		else
		{
			x -= 5;
			count ++;
			animateOffset = 20;
			if (x < -20)
			{
				x = 380;
			}

			if (x/40 > 0 && count == 1)
			{

				field[x/40][y/40] = this;
			}
		}

		return moved;
	}

	public HashSet<Fruit> getChain(int chain, Fruit[][] matrix) 
	{
		HashSet<Fruit> ll = new HashSet<Fruit>();

		for (int x=0; x<5; x++)
		{
			LinkedList<Fruit> tl = new LinkedList<Fruit>();
			Fruit next = nearFruit(matrix, x);
			
			tl.add(this);

			Color curType = type;
			
			while (next!= null && this != next)
			{
				if (next.type == curType || next.type == Color.white || curType == Color.white)
				{
					System.out.print(next+" "+this);
					tl.add(next);
					if (curType == Color.white)
						curType = next.type;
				}
				else
					break;
				
				next = next.nearFruit(matrix, x);
			}
			
			System.out.println();

			if (tl.size() >= chain)
				ll.addAll(tl);

		}
		
		return ll;
	}

	public String toString()
	{
		return "["+humanReadableColor()+" "+id+"]";
	}
	
	private String humanReadableColor() 
	{
		if (type == Color.white)
			return "Random";
		else if (type == Color.cyan)
			return "Cyan";
		else if (type == Color.yellow)
			return "Yellow";
		else if (type == Color.orange)
			return "Orange";
		else if (type == Color.magenta)
			return "Magenta";
		else
			return "Unknown";
	}

	private Fruit nearFruit(Fruit[][] matrix, int x2) 
	{
		if (x<40 || y<40 || x>=360 || y>320)
			return null;
			
		switch (x2)
		{
		case 0:
			return aboveFruit(matrix);
		case 1:
			return belowFruit(matrix);
		case 2: 
			return leftFruit(matrix);
		case 3: 
		default:
			return rightFruit(matrix);
		}
	}


}
