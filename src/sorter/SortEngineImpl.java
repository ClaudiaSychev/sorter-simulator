package sorter;

import javax.swing.event.ChangeEvent;

import javax.swing.event.ChangeListener;

import java.util.Random;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class SortEngineImpl implements SortEngine
{
	private int [] list;
	private int maxSize;
	private EventListenerList eventListenerList;
	private final ChangeEvent CHANGE_EVENT = new ChangeEvent(this);
	private boolean stopFlag;
	
	//First constructor
	//
	public SortEngineImpl()
	{
		this.list = new int [SortEngine.MAX_SIZE];
		Random randomNumberGenerator = new Random();
		for(int i = 0; i < list.length; i++)
		{
			int randomInt = randomNumberGenerator.nextInt(MAX_SIZE);
			this.list[i] = randomInt;
		}
		this.eventListenerList = new EventListenerList(); 
		this.stopFlag = false;
		this.maxSize = MAX_SIZE;
	}
	//Second constructor
	public SortEngineImpl(int maxSize)
	{
		
		if(maxSize <= 0 || maxSize > MAX_SIZE)
		{
			this.maxSize = MAX_SIZE;
		}
		else
		{
			this.maxSize = maxSize;
		}
		this.list = new int [this.maxSize];
		Random randomNumberGenerator = new Random();
		for(int i = 0; i < list.length; i++)
		{
			int randomInt = randomNumberGenerator.nextInt(this.maxSize);
			this.list[i] = randomInt;
		}
		this.eventListenerList = new EventListenerList(); 
		this.stopFlag = false;
	}
	// post: returns the size of this engine's list
	public int getSize()
	{
		return this.maxSize;
	}

	
	
	
	
	//receives: nothing 
	//task: list is sorted in ascending order using mergesort algorithm
	//returns: nothing
	public void mergeSort()
	{
		myMergeSort(list, 0, maxSize-1);
		this.fireChangeEvent(CHANGE_EVENT);
		
	}
	
	//mergesort algoritms implementation 
	private void myMergeSort(int[] list, int low, int high)
	{
		
		if(low<high && stopFlag == false)
		{
			int mid = (low+high)/2;
			myMergeSort(list, low, mid);
			myMergeSort(list,mid+1, high);
			merge(list,low,mid,mid+1,high);
			this.fireChangeEvent(CHANGE_EVENT);
		}	
	}
	//merges lists
	private void merge(int [] list,int low, int mid, int low1,int high) 
	{
		// merges two sorted lists within myList,
				//    myList[low:mid] and myList[low1:high]

				int temp[] = new int [(mid-low+1)+(high-low1+1)];
				// temp to hold list as merged.
				int s1, s2, d, k;  // indexes used to keep track of positions in merging
				s1=low;            // start of lower half, s1, upper half start is s2
				s2=low1;
				d=0;
				while(s1<=mid && s2<=high && stopFlag == false)  // while elements in BOTH sublists left
				{
					if(list[s1] < list[s2])
						temp[d++] = list[s1++];
					else
						temp[d++] = list[s2++];
				}
				while (s1<=mid)       // while lower half is not merged, copy rest of it
				{  
					temp[d++]=list[s1++];
				}
				while (s2<=high)      // while upper half is not merged copy rest into temp
				{  
					temp[d++]=list[s2++];
				}

				for(k=0,s1=low;s1<=high; s1++,k++) 
				{// now copy temp BACK to myList
					list[s1]=temp[k];
				}
				this.fireChangeEvent(CHANGE_EVENT);
	}
	
	
	
	
	//receives: nothing 
	//task: list is sorted in ascending order using quicksort algorithm
	//returns: nothing
	public void quickSort()
	{
		quickSort(list, 0, maxSize-1);
		this.fireChangeEvent(CHANGE_EVENT);
		
	}
	
	/* given:  array list with indices lower and upper 
	   task:   to sort array list from lower to upper
	   returns:  array list sorted */
	private void quickSort(int[] list, int low, int high) 
	{
		int pivotlocation;
		if (low < high && stopFlag==false)  //  is a list of more than 1 to sort?
		{
			pivotlocation = partition(list, low,high);
			this.fireChangeEvent(CHANGE_EVENT);
			quickSort(list, low, pivotlocation-1);
			quickSort(list, pivotlocation+1, high);
			
		}
		
	}
	//partitioning algorithm implementation for quick sort
	private int partition(int [] list, int lo, int hi)
	{
		int  pivotvalue;  // type of element being sorted
		pivotvalue = list[lo];
		while( lo < hi && stopFlag == false)
		{
			while (lo < hi && pivotvalue < list[hi]) 
				hi--;
				
			if (lo != hi)
			{
				list[lo]=list[hi];
				lo++;
				
			}
			while (lo < hi && pivotvalue > list[lo])
				lo++;
				
			if (lo !=hi )
			{
				list[hi]= list[lo];
				hi--;
				
			}
			this.fireChangeEvent(CHANGE_EVENT);
		}  // end of while 
		list[hi]=pivotvalue;
		pivotvalue=hi;
		// returns this as pivotlocation 
		return pivotvalue;
	}
	
	/* given:  list, the array to be partitioned
    lo and hi, the index limits of the list to partition
    pivot - location at which partition takes place.
task:  partition the array between lo and hi, returning location
   of the partition in variable pivot.

returns:  list partitioned and pivot as location of partition */
	
	
	

	
	
	//receives: nothing 
	//task: list is sorted in ascending order using insertion sort algorithm
	//returns: nothing
	public void insertionSort()
	{
		insertSort(list,this.maxSize);
		this.fireChangeEvent(CHANGE_EVENT);
	}
	//insertion sort algortithm implementation
	private void insertSort(int[]list, int numElements)
	{
		int temp;
        for (int i = 1; i < numElements && stopFlag == false; i++)
        {
            for(int j = i ; j > 0 && stopFlag == false ; j--)
            {
                if(list[j] < list[j-1])
                {
                    temp = list[j];
                    list[j] = list[j-1];
                    list[j-1] = temp;
                    this.fireChangeEvent(CHANGE_EVENT);
                    
                }
            }
        }
        
        
	}

	//receives: nothing 
	//task: list is sorted in ascending order using selection sort algorithm
	//returns: nothing
	public void selectionSort()
	{
		selectionSort(list, this.maxSize);
		this.fireChangeEvent(CHANGE_EVENT);
		
	}
	
	//selection sort method
	//algorithm implementation
	private void selectionSort(int[]list, int numElements)
	{
		
        for (int i = 0; i < numElements - 1 && stopFlag == false; i++)
        {
            int index = i;
            for (int j = i + 1; j < numElements && stopFlag ==false; j++) 
                if (list[j] < list[index]) 
                    index = j;
      
            int smallerNumber = list[index];  
            list[index] = list[i];
            list[i] = smallerNumber;
            this.fireChangeEvent(CHANGE_EVENT);
        }
       
    }
	

	//receives: nothing
	// task: this sort engine's current list is randomly shuffled 
	// returns: nothing
	public void shuffleList()
	{
		Random rgen = new Random();  // Random number generator			
		 
		for (int i=0; i < this.maxSize; i++)
		{
			
			
		    int randomPosition = rgen.nextInt(list.length);
		    int temp = list[i];
		    list[i] = list[randomPosition];
		    list[randomPosition] = temp;
		    
			
		}
		this.fireChangeEvent(CHANGE_EVENT);
		
		
	}

	//receives: nothing
	//returns: array of elements that comprise the contents of this sort engine's values 
	public int [] getList()
	{
		return this.list;
	}
	
	
	
	//pre: 
	//post: stop flag is set to given flagValue, which controls sorting (turns it off or on for control)
	public void setStopFlag(boolean flagValue)
	{
		this.stopFlag = flagValue;
	}

	//Method to changeListener
	//takes in one parameter 
	public void addChangeListener(ChangeListener stackEngineListener)
	{
		eventListenerList.add(ChangeListener.class, stackEngineListener);
	}

	// removes change listener
    //takes one ChangeListener parameter
	public void removeChangeListener(ChangeListener stackEngineListener) {
		
		eventListenerList.remove(ChangeListener.class, stackEngineListener);
		
	}

	// fires off change events to update the view
	public void fireChangeEvent(ChangeEvent event) {
		// Guaranteed to return a non-null array
				Object[] listeners = eventListenerList.getListenerList();
				// Process the listeners last to first, notifying
				// those that are interested in this event
				for (int i = listeners.length-2; i>=0; i-=2) 
				{
					if (listeners[i]==ChangeListener.class) 
					{
						((ChangeListener)listeners[i+1]).stateChanged(event);
					}
				}
		
	}
	//To string
	public String toString()
	{
		String retString = "";
		for (int index = 0; index < maxSize; index++)
		{
			retString += list[index] + "\n";
		}

		return retString;
	}
	



}
