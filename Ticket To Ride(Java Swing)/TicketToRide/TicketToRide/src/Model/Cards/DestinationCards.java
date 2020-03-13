/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Cards;


import java.util.ArrayList;




public class DestinationCards extends CardsPoints{


private String from;
private String to;

private ArrayList<ColorTrainCards> color=new ArrayList<ColorTrainCards>();
private int[] sum;
/**<b>constructor</b>
 * Creates a DestinationCards card
 * @param ID : card number
 * @param from : The town of departure
 * @param to : The town of arrival
 * @param color : Colors that need to be obtained to buy the card
 * @param points : points of the DestinationCards card
 * **/

public DestinationCards(int ID,String from,String to, ArrayList<ColorTrainCards> color,int points) {
	
	super(ID,points);
	this.from=from;
	this.to=to;
	this.color=color;
	sum=new int[8];
	
	this.setSumOfEachColor();
	}

@Override
public int ReturnPoints() {
	return super.ReturnPoints();

}
/**<b>Accessor</b>
 * @return to
 * */
public String returnTo() {
	return this.to;
}

/**<b>Accessor</b>
 * @return from
 * */


public String returnFrom() {
	return this.from;
}

/**<b>Accessor</b>
 * 
 * @return color
 * */

public ArrayList<ColorTrainCards> returnArray() {
	return this.color;
	
}
/**<b>Transformer</b>
 * Sets the sum of each color the Destination Card has
 * */


public  void setSumOfEachColor() {
	int p=color.get(0).getValue();
	sum[p]++;
	
	for(int i=1;i<color.size();i++) {
		if(color.get(i)==null)
		   break;
		if(color.get(i).getValue()!=p)
		   p=color.get(i).getValue();
		
		sum[p]++;    
		
	}

}

/**<b>Accessor</b>
 * @return sum(of colors)
 * */
public int[] getSumOfEachColor() {
	return this.sum;
	
}

@Override 
public String toString() {
	return "desCard"+super.returnID();
}



@Override
public int returnID() {
	return super.returnID();
	
}
}



	
	
	
	
	
	
	
	
	
	
	
	
	
	



















