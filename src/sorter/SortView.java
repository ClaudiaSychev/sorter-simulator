package sorter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import java.util.Random;

public class SortView extends JPanel
{
	public final static int MYWIDTH = SortFrame.FRAME_WIDTH - 50;
	public final static int MYHEIGHT = SortFrame.FRAME_HEIGHT - 100;

	private int [] list;
	private SortEngine engine;
	Random randomNumberGenerator = new Random();
	public SortView(SortEngine aEngine)
	{
		super();
		this.engine = aEngine;
		this.list = engine.getList();
		this.setPreferredSize(new Dimension(MYWIDTH, MYHEIGHT));
		this.repaint();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.list = this.engine.getList();
		for (int index = 0; index < list.length; index++)
		{
			if(index %2==0) {
				int randomInt = randomNumberGenerator.nextInt(100);
				int randomInt2 = randomNumberGenerator.nextInt(100);
				int randomInt3 = randomNumberGenerator.nextInt(100);
				g.setColor(new Color(randomInt, randomInt2,randomInt3));
				
			}
			else {
				int randomInt = randomNumberGenerator.nextInt(200);
				int randomInt2 = randomNumberGenerator.nextInt(200);
				int randomInt3 = randomNumberGenerator.nextInt(200);
				g.setColor(new Color(randomInt, randomInt2,randomInt3));
				
			}
			
			
			
			
			int value = list[index];
			int x1 = index + 1;
			int x2 = index + 1;
			int y1 = MYHEIGHT + 50;
			int y2 = MYHEIGHT - value;
			g.drawLine(x1, y1, x2, y2);
		}
	}

}
