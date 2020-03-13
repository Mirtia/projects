package View;

import java.awt.Dimension;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Model.Cards.DestinationCards;

public class MyTicketsWindow extends JFrame {
	
	Image image;
	
	/**<b>Constructor:</b> 
	  *@param temp ArrayList of DestinationCards to be displayed(Player owns them)
	  *@param ID The ID of Player
	  * */
	public MyTicketsWindow(ArrayList<DestinationCards> temp,int ID){

		
		this.setResizable(false);
		this.setSize(10+temp.size()*65, 140);
		this.setTitle("Draw Destination Cards");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    
		this.setVisible(true);
	    this.setLayout(null);
		    

		for(int i=0;i<temp.size();i++) {
			
						
				try {
					image=ImageIO.read(new File("resources/images/destination_Tickets/"+(temp.get(i).toString()+".jpg")));
				} catch (IOException e) {
					e.printStackTrace();
				}
				image= image.getScaledInstance( 65, 100,  java.awt.Image.SCALE_SMOOTH ) ;
			
			JLabel p=new JLabel();
			p.setIcon(new ImageIcon(image));
	        p.setSize(65,100);
	        this.add(p);
	        p.setLocation(5+i*60,0);
	     }
   }
}
	        
	       		
