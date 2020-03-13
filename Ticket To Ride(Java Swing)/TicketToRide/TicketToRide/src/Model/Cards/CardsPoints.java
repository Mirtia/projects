package Model.Cards;

public class CardsPoints extends Cards{
private int points;

/**<b>Constructor:</b>
 * Creates a CardsPoints card
 * @param points :points(value)
 * @param ID : number of CardsPoints
 */
public CardsPoints(int ID,int points) {
	
		super(ID);
		this.points=points;
		
}
/**<b>Accessor:</b>  Returns the value of the CardsPoints card     
 *<b>Postcondition:</b> The points(value) of CardsPoints card has been returned.
 *@return points
 **/
public int ReturnPoints() {
	
	return this.points;
	
}

@Override
public int returnID() {
return super.returnID();
}
}
