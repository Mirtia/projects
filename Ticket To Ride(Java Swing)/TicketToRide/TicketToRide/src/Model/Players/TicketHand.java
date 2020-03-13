package Model.Players;

import java.util.ArrayList;



import Model.Cards.DestinationCards;



public class TicketHand {
    private int sum;
	private ArrayList<DestinationCards> hand;
	
	
/**<b>Constructor</b>
 * Creates a DestinationTicketHand Object
 * The DestinationCards objects PlayerF has on his hand
 * */
public TicketHand() {
	
	hand=new ArrayList<DestinationCards>();
	
}
/**<b>Transformer:</b> Removes the Card that is bought
 *<b>Postcondition:</b> Card removed if purchase completed successfully
 *@param index : Which card to buy ,index is position
 *@return Destinationcards object  at index of TicketHand (hand)
 * **/

public DestinationCards ChoisetoBuyHand(int index) {
	return hand.get(index);
	
	
};
/**<b>Transformer:</b> Adds an ArrayList of DestinationCards to the TicketHand
 *<b>Postcondition:</b> Cards added successfully
 *@param selected List to add to Hand
*/

public void AddListHand(ArrayList<DestinationCards> selected) {
	
	hand.addAll(selected);
};



/**<b>Transformer:</b> Adds a DestinationCards card to the TicketHand
 *<b>Postcondition:</b> Card added successfully
 *@param dt The DestinationCard to be added
*/
public void AddOneCard(DestinationCards dt) {
	hand.add(dt);
}

/**<b>Accessor:</b> Returns the points (sum) of the TicketPoints on TicketHand,used to calculate score(ScoreBoard)     
 *<b>Postcondition:</b> Points returned
 *@return sum (points calculated from DestinationCards at the whole)
 **/

public int ReturnCurrentTicketPoints(){
	sum=0;
	for(DestinationCards i:hand) {
		sum+=i.ReturnPoints();
	}
    return sum;
	
}

/**<b> Accessor:</b> Return ArrayList hand of TicketHand
 * <b>Postcondition:</b> ArrayList returned
 * @return hand
 * */
public ArrayList<DestinationCards> returnCards(){
	return this.hand;
}

/**<b>Transformer:</b> Removes the DestinationCards card from index at hand
  *<b>Postcondition:</b> DestinationCards removed
  * @param index : index!
  * */

public void removeAtIndex(int index) {
	hand.remove(index);
}


}


