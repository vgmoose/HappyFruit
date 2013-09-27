import java.awt.Color;

public class Fruit 
{
	int x, y;
	Color type;

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

		if (y >= 340)
		{
			y = 320;
			fallen = true;
		}

		if (y >=0)
		{
			if (fallen || field[x/40][y/40+1] != null)
			{
				y = ((int)(y/40))*40;
				
				field[x/40][y/40] = this;
				fallen = true;
			}
			else
				y+= 5;
		}
		else
			y += 5;

		return fallen;

	}

}
