package Model.Deck;

import java.util.ArrayList;



import Model.Cards.TrainCards;


public class RevealedTrainCards  {

private TrainCards[] array;

/**<b>Constructor</b>
 * Initializes an ArrayList of 5 size 
 * These cards are situated at the center-field,can be accessed by both Players
 * */
public  RevealedTrainCards() {
	array=new TrainCards[5];
}
	
/**<b>Transformer:</b> Takes a ColorTrainCard and places it at index position
   *<b>Postcondition:</b> Card placed
   *@param index: Position to set the ColorTrainCard
   *@param c: the ColorTrainCard to be set
 * */

public void InserttoList(TrainCards c,int index) {
	array[index]=c;
}
	
/**<b>Transformer:</b> Takes a card from index 
  * <b>Postcondition:</b> Card has been given to the Player 
  * @param index : Position of the seleceted card to be returned
  * @return array[index]: ColorTrainCards card from index
  * */

public TrainCards TakefromIndex(int index) {
	
	return array[index];
}

/**<b>Transformer:</b> Removes(resets to null) the array element at position index
 * <b>Postcondition:</b> array[index] set to null
 * @param index (position of array)
 * */

public void removeFromIndex(int index) {
	array[index]=null;
}

/**<b>Transformer:</b> Adds a list to the array
 * <b>Postcondition:</b> List added
 * @param temp(List passed as parameter and will be added to each array position)
 * */

public void AddList(ArrayList<TrainCards> temp) {

for(int i=0;i<this.array.length;i++) {
		array[i]=temp.get(i);
	}
}
   

/**<b> Accessor:</b>Returns array of TrainCards 
 * <b>Postcondition:</b> array returned
 * @return array
 * */

public  TrainCards[]  returnRt(){
	return this.array;
}

}
	
	

