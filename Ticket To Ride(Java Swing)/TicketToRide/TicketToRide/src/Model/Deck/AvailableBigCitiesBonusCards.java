package Model.Deck;





import Model.Cards.BigCitiesBonusCards;


public class AvailableBigCitiesBonusCards {

private BigCitiesBonusCards[] abcards;

/**<b>Constructor</b>
 * Creates an ArrayList of BigCitiesBonusCards
 */
	
public 	AvailableBigCitiesBonusCards() {
	abcards=new BigCitiesBonusCards[6];
}


/**<b>Transformer:</b> Add an object of BigCitiesBonusCards to the abcards
  *<b>Postcondition:</b> Object added
  *@param b : BigCitiesBonusCards object  to add
  *@param index : index that the BigCitiesBonusCards object is placed
  * */	
public void InserttoList(BigCitiesBonusCards b,int index) {
	abcards[index]=b;
}
	

public BigCitiesBonusCards takeFromIndex(int index) {
	return abcards[index];
	
}

/**<b>Transformer:</b> "Remove" Object from index(position)
  *<b>Postcondition:</b> abcards at index set to null
  *@param index : position
  * 
  * */

public void  removefromIndex(int index) {
	abcards[index]=null;
	
}


/**<b> Accessor:</b> Returns the ArrayList of abcards 
 * <b>Postcondition:</b> ArrayList returned
 * @return abcards ..
 * */

public BigCitiesBonusCards[] returnCards() {
	
	return this.abcards;
}
}
