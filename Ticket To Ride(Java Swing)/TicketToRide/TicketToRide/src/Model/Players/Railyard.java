package Model.Players;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Model.Cards.TrainCards;

public class Railyard {
	
	private boolean hasMoved;//Railyards top cards from ArrayLists have moved 
	private ArrayList<TrainCards> yellow;
    private ArrayList<TrainCards> blue;
    private ArrayList<TrainCards> red;
    private ArrayList<TrainCards> white;
    private ArrayList<TrainCards> black;
    private ArrayList<TrainCards> green;
    private ArrayList<TrainCards> purple;
    private ArrayList<TrainCards> orange;
    private ArrayList<TrainCards> cardsremoved;
    
	private int[] sumofeachcolor;//Sum of LocomotivestoColor in Railyard

	
   /**<b>Constructor</b>
	 *Initializes the ArrayLists containing colors
	 **/
	
	public Railyard() {
		yellow=new ArrayList<TrainCards>();
		blue=new ArrayList<TrainCards>();
		red=new ArrayList<TrainCards>();
	    white=new ArrayList<TrainCards>();
	    black=new ArrayList<TrainCards>();
	    green=new ArrayList<TrainCards>();
	    purple=new ArrayList<TrainCards>();
	    orange=new ArrayList<TrainCards>();
	    this.sumofeachcolor=new int[8];
	    cardsremoved=new ArrayList<TrainCards>();
	   
		
		
	}
   
    /**<b> Accessor:</b> Get cards that were removed from the Player this Round (to be placed OnTheTrack)
	  * @return cardremoved : Top cards of Railyard
	  * 
	  * */
	
	
	
   public ArrayList<TrainCards> getcardsremoved(){
	   return this.cardsremoved;
   }
   
   /**<b>Transformer:</b> Deletes the contents of cardsremoved
    *<b>Postcondition:</b> cardsremoved clear for next Round
    * 
    * */
   
   public void clearcardsremoved() {
	   this.cardsremoved.clear();
   }
   
   /**<b>Transformer:</b> Clears the ArrayList of color=index (used for TrainRobbing)
    *<b>Postcondition:</b> ArrayList cleared
    * @param index -color
    * */
   public void RemoveList(int index) {
	  
	   switch(index){
		case 0:
			this.yellow.clear();
			break;
		case 1:
			 this.blue.clear();
			 break;
		case 2:
			this.red.clear();
			break;
		case 3:
			this.white.clear();
			break;
		case 4:
			this.black.clear();
			break;
		case 5:
			this.green.clear();
			break;
		case 6:
			this.purple.clear();
			break;
		case 7:
			this.orange.clear();
			break;
	    default:
	    	
		}
	 
    }
	
  /**<b>Transformer:</b>Set hasMoved to some value
    *@deprecated
    * */
   
	public void setMovedValue(boolean value) {
		this.hasMoved=value;
	}
	
	/**<b>Accessor:</b> Returns HasMoved value      
	 *<b>Postcondition:</b> Returned HasMoved value
	 *@return hasMoved if the player HasMoved
	 **/

	
	public boolean ReturnIfMoved() {
		return this.hasMoved;
	}
    
	 /**<b>Accessor:</b>Returns ArrayList(specific color)    
	  *<b>Postcondition:</b> ArrayList returned
	  *@return ArrayList(e.g. this.yellow )
	  *@param index: return ArrayList of specific position
	  **/

	
	
	public ArrayList<TrainCards> returnList(int index) {
		switch(index){
		case 0:
			return this.yellow;
		case 1:
			return this.blue;
		case 2:
			return this.red;
		case 3:
			return this.white;
		case 4:
			return this.black;
		case 5:
			return this.green;
		case 6:
			return this.purple;
		case 7:
			return this.orange;
	    default:
	    	return null;
		}
		
		
		
	}

   /**<b> Accessor:</b> returns size of specific ArrayList
     * @param index -color List
     * */
	
	public int returnsum(int index) {
		switch(index){
		case 0:
			return this.yellow.size();
					
		case 1:
			return this.blue.size();
		case 2:
			return this.red.size();
		case 3:
			return this.white.size();
		case 4:
			return this.black.size();
		case 5:
			return this.green.size();
		case 6:
			return this.purple.size();
		case 7:
			return this.orange.size();
	    default:
	    	return 0;
		}}
	
	
	/**<b>Transformer:</b> Moves top Cards to RailYard.
	  *<b>Postcondition:</b> Phase 1 ended.
	  * 
	  * */
	
	public void  MoveTopCardsonRailyard() {
		 for(int index=0;index<8;index++) {
			   switch(index){
				case 0:
					if(!yellow.isEmpty()) {
					this.cardsremoved.add(yellow.get(yellow.size()-1));
					this.yellow.remove(yellow.size()-1);
					}
					break;
				case 1:
					 if(!blue.isEmpty()) {
					 this.cardsremoved.add(blue.get(blue.size()-1));
					 this.blue.remove(blue.size()-1);
					 }
					 break;
				case 2:
					if(!red.isEmpty()) {
					this.cardsremoved.add(red.get(red.size()-1));
					this.red.remove(red.size()-1);
					}
					break;
				case 3:
					if(!white.isEmpty()) {
					this.cardsremoved.add(white.get(white.size()-1));
					this.white.remove(white.size()-1);
					}
					break;
				case 4:
					if(!black.isEmpty()) {
					this.cardsremoved.add(black.get(black.size()-1));
					this.black.remove(black.size()-1);
					}
					break;
				case 5:
					if(!green.isEmpty()) {
					this.cardsremoved.add(green.get(green.size()-1));
					this.green.remove(green.size()-1);
					}
					break;
				case 6:
					if(!purple.isEmpty()) {
					this.cardsremoved.add(purple.get(purple.size()-1));
					this.purple.remove(purple.size()-1);
					}
					break;
				case 7:
					if(!orange.isEmpty()) {
					this.cardsremoved.add(orange.get(orange.size()-1));
					this.orange.remove(orange.size()-1);
					}
					break;
			   
			    	
				}
			 }
		
		
	}
	
	/**<b>Transformer:</b> Adds a TrainCards c to specific ArrayList
	  *<b>Postcondition:</b> Traincards c added
	  * @param index
	  * @param c -TrainCards to be added to index List (index indicates ColorTrainCards of the ArrayList)
	  * */
	
	public void addToListIndex(int index,TrainCards c) {
		
			   switch(index){
				case 0:
					this.yellow.add(c);
					break;
				case 1:
					 this.blue.add(c);
					 break;
				case 2:
					this.red.add(c);
					break;
				case 3:
					this.white.add(c);
					break;
				case 4:
					this.black.add(c);
					break;
				case 5:
					this.green.add(c);
					break;
				case 6:
					this.purple.add(c);
					break;
				case 7:
					this.orange.add(c);
					break;
			    default:
			    	break;
				}
			 
		
		
		
	}
	
	/**<b> Accessor:</b>Returns sumofeachcolor array
	  *@return sumofeachcolor
	  * 
	  * */
	
	public int[]  Getsumofeachcolor() {
		//System.out.println("null pointer");
		return this.sumofeachcolor;
		
		
	}
   
	/**<b>Transformer:</b> Increments sum of Locomotives at specific index.
	  *<b>Postcondition:</b> Sum incremented.
	  * @param index -index..
	  * */
	public void SetSumofLocomotives(int index) {
	sumofeachcolor[index]++;	
		
	}
	
	/**<b>Transformer:</b> Sorts the ArrayLists(fields of Railyard) according to TrainCard ID.
	  *<b>Postcondition:</b> Cards sorted from lower to higher ID.
	  **/
	
	public void sortList() {
		ListComparator compare=new ListComparator();
		for(int i=0;i<8;i++)
        Collections.sort(this.returnList(i),compare.reversed());		
		
	}
	
	}


//Compares TrainCards according to ID
class ListComparator implements Comparator<TrainCards>{

	@Override
	public int compare(TrainCards o1, TrainCards o2) {
		return (o2.returnID() - o1.returnID());
    }
	}
	







	
