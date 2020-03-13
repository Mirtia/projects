package Model.Players;

import java.util.ArrayList;




import Model.Cards.DestinationCards;
import Model.Cards.TrainCards;



public class OnTheTrack {

private int[] sum;

	


/**<b>Constructor</b>
 * Creates a new OnTheTrack field for Player 
 * 
 * */
public OnTheTrack() {
	
  sum=new int[9];
  }

/**<b>Transformer:</b> Takes the cards from the Railyard and organizes them in OnTheTrack
 *<b>Postcondition:</b> Cards organized according to their ColorTrainCards color 
 * @param x Cards that were moved from Railyard
 * */
    
public void  ArrangeFromRailyard(ArrayList<TrainCards> x) {
		for(int i=0;i<x.size();i++) {
			System.out.println(x.get(i).returnID()+" "+x.get(i).returnColor() );
			sum[x.get(i).returnColor().getValue()]++;
			
		}
		}


/**<b>Transformer:</b> Removes the sum of DestinationCards object that will be exchanged for purchase
  *<b>Postcondition:</b> Sum decremented
  * @param c The card to be removed and bought
  * */	
public void RemovetoBuy(DestinationCards c) {
	int array[]=c.getSumOfEachColor();
	for(int i=0;i<array.length;i++) {
		if((sum[i]-array[i])>=0)
		sum[i]-=array[i];
	}

 
};


/**<b>Transformer:</b> Removes the number of Locos passed as parameter
 *<b>Postcondition:</b> Cards removed
 * @param number The sum  to be removed 
 * 
 * */	


public void RemoveLocos(int number) {
	sum[8]-=number;
}

/**<b>Accessor:</b>  Returns the sum of ColorTrainCards for the specific color ArrayList     
 *<b>Postcondition:</b> Sum returned
 * @param index : position of the specific Color
 * @return sum at specific index
 * */	


public int getSumColorIndex(int index) {
		return sum[index];
	
}


/**<b>Accessor:</b>  Returns the sum of ColorTrainCards 
 *<b>Postcondition:</b> Sum returned
 * @return sum 
 * */	
public int[] returnSum() {
	
	return this.sum;
}
	
}
