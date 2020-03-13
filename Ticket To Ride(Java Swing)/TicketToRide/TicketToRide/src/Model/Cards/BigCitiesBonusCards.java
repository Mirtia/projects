package Model.Cards;

public class BigCitiesBonusCards extends CardsPoints{
   

private String town;

/**<b>Constructor:</b>
 * Creates and initializes the fields of a BigCitiesBonusCards card
 * @param points : Points the card holds
 * @param ID :The number of each card(identity)
 * @param town: Town that the BigCitiesBonusCards holds
 * **/
public BigCitiesBonusCards(int ID,int points,String town) {
    super(ID,points);
	this.town=town;
    
	
	
}


@Override
public int ReturnPoints() {
	
	return super.ReturnPoints();
	
}

/**<b>Accessor:</b> Returns the name of the town the BigCitiesBonusCard object holds      
   <b>Postcondition:</b> The name has been returned
   @return town name 
 * */

public String getNameTown() {
	return this.town;
}



}
