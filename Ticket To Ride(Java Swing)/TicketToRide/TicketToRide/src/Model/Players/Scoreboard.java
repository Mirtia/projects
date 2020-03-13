package Model.Players;

import java.util.ArrayList;


import Model.Cards.BigCitiesBonusCards;
import Model.Cards.DestinationCards;

public class Scoreboard {

private ArrayList<BigCitiesBonusCards> bcbc;
private ArrayList<DestinationCards> dtc;
private int score;
private int[] sumbigcities;//sum of the Cities of the cards contained in dtc

/**<b>Constructor</b>
 *Initializes an ArrayList of BigCitiesBonusCards and DestinationCards
 **/

public Scoreboard() {
	sumbigcities=new int[6];
	this.score=0;
	bcbc=new ArrayList<BigCitiesBonusCards>();
	dtc=new ArrayList<DestinationCards>();
	
	}

/**<b>Transformer:</b> After each method that changes the score happens Setscore would be called
 *<b>Postcondition:</b> Score is changed by the specific value
 *@param value: Value is the quantity to change score
 */


public void Setscore(int value) {
	
	score=value;
}


/**<b>Accessor:</b> Returns Current score of the PlayerF's Scoreboard      
 *<b>Postcondition:</b> Score returned
 *@return score
  **/

public int Getscore() {
	
	return this.score;
	
}



/**<b>Transformer:</b> Add BigCitiesBonus card to bcbc
  *<b>Postcondition:</b> Card added
  *@param b BigCitiesBonusCardscard  to be added
  */

public void  AddBigCitiesBonusCards(BigCitiesBonusCards b) {
	
 bcbc.add(b);
	
}



/**<b>Transformer:</b> Add DestinationCards card to bcbc
 *<b>Postcondition:</b> Card added
 *@param c DestinationCard be added
 */


public void  AddDestinationCards(DestinationCards c) {
	
 dtc.add(c);
	
}

public ArrayList<BigCitiesBonusCards> returnBigCitiesBonusCards() {
	return this.bcbc;
}


public ArrayList<DestinationCards> returnDestinationCards() {
	return this.dtc;
}

public int PointsInHold() {
	int sum=0;
	for(int i=0;i<dtc.size();i++)
		sum+=dtc.get(i).ReturnPoints();
	return sum;
}

public int BigCitiesPoints() {
	int sum=0;
	for(int i=0;i<bcbc.size();i++)
		sum+=bcbc.get(i).ReturnPoints();
	return sum;
	
	
}

public void calculateTowns() {
	System.out.println("Function reached ........................................................");
	for(int i=0;i<dtc.size();i++)
		sumbigcities[i]=0;
	for(int i=0;i<dtc.size();i++) {
		if(((dtc.get(i).returnTo()).equals("Miami"))||((dtc.get(i).returnFrom()).equals("Miami")))
			++sumbigcities[0];
		if(((dtc.get(i).returnTo()).equals("Seattle"))||((dtc.get(i).returnFrom()).equals("Seattle")))
			++sumbigcities[1];
		if(((dtc.get(i).returnTo()).equals("Dallas"))||((dtc.get(i).returnFrom()).equals("Dallas")))
			sumbigcities[2]++;
		if(((dtc.get(i).returnTo()).equals("Chicago"))||((dtc.get(i).returnFrom()).equals("Chicago")))
			sumbigcities[3]++;
		if(((dtc.get(i).returnTo()).equals("LosAngeles"))||((dtc.get(i).returnFrom()).equals("LosAngeles")))
			sumbigcities[4]++;
		if(((dtc.get(i).returnTo()).equals("NewYork"))||((dtc.get(i).returnFrom()).equals("NewYork")))
			sumbigcities[5]++;
		
		
	}
	
}
public int[] returnSumBigCities() {
	return this.sumbigcities;
}

public ArrayList<Integer> CheckForBigCities() {
	ArrayList<Integer> cardstoGet=new ArrayList<Integer>();
	for(int i=0;i<sumbigcities.length;i++) {
		if(sumbigcities[i]>=3) {
			cardstoGet.add(i);
		}
	}
		return cardstoGet;}
	
	
}



