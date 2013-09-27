import java.awt.Color;
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
		FruitGame d = new FruitGame(400, 380);
		Main m = new Main(d);

	}
	
	public Main(FruitGame d)
	{
		this.add(d);
		this.pack();
		setSize(400, 380);
		this.setVisible(true);
		
	}
}