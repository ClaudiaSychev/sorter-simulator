package sorter;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SortController extends JPanel
{
	private JButton startButton, stopButton, resetButton;
	private SortEngine engine;
	private Thread sortThread;
	private JComboBox <String> sortBox;
	private SortView sortView;
	private String [] mySorts = {"Quick Sort", "Merge Sort", "Insertion Sort", "Selection Sort"};
	private JPanel buttonPanel;
	private ChangeListener sortListener;

	public SortController(SortEngine aEngine)
	{
		super();
		this.setPreferredSize(new Dimension(SortFrame.FRAME_HEIGHT - 10, SortFrame.FRAME_HEIGHT -20));

		this.startButton = new JButton("Start");
		this.stopButton = new JButton("Stop");
		this.resetButton = new JButton("Reset");
		this.sortBox = new JComboBox <String> (mySorts);

		buttonPanel = new JPanel();

		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(sortBox);

		
		this.setLayout(new BorderLayout());

		// construct view
		this.sortView = new SortView(aEngine);
		this.engine = aEngine;
		this.sortListener = new ChangeListener() {
			public void stateChanged(ChangeEvent e){
				update();
			}
		
		};
		this.engine.addChangeListener(sortListener);
		this.add(buttonPanel, BorderLayout.NORTH); 
		this.add(sortView, BorderLayout.SOUTH);
		

		//		this.add(buttonPanel, BorderLayout.NORTH);

		init();
	}
	private void update() 
	{
		try {
			Thread.sleep(20);
		}
		catch(InterruptedException e) {
			System.out.println("Error in Sleep");
			return;
		}
		
		repaint();
	}
	private void init()
	{
		this.stopButton.addActionListener(new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				while (sortThread != null && sortThread.isAlive())
				{
					engine.setStopFlag(true);
				}
				
			}

		});

		this.resetButton.addActionListener(new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				while (sortThread != null && sortThread.isAlive())
				{
					engine.setStopFlag(true);
				}
				engine.shuffleList();
			}
		});

		this.startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				while (sortThread != null && sortThread.isAlive())
				{
					engine.setStopFlag(true);
				}
				
				sortThread = new Thread (new Runnable()
				{ 
					public void run()
					{
						engine.setStopFlag(false);
						int index = sortBox.getSelectedIndex();
						switch (index)
						{
						case 0: engine.quickSort();
						break;
						case 1: engine.mergeSort();
						break;
						case 2: engine.insertionSort();
						break;
						case 3: engine.selectionSort();
						break;
						}
					}
				});
				sortThread.start();
			}
		});

	}
}
