package Model.Round;

import java.util.ArrayList;

import Model.Cards.ColorTrainCards;
import Model.Cards.TrainCards;
import Model.Players.Player;

public class Round {
	
   private Player[] players=new  Player[2];
   private int number;
  
   /**<b>Constructor:</b>	
    * Creates a Round object 
    * @param players: array of players
    **/
	
	
   public Round(Player[] players) {
		
		this.players=players;
		number=1;
	
	}

	/**<b>Transformer:</b> Makes placement if choise and placement is valid
	  *<b>Postcondition:</b> Sets validchoise and validcardplacement accordingly
	  *@param p : Player p 
	  */
    
	public void checkIfValidChoise(Player p) {
		
		int trainrobbing=0;
		int indextoremove=0;//list to remove when trainrobbing=1
		ArrayList<TrainCards> temp=p.GetHandCardstoPlaceRailyard();
		int sum[]=new int[9];//sum of each color
		int numberofchosen[]=new int [9];
		int differentcolors=0;//how many different colors found
		boolean check=false;

		
		
		for(TrainCards x:temp) {
			sum[x.returnColor().getValue()]++;
			
		}
		//System.out.println("Sum of Locomotive" + sum[8]);
		//condition 2:3 cards of 3 different colors,no Locomotive alllowed
		for(int i=0;i<8;i++) {
			if(sum[i]!=0) {numberofchosen[differentcolors]=i;
			differentcolors++;}//finds the array of the different colors
		    if((differentcolors>1)&&(sum[8]>=1)){//you cant have more than one different color and a Locomotive sum[8]
		    p.setValidChoise(false);
		    break;}
		    
		}
		
		if((differentcolors==3)&&sum[8]==0) {
			for(int i=0;i<3;i++) {
				if((sum[numberofchosen[i]]==1))check=true;
				else check=false;
				
			}
			p.setValidChoise(check);
		}
			
		if(differentcolors==1) {
			//System.out.println("AASAA");
		   if((sum[numberofchosen[0]]>=2)) { p.setValidChoise(true);}
		  
		   if((sum[numberofchosen[0]]==1)&&(sum[8]>0)) {p.setValidChoise(true);}
		}
		
		
		
		
		
		
		if(p.getValidChoise()) {
	
			if(differentcolors==1) {
	
				if(((sum[numberofchosen[0]]+sum[8])>players[0].returnRailyard().returnsum(numberofchosen[0])&&((sum[numberofchosen[0]]+sum[8])>players[1].returnRailyard().returnsum(numberofchosen[0]))))
				{		
					trainrobbing++;
					indextoremove=numberofchosen[0];
					p.setValidCardPlacement(true);
		   
				}
				if(trainrobbing==1) {
			
					players[0].returnRailyard().RemoveList(indextoremove);
					players[1].returnRailyard().RemoveList(indextoremove);
					System.out.println("Why AM I");
		
					for(int i=0;i<temp.size();i++) {
						if(temp.get(i).returnColor()==ColorTrainCards.Locomotive)
							p.returnRailyard().SetSumofLocomotives(indextoremove);
			
							p.returnRailyard().addToListIndex(indextoremove, temp.get(i));
			
			
					}			
			
				}
			
		
			}
			if(differentcolors==3){	
				int flag=0;
				
				for(int i=0;i<numberofchosen.length;i++) {
					System.out.println(numberofchosen[i]);
				}
				
				for(int i=0;i<temp.size();i++) {
					indextoremove=numberofchosen[i];
					
					System.out.println("Player 0 -"+indextoremove + " "+players[0].returnRailyard().returnsum(indextoremove));
					System.out.println("Player 1 -"+ indextoremove +"  " +players[1].returnRailyard().returnsum(indextoremove));
					if(((players[0].returnRailyard().returnsum(indextoremove))==0)&&(((players[1].returnRailyard().returnsum(indextoremove))==0))) {
				      
						
					
				    }else {
						flag=1;
					}
			        
				
					}	
					
				if(flag==0) 	{
				  for(int i=0;i<3;i++) {
					  System.out.println("temp at "+temp.get(i).returnColor().getValue());
					  p.returnRailyard().addToListIndex(temp.get(i).returnColor().getValue(), temp.get(i));
					  
				  }
				  p.setValidCardPlacement(true);
				 
			    
				}
			  
				
			}


}}
		
		
		
		
		


	/**<b>Accessor:</b> Returns the player that it's his turn.    
	  *<b>Postcondition:</b>Player that plays returned
	  *@return Player players[id]
	  */

 
  
	public Player returnPlayerPlaying() {
		
		if(players[0].returnIfPlay())
			return players[1];
		else 
			return players[0];
		
		
	}
	
	/**<b>Accessor:</b> Returns the player that his turn has not come yet.    
	  *<b>Postcondition:</b>Player that doesn't plays returned
	  *@return Player players[id]
	  */

	
	public Player returnPlayernotPlaying() {
		

		if(!players[0].returnIfPlay())
			return players[1];
		else 
			return players[0];
	}
	
	/**<b>Transformer:</b> Changes players' turns
	  *<b>Postcondition:</b> Players turn(hasPlayed) exchanged values
	  *@param ID  -ID
	  */

	
	public void TurnSetTrue(int ID) {
	     players[ID].SetPlay(true);
	     if(ID==1)	 players[0].SetPlay(false);
	     else        players[1].SetPlay(false);
	     number++;
	        
		
		
    };
	
	
	
	 /**<b>Accessor:</b> Returns sum of Rounds.      
	  *<b>Postcondition:</b> Sum Rounds returned
	  *@return number(sum of rounds);
	  */

	
	public int GetSumRounds() {
		
		return this.number;
	};
	
	
	}
