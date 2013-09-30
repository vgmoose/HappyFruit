import java.awt.Color;

public class Fruit 
{
	int x, y;
	Color type;
	
	int animateOffset = 20;

	public Fruit(int col)
	{
		y = -40; 
		x = col*40;

		type = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));

	}

	public boolean fall(Fruit[][] field)
	{
		return hasFallen(field);
	}

	public boolean hasFallen(Fruit[][] field)
	{
		boolean fallen = false;


		fallen = (y >= (320 + animateOffset) || (belowFruit(field) != null && y >= ((int)(y/40))*40 + animateOffset));

		if (fallen)
		{
			y = ((int)(y/40))*40;
			if (animateOffset == 20)
				x = ((int)(x/40))*40;

			if (field[x/40][y/40] == null)
				field[x/40][y/40] = this;
			
			fallen = true;
			animateOffset = 0;
		}
		else
		{
			y+= 5;
			x = ((int)(x/40))*40;
			animateOffset = 20;
		}

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
		
	}

}
