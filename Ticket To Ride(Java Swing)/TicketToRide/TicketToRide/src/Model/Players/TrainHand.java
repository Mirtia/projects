package Model.Players;

import java.util.ArrayList;



import Model.Cards.TrainCards;


public class TrainHand {
	private ArrayList<TrainCards> hand;

  /**<b>constructor</b>
   *Creates a new Arraylist of TrainCards
   **/
	public TrainHand() {
		
		hand=new ArrayList<TrainCards>();
		
	}
	/**<b> Accessor:</b> Returns if ArrayList is empty
	  * <b>Postcondition:</b> Boolean value calculated by isEmpty() is returned 
	  * @return hand.isEmpty()
	  * */
	
	public boolean returnEmpty() {
		return hand.isEmpty();
	}

   /**<b>Transformer:</b> Selects ArrayList of TrainCards to play on the Railyard
    *<b>Postcondition:</b> Cards chosen
    *@param selectedtoplay Cards that the players want to play
    */
   
	public void RemoveChoisesHand(ArrayList<TrainCards> selectedtoplay) {
		hand.removeAll(selectedtoplay);
	}	

	/**<b>Transformer:</b> Adds an ArrayList of TrainCards to the TrainHand
	 *<b>Postcondition:</b> Cards added successfully
	 *@param selected is the list of TrainCards to add on Hand
	*/
	
	public void AddListHand(ArrayList<TrainCards> selected) {
		hand.addAll(selected);
	}

	/**<b>Transformer:</b> Adds a TrainCards card to the TrainHand
	 *<b>Postcondition:</b> Card added successfully
	 *@param t : Add one Card to Trainhand
	*/
	
	
	public void AddOneCard(TrainCards t) {
		hand.add(t);
	}

	
	/**<b> Accessor:</b> Returns TrainCards
	  *<b>Postcondition:</b> Traincards returned
	  *@return hand 
	  * */
	public ArrayList<TrainCards> returnCards() {
		
		return this.hand;
	}
    
	
}
