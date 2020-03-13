package Model.Cards;

public abstract class Cards{
	
private int ID;


/**<b>Constructor:</b>	
 * Creates a Cards object 
 * @param ID: number of Card(identity)
 **/

public Cards(int ID){
	this.ID=ID;
}	


/**<b>Accessor:</b>
 * <b>Postcondition:</b>
 * Returns the ID of the Cards object  
 * @return ID
 **/

public int returnID() {
	return ID;

	
}


}
