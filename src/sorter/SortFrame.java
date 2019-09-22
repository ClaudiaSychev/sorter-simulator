package sorter;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;



public class SortFrame {
 
	public static final int FRAME_WIDTH=700;
	public static final int FRAME_HEIGHT=700;
	public static void main(String[]args)
	{
	SortEngine sortEngine = new SortEngineImpl(SortEngine.MAX_SIZE);
	
	SortController controller = new SortController(sortEngine);
	
	JFrame frame = new JFrame("Sorting Simulator -- Claudia Sychev");
	frame.setLayout(new FlowLayout());
	frame.addWindowListener( new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent)
		{
			System.exit(0);
		}
	});
	frame.getContentPane().add(controller);
	frame.setSize(SortFrame.FRAME_WIDTH,SortFrame.FRAME_HEIGHT);
	frame.setVisible(true);
	
   }

}
