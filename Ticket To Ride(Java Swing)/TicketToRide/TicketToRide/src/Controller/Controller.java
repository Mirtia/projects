package Controller;


import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import Model.Cards.BigCitiesBonusCards;
import Model.Cards.ColorTrainCards;
import Model.Cards.DestinationCards;
import Model.Cards.TrainCards;
import Model.Deck.AvailableBigCitiesBonusCards;
import Model.Deck.RevealedTrainCards;
import Model.Players.Player;
import Model.Round.Round;



public class Controller {
private Player[] players;
private Round round;
private boolean gamefinished;
private boolean gamestarted;
private ArrayList<DestinationCards> destideck;
private ArrayList<TrainCards> traindeck;
private RevealedTrainCards rtc;
private AvailableBigCitiesBonusCards b;


/**<b>Constructor</b>
 * Instantiate two Players and let the Round Begin(not even game)
 * */
public Controller(){
 b=new AvailableBigCitiesBonusCards();
 rtc=new RevealedTrainCards();
 players=new Player[2];
 players[0]=new Player(0);
 players[1]=new Player(1);
 round=new Round(players);
 this.gamefinished=false;
 destideck=new ArrayList<DestinationCards>();
 traindeck=new ArrayList<TrainCards>();

 
 try {
	 this.readDestinationCards("resources/files/destinationCards.csv");
	 this.readBigCities("resources/files/bigcitiesbonusCards.csv");
	 this.readTrainCards("resources/files/trainCards.csv");
	 }
 catch( Exception e){
	    	e.printStackTrace();
	 }
 
}

/**<b>Transformer:</b> Initializes the game. Makes necessary calls of functions. 
 *<b>Postcondition:</b> gamestarted=true
 * 
 * */
public void startGame() {
	
	
	this.returnPlayer(0).returnTrainHand().AddOneCard(this.traindeck.get(95));
	this.returnPlayer(1).returnTrainHand().AddOneCard(this.traindeck.get(94));
	this.removeTraindeck(95);
	this.removeTraindeck(94);
	Collections.shuffle(this.traindeck);
	this.rtc.AddList(this.returnTrainDeck(5));
	removetillTraindeck(5);
	
	
	Collections.shuffle(this.destideck);
	this.returnPlayer(0).AddTrainCardsOnHand(this.returnTrainDeck(6));
	removetillTraindeck(6);
	this.returnPlayer(1).AddTrainCardsOnHand(this.returnTrainDeck(6));
	removetillTraindeck(6);
	Collections.shuffle(this.traindeck);
	this.gamestarted=true;
	

	
}

/**<b> Accessor:</b> Returns gamestarted
 * <b>Postcondition:</b> 
 * @return gamestarted -value (gamestarted)
 * */
public boolean returnGameStarted() {
	return this.gamestarted;
}

/**<b>Transformer:</b> Determines randomly which Player will play first.
 *<b>Postcondition:</b> Random player chosen.
 * 
 * */

public void determineFirstToPlay() {
	
	
	int random = (int)(Math.random() * 2);
	players[random].SetPlay(false);

}

/**<b>Accessor:</b>  Returns the winner of the game     
  *<b>Postcondition:</b> Winner returned
  *@return winner of Game (int form)
  **/

public int GetWinner() {
	if (this.gamefinished) {
		if(players[0].GetScore()>players[1].GetScore())	return players[0].GetID();
		else if (players[0].GetScore()<players[1].GetScore())return players[1].GetID();
	          
	    else
	    	if(players[0].returnScoreboard().returnDestinationCards().size()>players[1].returnScoreboard().returnDestinationCards().size())
		            return players[0].GetID();
	        else if(players[0].returnScoreboard().returnDestinationCards().size()<players[1].returnScoreboard().returnDestinationCards().size())
	                return players[1].GetID();
		     
	        else
		    	if(players[0].returnScoreboard().returnBigCitiesBonusCards().size()>players[1].returnScoreboard().returnBigCitiesBonusCards().size())
		    		return players[0].GetID();
		    	else  if (players[0].returnScoreboard().returnBigCitiesBonusCards().size()<players[1].returnScoreboard().returnBigCitiesBonusCards().size())
		    		return players[1].GetID();
		
		
		    	else return 2;
	}      
	else return 0;
	
	
	
	
}

/**<b> Accessor:</b> Returns RevealedTrainCards 
 * <b>Postcondition:</b> RevealedTrainCards returned.
 * @return rtc - RevealedTrainCards oject
 * */



public RevealedTrainCards returnFaceup(){
	return this.rtc;
}

/**<b>Accessor</b> Returns DestinationDeck till some kind of index
 * <b>Postcondition:</b> Deck returned
 * @return temp - (whole deck or some proportion of it)
 * @param x -index
 * */

public ArrayList<DestinationCards> returnDestinationDeck(int x) {
	if(x==46) return this.destideck;
	else {
	ArrayList <DestinationCards> temp = new ArrayList<DestinationCards>();
	for(int i=0;i<x;i++) {
		temp.add(this.destideck.get(i));
		
	}
	
	return temp;
	}
	
}

/**<b>Accessor</b> Returns TrainDeck till some kind of index
 * <b>Postcondition:</b> Deck returned
 * @return temp - (whole deck or some proportion of it)
 * @param x- index
 * */

public ArrayList<TrainCards> returnTrainDeck(int x) {
	if(x==96) return this.traindeck;

	else {
		ArrayList <TrainCards> temp = new ArrayList<TrainCards>();
		for(int i=0;i<x;i++) {
			temp.add(this.traindeck.get(i));
			
			
		}
		
		return temp;
		}
}



/**<b> Accessor:</b> Returns AvailableBigCitiesBonusCards
 * <b>Postcondition:</b> AvailableBigCitiesBonusCards returned
 * @return b - AvailableBigCitiesBonusCards object
 * */


public AvailableBigCitiesBonusCards returnAvailableBigBonus() {
	return b;
	
}
/**<b>Transformer:</b> Removes DestinationCards object at specific index.
 *<b>Postcondition:</b> DestinationCards removed.
 * @param index -index 
 * */

public void removeDestideck(int index) {
	this.destideck.remove(index);
	}

/**<b>Transformer:</b> Removes TrainCards object at specific index.
 *<b>Postcondition:</b> TrainCards removed.
 * @param index -index 
 * */
public void removeTraindeck(int index) {
	this.traindeck.remove(index);
	
}

/**<b>Transformer:</b> Removes till index (sublist)-DestinationDeck
 *<b>Postcondition:</b> Sublist removed
 * @param index -index 
 * */

public void removetillDestideck(int index) {
	 this.destideck.subList(0,index).clear();
	}


/**<b>Transformer:</b> Removes till index (sublist)-TrainDeck
 *<b>Postcondition:</b> Sublist removed
 * @param index -index 
 * */
public void removetillTraindeck(int index) {
	this.traindeck.subList(0,index).clear();
}

/**<b>Transformer:</b> Sets gamefinished to true
 *<b>Postcondition:</b> gaemfinished==true
 **/


public void SetGameFinished() {
	this.gamefinished=true;
	
}
/**<b>Accessor:</b> Returns Player from specific ID(index) of players[]
 * <b>Postcondition:</b> Player returned
 * @return players[ID] Player 
 * @param ID - ID of Player
 * */

public Player returnPlayer(int ID){
	return players[ID];
}
/**<b> Accessor:</b> Returns boolean that determines whether game has finished 
 * <b>Postcondition:</b> gamefinished returned
 * @return gamefinished -gamefinished
 * */

public boolean getGamefinished() {
	return this.gamefinished;
}



/**<b>Transformer:</b> Initializes DestinationCards.
 *<b>Postcondition:</b> Objects(46)created.
 * @param filePath -path
 **/

public void readDestinationCards(String filePath) throws FileNotFoundException, IOException {
    BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
    String sCurrentLine = "";
    int i = 0;
    while ((sCurrentLine = br.readLine()) != null) {
        if (i == -1) {
            i = 0;
            continue;
        }
        String[] splitLine = sCurrentLine.split(",");
        String id = splitLine[0];
        
        String from = splitLine[1];
        String to = splitLine[2];
        System.out.println(to + from);
        int score = Integer.parseInt(splitLine[3]);
        String colorsList = splitLine[4];
        String[] splitColors = colorsList.split("-");
        ArrayList<ColorTrainCards> colors = new ArrayList<ColorTrainCards>();
        for(int p=0;p<splitColors.length;p++) {
        	colors.add(ColorTrainCards.valueOf(splitColors[p]));
         }
        System.out.println(Integer.parseInt(id));
        DestinationCards p=new DestinationCards(Integer.parseInt(id),from,to,colors,score);
      
        this.destideck.add(p);
        
        
        
          
    }
    br.close();
    }

/**<b>Transformer:</b> Initializes BigCitiesBonusCards.
  *<b>Postcondition:</b> Objects(6)created.
  * @param filePath -path
  **/
public void readBigCities(String filePath) throws FileNotFoundException, IOException {
    BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
    String sCurrentLine = "";
    int i = 0;
    int k=0;
    while ((sCurrentLine = br.readLine()) != null) {
        if (i == -1) {
            i = 0;
            continue;
        }
        String[] splitLine = sCurrentLine.split(",");
        String id= splitLine[0];
        String name = splitLine[1];
        int score = Integer.parseInt(splitLine[2]);
        String imagePath = splitLine[3];
        System.out.println(id +" "+ name );
        BigCitiesBonusCards c=new BigCitiesBonusCards(Integer.parseInt(id),score,name);
        System.out.println(id);
        b.InserttoList(c,k);
        k++;
      }
	br.close();
}

/**<b>Transformer:</b> Initializes TrainCards.
 *<b>Postcondition:</b> Objects(96)created.
 * @param filePath -path
 **/

public void readTrainCards(String filePath) throws FileNotFoundException, IOException {
	    BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
	    String sCurrentLine = "";
	    int i = 0;
	    while ((sCurrentLine = br.readLine()) != null) {
	        if (i == -1) {
	            i = 0;
	            continue;
	        }
	        String[] splitLine = sCurrentLine.split(",");
	        String id= splitLine[0];
	        System.out.println(id);
	        String color = splitLine[1];
	       
	        TrainCards trainc=new TrainCards(Integer.parseInt(id),ColorTrainCards.valueOf(color));
	       
	        this.traindeck.add(trainc);
	        
	    
	      }
	    br.close();
		
}
/**<b> Accessor:</b> Returns Round
 * <b>Postcondition:</b> Round returned
 * @return round -round
 * */


public Round returnRound() {
	return this.round;
	
}

/**<b>Accessor:</b>  Returns round number     
 *<b>Postcondition:</b> Number returned
 *@return sum  of rounds
 **/

public int GetRoundNumber() {
	return round.GetSumRounds();
	
}


}
