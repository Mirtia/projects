package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.Controller;

public class BigCitiesWindow extends JFrame {
	
	
	String []files;// the paths of the relative images to display on the JFrame
	private int[] sum; // the sum of DestinationCards Towns of the player the JFrame responds to
	
	
   /**<b>Constructor</b>
	 * Initializes the variable files and array taken from Gui and Controller, respectively
	 * @param files Paths
	 * @param array Sum of Towns
	 */
	BigCitiesWindow(String[] files,int[] array){
		this.files=files;
		this.sum=array;
			
		this.setResizable(false);
		this.setSize(400,150);
		this.setTitle("My BigCitiesBonus Cards");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
	    this.setLayout(null);
		
		initializeButtons();
		
		this.setVisible(true);
	}
	
   /**Initializes JLabels images and text
	 * (Why initializeButtons though?)
	 * */
	
	private void initializeButtons() {
		for(int i=0;i<6;i++) {
			Image image = null;
				
				try {
					image=ImageIO.read(new File("resources/"+files[i]));
				} catch (IOException e) {
					e.printStackTrace();
				}
				image= image.getScaledInstance( 65, 100,  java.awt.Image.SCALE_SMOOTH ) ;
			
			JLabel p=new JLabel();
			p.setIcon(new ImageIcon(image));
			p.setText(sum[i]+"");
			p.setForeground(Color.WHITE);
		  	p.setFont(new Font("Arial",Font.PLAIN,50));
	        p.setSize(65,100);
	        p.setHorizontalTextPosition(JLabel.CENTER);
	        this.add(p);
	        p.setLocation(15+i*60,15);
	        
	       		
	}
    
}}
