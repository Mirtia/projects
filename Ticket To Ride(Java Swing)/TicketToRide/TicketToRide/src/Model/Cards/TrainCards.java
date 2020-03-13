package Model.Cards;

public class TrainCards extends Cards {
	private ColorTrainCards color;
	/**<b>Constructor</b>
	 * Creates a TrainCards Card
	 * @param ID :Type of card
	 * @param color : color of Card
	 * 
	 * */
	public TrainCards(int ID,ColorTrainCards color){
		super(ID);
		this.color=color;
	}
	/** <b>Accessor:</b> Returns the color of the TrainCard object     
       <b>Postcondition:</b> color returned
       @return TrainCards color
	 * */
	
    public ColorTrainCards returnColor() {
		return this.color;
    	
    }
    
    @Override 
    public String toString() {
    	return color.toString();
    }

    
    @Override
    public int returnID() {
    	return super.returnID();
    	
    }
}
