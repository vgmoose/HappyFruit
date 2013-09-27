public class Fruit 
{
	int x, y;
	
	public Fruit(int col)
	{
		y = 0; 
		x = col*40;
	}
	
	public boolean fall()
	{
		y += 1;
		if ((y >= 340))
		{
			y = 340;
		}
		
		return (y >= 340);
	}

	
}
