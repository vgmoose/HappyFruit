

import javax.swing.*;

public class Main extends JFrame
{

	private static final long serialVersionUID = 1L;

	public static void main(String[] args)
	{
		FruitGame d = new FruitGame(400, 380);
		new Main(d);

	}
	
	public Main(FruitGame d)
	{
		this.add(d);
		this.pack();
		setSize(400, 380);
		this.setVisible(true);
		
	}
}
