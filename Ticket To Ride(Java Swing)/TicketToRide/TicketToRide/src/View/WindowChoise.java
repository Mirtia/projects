package View;


import java.awt.Dimension;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import Model.Cards.DestinationCards;



public class WindowChoise extends JFrame{
	private JCheckBox[] choises;
	private Image image;
	
	
	private ArrayList<DestinationCards> cards;
	private ArrayList<DestinationCards> chosen;
	private ArrayList<DestinationCards> notchosen;
 	private int ID;
	private JLabel playerch;
	private JButton button;
	private boolean choisemade;
	private Gui gui;
	
	
	/**<b>Constructor:</b>	
	 * Creates a Cards object 
	 * @param ID: number of Player......(-1)
	 * @param temp: DestinationCards to chose
	 * @param gui: The Gui object created 
	 **/
	public WindowChoise(ArrayList<DestinationCards> temp,int ID,Gui gui){
		this.gui=gui;
		this.choisemade=false;
		this.choises=new JCheckBox[temp.size()];
		this.ID=ID;
		this.cards=new ArrayList<DestinationCards>();
		this.chosen=new ArrayList<DestinationCards>();
		this.notchosen=new ArrayList<DestinationCards>();
		this.cards=temp;
		this.setResizable(false);
		this.setSize(600, 300);
		this.setTitle("Draw Destination Cards");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		
	    this.setLayout(null);
		
		playerch=new JLabel();
	    playerch.setText("PLAYER "+(this.ID+1)+" CHOISE");
	    playerch.setBounds(this.getWidth()/2-50,20,200,20);
	    this.add(playerch);
	    button=new JButton();
	    button.setText(" OK ");
	    button.setBounds(this.getWidth()/2-30,230,60,20);
	    button.addActionListener(new ChoiseListener());
	    this.add(button);
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		for(int i=0;i<cards.size();i++) {
			try {
				image=ImageIO.read(new File("resources/images/destination_Tickets/desCard"+(cards.get(i).returnID())+".jpg"));
				image= image.getScaledInstance( 65, 100,  java.awt.Image.SCALE_SMOOTH ) ;		
			    JLabel p=new JLabel();
						     
			
			
			
			p.setIcon(new ImageIcon(image));
	        p.setSize(65,100);
	        this.add(p);
	        p.setLocation(15+i*100,60);
	        choises[i]=new JCheckBox();
	        choises[i].setBounds(15+i*100+20,180,30,30);
	        this.add(choises[i]);
	        
			} catch (IOException e) {
				
				e.printStackTrace();
			}}
		this.repaint();
		this.setVisible(true);
	}
	
	/**<b>Constructor:</b>	
	 * Creates a Cards object 
	 * @param ID: number of Player......(-1)
	 * @param temp: DestinationCards to chose
	 **/
	public WindowChoise(ArrayList<DestinationCards> temp,int ID){
		
		this.choisemade=false;
		
		this.ID=ID;
		this.cards=new ArrayList<DestinationCards>();
		this.chosen=new ArrayList<DestinationCards>();
		this.notchosen=new ArrayList<DestinationCards>();
		this.cards=temp;
		this.choises=new JCheckBox[cards.size()];
		this.setResizable(false);
		this.setSize(600, 300);
		this.setTitle("Draw Destination Cards");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    
		this.setVisible(true);
	    this.setLayout(null);
		
		playerch=new JLabel();
	    playerch.setText("PLAYER "+(this.ID+1)+" CHOISE");
	    playerch.setBounds(this.getWidth()/2-50,20,200,20);
	    this.add(playerch);
	    button=new JButton();
	    button.setText(" OK ");
	    button.setBounds(this.getWidth()/2-30,230,60,20);
	    button.addActionListener(new ChoiseListener());
	    this.add(button);
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		for(int i=0;i<cards.size();i++) {
			
			
			try {
				
				image=ImageIO.read(new File("resources/images/destination_Tickets/"+(cards.get(i).toString()+".jpg")));
				image= image.getScaledInstance( 65, 100,  java.awt.Image.SCALE_SMOOTH ) ;
			
			JLabel p=new JLabel();
			p.setIcon(new ImageIcon(image));
	        p.setSize(65,100);
	        this.add(p);
	        p.setLocation(15+i*100,60);
	        choises[i]=new JCheckBox();
	        choises[i].setBounds(15+i*100+20,180,30,30);
	        this.add(choises[i]);
	        this.repaint();
			} catch (IOException e) {
				System.out.println("resources/images/destination_Tickets/desCard"+(cards.get(i).returnID()+1)+".jpg");
				
				e.printStackTrace();
			}
	}
	
	
	
    
	
}
	
public  ArrayList<DestinationCards> returnSelected(){
	if(this.choisemade)
	return this.chosen;
	else return null;
		
};	

public boolean returnChoiseMade() {
	return this.choisemade;
}

public ArrayList<DestinationCards> returnNotSelected(){
	return this.notchosen;
	
	
}
 public WindowChoise returnthis() {
	 return this;
 }
 
/* This ActionListener is used for CheckBoxes
 * Each time the choises are made a JOptioPane appears to make sure that the Player ID has decided and if the choise is valid.
 * Then by using gui it calls the waitToRefresh() to make changes in Gui and finally in Controller
 * */
private class ChoiseListener implements ActionListener{
       
		@Override
		public void actionPerformed(ActionEvent e) {
			  chosen.clear();
			  notchosen.clear();
			  for(int i=0;i<cards.size();i++) {
				 
				  if(choises[i].isSelected())  chosen.add(cards.get(i));
				  else 		                notchosen.add(cards.get(i));
			  }  
			  
			if(cards.size()>4) {
				
			
		
			if(chosen.size()==0) {
				 JOptionPane.showMessageDialog(new JFrame(),"Choose at least one card","Better read the rules again...",JOptionPane.ERROR_MESSAGE);
			
			}else {
			   choisemade=true;
			   setVisible(false);
			   dispose();
		    }
					
			
			}else {//<=4
		    	 int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm",
		    		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    	 
		    		    if (response == JOptionPane.NO_OPTION) {
		    		       chosen.clear();
		    		       notchosen.clear();
		    		       choisemade=false;	
		    		    } else if (response == JOptionPane.YES_OPTION) {
		    		    	choisemade=true;
			    		    gui.waitToRefresh(returnthis());
			    		    returnthis().setVisible(false);
			    		    returnthis().dispose();
		    		    
		    		     
		    		    }
		    }
			
			}	
		}
		
}


