package Model.Players;

import java.util.ArrayList;


import Model.Cards.DestinationCards;
import Model.Cards.TrainCards;


public class Player {

private OnTheTrack onthetrack;
private Railyard railyard;
private Scoreboard scoreboard;
private TrainHand trainCardsOnHand;
private TicketHand ticketsOnHand;
private ArrayList<TrainCards> CardsToPlay;
private int ID;
private boolean validchoise;
private boolean validcardplacement;
private boolean played=true;
private boolean canbuy=false;
/**<b>Constructor</b>
 * Creates a TrainHand, TicketHand, Scoreboard, OnTheTrack object for each Player Instance
 * @param ID : Player ID,Player 0 ,Player 1
  * */
public Player(int ID) {
	
	CardsToPlay=new ArrayList<TrainCards>();
	this.ID=ID;
	trainCardsOnHand=new TrainHand();
    ticketsOnHand=new TicketHand();
    this.scoreboard=new Scoreboard();
    this.onthetrack=new OnTheTrack();    
    this.validcardplacement=false;
    this.validchoise=false;
    railyard=new Railyard();
}
/**<b>Accessor:</b> Returns PlayerF's ID (number ID)      
 *<b>Postcondition:</b> PlayerF ID returned
 * @return ID
 */
public int GetID() {
	
	return this.ID;
}

/**<b>Transformer:</b> Adds CardsSelected on PlayerF's Hand (uses method  from TrainHand)     
 *<b>Postcondition:</b> The cards have been added to TrainHand
 * @param selected : The  cards to be added on TrainHand
 */


public void AddTrainCardsOnHand(ArrayList<TrainCards> selected) {
	this.trainCardsOnHand.AddListHand(selected);
}

/**<b>Transformer:</b> Adds CardsSelected on PlayerF's Hand (uses method  from TicketHand)     
 *<b>Postcondition:</b> The cards have been added to TicketHand
 * @param selected: The  The  cards to be added on TicketHand
 */

public void AddTicketCardsOnHand(ArrayList<DestinationCards> selected) {
	this.ticketsOnHand.AddListHand(selected);
}

/**<b>Accessor:</b> Returns the sum of Points that are in PlayerF's TicketHand      
 *<b>Postcondition:</b> Sum returned
 *@return the sum of points of Tickets on hand
 */


public int PointsTicketsHands() {
	return ticketsOnHand.ReturnCurrentTicketPoints();
}

/**<b>Accessor:</b> Returns the PlayerF's Score      
 *<b>Postcondition:</b> Score returned
 *@return score of the Player
 */

public int GetScore() {
	return this.scoreboard.Getscore();
	
}

/**<b>Transformer:</b> Sets the TrainCards that the PlayerF want to place on his Railyard
 *<b>Postcondition:</b> Cards set
 * @param e: Cards set to play
 * */

public void SetHandCardsPlaceRailyard(ArrayList<TrainCards> e) {//chosen by the buttons
this.CardsToPlay.addAll(e);

	
	
}

public void AddtoPlay(TrainCards e) {
	this.CardsToPlay.add(e);
}



public void CleartoPlay() {
	this.CardsToPlay.clear();
}


/**<b>Accessor:</b> Get CardsToPlay (cards to place on Railyard /have to be compare by Round's class  method before played
 *<b>Postcondition:</b> return CardsToPlay
 * @return CardsToPlay : cards that the Player wants to place on Railyard
 * */


public  ArrayList<TrainCards> GetHandCardstoPlaceRailyard() {
	return CardsToPlay;
	
	
}

/**<b>Transformer:</b> The index responds to PlayerF's DestinationCard on specific position,buys DestinationCards c from TicketHand
 *<b>Postcondition:</b> Purchase completed successfully
 * @param index Buy from specific position
 * */

public void BuyCards(int index) {
	DestinationCards g=this.returnTicketHand().ChoisetoBuyHand(index);
    int[] col=g.getSumOfEachColor();
    canbuy=true;
    for(int i=0;i<8;i++) {
       if(col[i]>this.onthetrack.getSumColorIndex(i))
          canbuy=false;
    }
    if(canbuy==true) {
    	this.scoreboard.AddDestinationCards(g);
    	this.onthetrack.RemovetoBuy(g);
    	this.ticketsOnHand.removeAtIndex(index);
    	
    	
    }
}




public void BuyCardsWithLocomotives(int index) {
	DestinationCards g=this.returnTicketHand().ChoisetoBuyHand(index);
    int[] col=g.getSumOfEachColor();
    int numberoflocosneed=0;
    
    
    for(int i=0;i<8;i++) {
       if(col[i]>this.onthetrack.getSumColorIndex(i))
          numberoflocosneed++;
    }
    if(numberoflocosneed<=this.onthetrack.getSumColorIndex(8)) {
    	this.onthetrack.RemoveLocos(numberoflocosneed);
    	this.onthetrack.RemovetoBuy(g);
    	this.scoreboard.AddDestinationCards(g);
    	
    	this.ticketsOnHand.removeAtIndex(index);
    	canbuy=true;
    	
    }
}

/**<b> Accessor:</b>Returnts if current Player can buy Destinationcard wanted 
 * <b>Postcondition:</b> Boolean value returned
 * @return canbuy
 * */
public boolean returnCanBuy() {
	return this.canbuy;
	
}

/**<b>Transformer:</b> Sets canbuy with some value. 
  * <b>Postcondition:</b> canbuy value set 
  * @param value (boolean)
  * */


public void setCanBuy(boolean value) {
	this.canbuy=value;
}

/**<b>Transformer:</b> Set the played with play value(Player has decided what to play )
 *<b>Postcondition:</b> played  equals play
 * @param play (boolean)
 * */

public void SetPlay(boolean play) {
	this.played=play;
	
}

/**<b>Accessor:</b> Returns if the Player has played     
 *<b>Postcondition:</b> Player's value played returned
 * @return played if player has finished his turn
 * */

public boolean returnIfPlay() {
	return this.played;
	
}

/**<b>Transformer:</b> Sets Player's validcardplacement with value x
 * <b>Postcondition:</b> validcardplacement changed
 *@param x : value to be set to validcardplacement 
 * */


public void setValidCardPlacement(boolean x) {
	
	this.validcardplacement=x;
}

/**<b> Accessor:</b> Returns validcardplacement of Player
 * <b>Postcondition:</b> validcardplacement returned
 * @return validcardplacement
 * */


public boolean getValidCardPlacement() {
	
	return this.validcardplacement;
			
}

/**<b>Transformer:</b> Sets valiChoise with parameter x
 * <b>Postcondition:</b> validchoise changed
 * @param x (boolean value)
 * */




public void setValidChoise(boolean x) {
	
	this.validchoise=x;
}

/**<b> Accessor:</b> Returns validchoise of Player
 * <b>Postcondition:</b> validchoise returned
 * @return validchoise
 * */



public boolean getValidChoise() {
	
	return this.validchoise;
			
}


/**<b> Accessor:</b> Returns Player's trainsOnHand
 * @return trainsOnHand
 * */

public TrainHand returnTrainHand(){
	return this.trainCardsOnHand;
}

/**<b> Accessor:</b> Returns Player's ticketsOnHand
  * @return ticketsOnHand
  * */

public TicketHand returnTicketHand(){
	return this.ticketsOnHand;
}

/**<b> Accessor:</b> Returns Player's Railyard.
 * <b>Postcondition:</b> Player's Railyard returned.
 * @return railyard
 * */
 public Railyard returnRailyard() {
	return this.railyard;
	
}

/**<b>Transformer:</b> Updates the score according to the points of TicketsOnHand , BigCitiesBonusCards owned and DestinationCards
 * purchased.
 * <b>Postcondition:</b> 
 * Score has been calculated once again.
 * */

public void updateScore() {
	this.scoreboard.Setscore(-this.PointsTicketsHands()+this.scoreboard.PointsInHold()+this.scoreboard.BigCitiesPoints());
}

/**<b> Accessor:</b>Returns Player's object OnTheTrack 
 * <b>Postcondition:</b> OnTheTrack returned
 * @return onthetrack
 * */



public OnTheTrack returnOnTheTrack() {
	return this.onthetrack;
}

/**<b> Accessor:</b> Returns scoreboard of Player
 * <b>Postcondition:</b> Scoreboard returned
 * @return scoreboard
 * */

public Scoreboard returnScoreboard() {
	return this.scoreboard;
}

}
