package View;






import java.awt.Color;



import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Image;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import Controller.Controller;
import Model.Cards.BigCitiesBonusCards;
import Model.Cards.ColorTrainCards;
import Model.Cards.DestinationCards;
import Model.Cards.TrainCards;




@SuppressWarnings("serial")
public class Gui extends JFrame {
private int[] k=new int[2];
private int[] ti=new int[2];
private JLabel railyardlabel,scorelabel,onthetracklabel,trainhandlabel,tickethandlabel;	
private JLabel[] scoreshow;
private Controller game;	
private ClassLoader cldr;
private String[] filepaths;
private URL imageURL;
private JLayeredPane general,score[],railyard[],onthetrack[],trainhand[],tickethand[],centerfield;
private JButton[] b,o,t,tick; 
private JButton trainback;
private JButton ticketback;
private Image image;
private Image image_b;
private BackgroundPane backgroundimage;
private List<ColorTrainCards> colorlabel;
private JLabel[] Colors;
private JButton[] bigCitiesCards;
private WindowChoise p1,p2;
private ArrayList<JButton> tickethands;
private ArrayList<JButton>trainhands;
private ArrayList<JButton>trainhands_b;
private ArrayList<JButton> tickethands_b;
private JButton[] faceuptrain;
private TrainCards[] faceuptrains;
private ArrayList<Integer> thesis;
private boolean[] trainsonhand;
private boolean[] ticketsonhand;
private int[] sum;
private ArrayList<JLabel> onthetracklabel1;
private ArrayList<JLabel> onthetracklabel2;
private ArrayList<JLabel> railyardlabel1;
private ArrayList<JLabel> railyardlabel2;

HashMap<String,ArrayList<JButton>> map;
HashMap<Integer,ArrayList<JLabel>> labels;

/**<b>Constructor</b>:
 * Initializes games and buttons
 * The initialization of board takes place in the constructor as well as the dealing of the first cards.
 * */

public Gui()  {
	filepaths=new String[6];
	thesis=new ArrayList<Integer>();
	onthetracklabel1=new ArrayList<JLabel>();
	onthetracklabel2=new ArrayList<JLabel>();
	railyardlabel1=new ArrayList<JLabel>();
    railyardlabel2=new ArrayList<JLabel>();
	sum=new int[2];
	this.setLayout(null);
	map= new HashMap<>();
	labels=new HashMap<>();
	tickethands=new ArrayList<JButton>();
	trainhands=new ArrayList<JButton>();
	tickethands_b=new ArrayList<JButton>();
	trainhands_b=new ArrayList<JButton>();
	map.put("0",trainhands);
	map.put("1",trainhands_b);
	map.put("2",tickethands);
	map.put("3",tickethands_b);
	labels.put(0,onthetracklabel1);
	labels.put(1,onthetracklabel2);
	labels.put(2,railyardlabel1);
	labels.put(3,railyardlabel2);
	score=new JLayeredPane[2];
	railyard=new JLayeredPane[2];
	onthetrack=new JLayeredPane[2];
	trainhand=new JLayeredPane[2];
	tickethand=new JLayeredPane[2];
	scoreshow=new JLabel[2];
	faceuptrains=new TrainCards[5];
	new ArrayList<TrainCards>();
	new ArrayList<DestinationCards>();
	game=new Controller();
	backgroundimage= new BackgroundPane();
	backgroundimage.setSize(new Dimension(1000,800));
	cldr = this.getClass().getClassLoader();
	imageURL= cldr.getResource("images/train.png");
    image=new ImageIcon(imageURL).getImage();
    this.add(this.backgroundimage);
    k[0]=0;
    k[1]=0;
    ti[0]=0;
    ti[1]=0;
    trainsonhand=new boolean[2];
    ticketsonhand=new boolean[2];
	this.setPreferredSize(new Dimension(1000,800));
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setTitle("Ticket To Ride Card Game");
	this.setResizable(false);
	
	
	faceuptrain=new JButton[5];
	
	
    colorlabel=new ArrayList<ColorTrainCards>(8);
    
    b=new JButton[2];
    o=new JButton[2];
	t=new JButton[2];
	trainback=new JButton();
	ticketback=new JButton();
	
	tick=new JButton[2];
	Colors=new JLabel[8];//for 8 colors
	
	Player(0,0);
	Player(442,1);
	
	this.pack();
    this.setVisible(true);
	
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
   
	 
    centerfield=new JLayeredPane();
    centerfield.setBounds(0,310,1000,467);
    centerfield.setLayout(null);
    
    this.add(centerfield);
    
    this.initializeBigCitiesCards();
    ticketback=new JButton();
    Image buttonIcon ;
	try {
		
	
    imageURL= cldr.getResource("images/destination_Tickets/desBackCard.jpg");
    buttonIcon= new ImageIcon(imageURL).getImage().getScaledInstance(65,100, Image.SCALE_SMOOTH);
	ticketback= new JButton(new ImageIcon(buttonIcon));
    ticketback.addActionListener(new DrawDestinationCards());
    ticketback.setBounds(150,25,60,100);
    
   
    
    buttonIcon = ImageIO.read(new File("resources/images/trainCards/trainBackCard.jpg"));
	buttonIcon= buttonIcon.getScaledInstance( 60, 100,  java.awt.Image.SCALE_SMOOTH ) ;
	trainback= new JButton(new ImageIcon(buttonIcon));
	trainback.addActionListener(new DrawTrainCards());
    trainback.setBounds(50,25,60,100);
    
    
    centerfield.add(ticketback);
    centerfield.add(trainback);
   
   
	} catch (IOException e) {
		e.printStackTrace();
	}

	game.startGame();
    
    this.updateCardstoTrainHand(0);
    this.updateCardstoTrainHand(1);
    this.updateFaceUp();
    
    
    if(game.returnGameStarted()) {
    	
    
    ArrayList<DestinationCards> temp=game.returnDestinationDeck(6);
    p1=new WindowChoise(temp,0);
    game.removetillDestideck(6);
    while(!p1.returnChoiseMade()) {
	
    	System.out.print("");
    }
    
    temp=game.returnDestinationDeck(6);
    game.removetillDestideck(6);
    p2=new WindowChoise(temp,1);
    
    while(!p2.returnChoiseMade()) {
    	System.out.print("");
    }
  
   
   game.returnPlayer(0).AddTicketCardsOnHand(p1.returnSelected());
   game.returnPlayer(1).AddTicketCardsOnHand(p2.returnSelected());
   this.updateTicketHand(1);
   this.updateTicketHand(0);
   
   
   this.updateScore();   
   game.returnDestinationDeck(46).addAll(p1.returnNotSelected());
   game.returnDestinationDeck(46).addAll(p2.returnNotSelected());
   Collections.shuffle(game.returnDestinationDeck(46));

   game.determineFirstToPlay(); 
   this.fixAblePlayer();
   checkTrainCard(0);
   checkTrainCard(1);
    }
   }


    
public void checkTrainCard(int ID) {
	ArrayList<DestinationCards> ririr=game.returnPlayer(ID).returnTicketHand().returnCards();
	for(int i=0;i<ririr.size();i++) {
		System.out.println("ID:"+ID +ririr.get(i).returnID());
	}
}	

/**<b>Transformer:</b> Adds Buttons that have not been added yet.
 *<b>Postcondition:</b> It should respond to Controller's player trainhand 
 * @param ID -Player ID
 * */

public void AddButtonTrain(int ID) {
	ArrayList<TrainCards> pain=game.returnPlayer(ID).returnTrainHand().returnCards();
	int buttonsize=map.get(Integer.toString(ID)).size();
	if(buttonsize<pain.size()) {
		
	 for(int i=buttonsize;i<pain.size();i++) {
		imageURL= cldr.getResource("images/trainCards/"+pain.get(i).returnColor()+".jpg");
		image = new ImageIcon(imageURL).getImage().getScaledInstance(55,90, Image.SCALE_SMOOTH);
		JButton p=new JButton();
		p.setIcon(new ImageIcon(image));
        p.setSize(55,90);
        p.setActionCommand(i+"");
        p.addActionListener(new TrainCard());
     
        map.get(Integer.toString(ID)).add(p);
	    this.trainhand[ID].add(map.get(Integer.toString(ID)).get(i));
	    
	 }
		
	}
}
/**<b>Transformer:</b> Adds Buttons that have not been added yet.
 *<b>Postcondition:</b> It should respond to Controller's player tickethand 
 * @param ID -Player ID
 * */

public void AddButtonTicket(int ID) {
	ArrayList<DestinationCards> pain=game.returnPlayer(ID).returnTicketHand().returnCards();
	int buttonsize=map.get(Integer.toString(ID+2)).size();
	if(buttonsize<pain.size()) {
		
	 for(int i=buttonsize;i<pain.size();i++) {
		imageURL= cldr.getResource("images/destination_Tickets/desCard"+(pain.get(i).returnID())+".jpg");
		image = new ImageIcon(imageURL).getImage().getScaledInstance(65,100, Image.SCALE_SMOOTH);
		JButton p=new JButton();
		p.setIcon(new ImageIcon(image));
        p.setSize(65,100);
        p.addActionListener(new TicketHandCard());
        p.setActionCommand(i+"");
   
        map.get(Integer.toString(ID+2)).add(p);
	    this.tickethand[ID].add(map.get(Integer.toString(ID+2)).get(i));
	    
	 }
		
	}
	
}
/**<b>Transformer:</b> Updates and realocates Buttons.
 *<b>Postcondition:</b> It should respond to Controller's player tickethand.
 * @param ID -Player ID
 * */

public  void updateTicketHand(int ID) {
	ArrayList<DestinationCards> pain=game.returnPlayer(ID).returnTicketHand().returnCards();

if(ticketsonhand[ID]==false) {
	for(int i=0;i<pain.size();i++) {
		imageURL= cldr.getResource("images/destination_Tickets/desCard"+(pain.get(i).returnID())+".jpg");
		image = new ImageIcon(imageURL).getImage().getScaledInstance(65,100, Image.SCALE_SMOOTH);
		JButton p=new JButton();
		p.setIcon(new ImageIcon(image));
        p.setSize(65,100);
        p.setLocation(5+i*p.getWidth(),3);
        p.addActionListener(new TicketHandCard()); 
        p.setActionCommand(i+"");
        System.out.println(map.get(Integer.toString(ID+2)));
        map.get(Integer.toString(ID+2)).add(p);
	    this.tickethand[ID].add(map.get(Integer.toString(ID+2)).get(i));
	  
	   
		
	}
   ticketsonhand[ID]=true;
   
}else{
	 if(65*pain.size()>200) {
			ti[ID]=(int) Math.ceil((65*pain.size()-200)/(pain.size()-1 ));
			for(int i=0;i<pain.size();i++) {
				map.get(Integer.toString(ID+2)).get(i).setLocation(5+i*(65-ti[ID]),3);
				map.get(Integer.toString(ID+2)).get(i).setActionCommand(i+"");
			}	
		   }
		
		  if(65*pain.size()<200) {
		 	 
			 for(int i=0;i<pain.size();i++) { 
				 map.get(Integer.toString(ID+2)).get(i).setLocation(5+i*65,3);
				 map.get(Integer.toString(ID+2)).get(i).setActionCommand(i+"");
			 }
	       }
		
		
	}
	
	
	
	
}
/**<b>Transformer:</b> Updates and realocates Buttons.
 *<b>Postcondition:</b> It should respond to Controller's player trainhand.
 * @param ID -Player ID
 * */

public void updateCardstoTrainHand(int ID) {
	ArrayList<TrainCards> pain=game.returnPlayer(ID).returnTrainHand().returnCards();
	
 if(trainsonhand[ID]==false) {
	for(int i=0;i<pain.size();i++) {
		imageURL= cldr.getResource("images/trainCards/"+pain.get(i).returnColor()+".jpg");
		image = new ImageIcon(imageURL).getImage().getScaledInstance(55,90, Image.SCALE_SMOOTH);
		JButton p=new JButton();
		p.setIcon(new ImageIcon(image));
        p.setSize(55,90);
        p.setLocation(5+i*p.getWidth(),3);
        p.addActionListener(new TrainCard());
        p.setActionCommand(i+"");
        map.get(Integer.toString(ID)).add(p);
	    this.trainhand[ID].add(map.get(Integer.toString(ID)).get(i));
	    
	  		
	}
	trainsonhand[ID]=true;
	
 }else {
	  if(55*pain.size()>450) {
		k[ID]=(int) Math.ceil((55*pain.size()-450)/(pain.size()-1 ));
		for(int i=0;i<pain.size();i++) { 
			map.get(Integer.toString(ID)).get(i).setActionCommand(i+"");
			map.get(Integer.toString(ID)).get(i).setLocation(5+i*(55-k[ID]),3);}
				
	   }
	
	  if(55*pain.size()<450) {
	 	 
		 for(int i=0;i<pain.size();i++) {
			 map.get(Integer.toString(ID)).get(i).setActionCommand(i+"");
			 map.get(Integer.toString(ID)).get(i).setLocation(5+i*55,3);
		 }
       }
	
	
}
 	
}

/**<b>Transformer:</b> Updates scoreboard of both Players.
 *<b>Postcondition:</b> It should respond to Controller's players scoreboard.
 * */


private void updateScore() {
	
	game.returnPlayer(0).updateScore();
	game.returnPlayer(1).updateScore();
	
	
	scoreshow[1].setText("Score:  "+String.valueOf(game.returnPlayer(1).GetScore()));
    scoreshow[0].setText("Score:  "+String.valueOf(game.returnPlayer(0).GetScore()));
   
    if((game.returnPlayer(0).GetScore()>=100||game.returnPlayer(1).GetScore()>=100)||game.returnTrainDeck(96).isEmpty()) {
        game.SetGameFinished();
    	if(game.GetWinner()==2) {
        JOptionPane.showMessageDialog(null, "It's a tie!","Game Finished",EXIT_ON_CLOSE);
    	this.setVisible(false);}
        else {
        JOptionPane.showMessageDialog(null, "The winner is"+(game.GetWinner()+1),"Game Finished",EXIT_ON_CLOSE);
    	this.setVisible(false);}
}}




    
		
/**<b>Transformer:</b> Updates and Face up Train Cards Button at index.
 *<b>Postcondition:</b> It should respond to Controller's player RevealedTrainCards.
 * @param index -index of JButton array
 * */


public void updateFaceUpSpecIn(int index) {
	 
	 faceuptrain[index].setEnabled(false);
	 centerfield.remove(faceuptrain[index]);
	 faceuptrains=this.game.returnFaceup().returnRt();
	 
	 imageURL= cldr.getResource("images/trainCards/"+faceuptrains[index].returnColor()+".jpg");
	 
	 image = new ImageIcon(imageURL).getImage().getScaledInstance(55,90, Image.SCALE_SMOOTH);
	
	 
     this.centerfield.add(faceuptrain[index]);
   
}

/**<b>Transformer:</b> Updates and Face up Train Cards Buttons
*<b>Postcondition:</b> It should respond to Controller's player RevealedTrainCards.
* 
* */

public void updateFaceUp() {
	faceuptrains=this.game.returnFaceup().returnRt();
	
	for(int i=0;i<5;i++) {
		
		imageURL= cldr.getResource("images/trainCards/"+faceuptrains[i].returnColor()+".jpg");
		image = new ImageIcon(imageURL).getImage().getScaledInstance(55,90, Image.SCALE_SMOOTH);
		JButton p=new JButton();
		faceuptrain[i]=p;
		faceuptrain[i].setIcon(new ImageIcon(image));
		faceuptrain[i].setSize(55,90);
		faceuptrain[i].setLocation(250+i*(p.getWidth()),30);
		faceuptrain[i].addActionListener(new FaceUpDrawListener());
		
		faceuptrain[i].setActionCommand(Integer.toString(i));
       
		this.centerfield.remove(faceuptrain[i]);
        this.centerfield.add(faceuptrain[i]);
        
        }
	    
}


/**<b>Transformer:</b> Updates Railyard texts.
 *<b>Postcondition:</b> It should respond to Controller's player Railyard.
 *@param ID -ID of Player
 * */


public void updateRailyard(int ID) {
	for(int i=0;i<8;i++) {
		labels.get(ID+2).get(i).setText(game.returnPlayer(ID).returnRailyard().returnsum(i)+"");
		
	}
};

/**<b>Transformer:</b> Updates OnTheTrack texts.
 *<b>Postcondition:</b> It should respond to Controller's player OnTheTrack.
 *@param ID -ID of Player
 * */
public void updateOnTheTrack(int ID) {
	for(int i=0;i<9;i++) {
		labels.get(ID).get(i).setText(game.returnPlayer(ID).returnOnTheTrack().getSumColorIndex(i)+"");
		
	}
	
}

/**<b>Transformer:</b> Updates setEnabled Buttons . Depends on the Player's turn.
 *
 * */
public void fixAblePlayer() {
	int v=game.returnRound().returnPlayerPlaying().GetID();
	int k=game.returnRound().returnPlayernotPlaying().GetID();
	t[v].setEnabled(true);
	o[v].setEnabled(true);
	tick[v].setEnabled(true);
	b[v].setEnabled(true);
	
	
	t[k].setEnabled(false);
	o[k].setEnabled(false);
	tick[k].setEnabled(false);
	b[k].setEnabled(false);
	
	for(int i=0;i<map.get(Integer.toString(v)).size();i++) {
		map.get(Integer.toString(v)).get(i).setEnabled(true);
	}
	for(int i=0;i<map.get(Integer.toString(k)).size();i++) {
		map.get(Integer.toString(k)).get(i).setEnabled(false);
	}
	
	for(int i=0;i<map.get(Integer.toString(v+2)).size();i++) {
		map.get(Integer.toString(v+2)).get(i).setEnabled(true);
	}
	for(int i=0;i<map.get(Integer.toString(k+2)).size();i++) {
		map.get(Integer.toString(k+2)).get(i).setEnabled(false);
	}
	
}
       
public void initializeBigCitiesCards(){
	this.bigCitiesCards=new JButton[6];
	int f=60;
	for(int i=0;i<6;i++) {
	
	URL imageURL= cldr.getResource("images/bigCitiesCards/"+game.returnAvailableBigBonus().takeFromIndex(i).getNameTown()+".jpg");
	Image image = new ImageIcon(imageURL).getImage().getScaledInstance(60,95, Image.SCALE_SMOOTH);

    this.bigCitiesCards[i]=new JButton();
    this.bigCitiesCards[i].setIcon(new ImageIcon(image));
    this.bigCitiesCards[i].setBounds(620+f*i,30,60,95);
    this.filepaths[i]="images/bigCitiesCards/"+game.returnAvailableBigBonus().takeFromIndex(i).getNameTown()+".jpg";
    centerfield.remove(bigCitiesCards[i]);
    centerfield.add(bigCitiesCards[i]);
    

    	}
	
}
/**<b>Transformer:</b> After chosing cards it refreshes.
 *@param x -Window to get values
 * */

public void waitToRefresh(WindowChoise x) {
    game.returnRound().returnPlayerPlaying().AddTicketCardsOnHand(x.returnSelected());
    AddButtonTicket(game.returnRound().returnPlayerPlaying().GetID());
    updateTicketHand(game.returnRound().returnPlayerPlaying().GetID());
    
    this.updateScore();
    game.returnRound().TurnSetTrue(game.returnRound().returnPlayerPlaying().GetID());
	fixAblePlayer();
	game.returnRound().returnPlayerPlaying().returnRailyard().sortList();
	game.returnRound().returnPlayerPlaying().returnRailyard().MoveTopCardsonRailyard();
	game.returnRound().returnPlayerPlaying().returnOnTheTrack().ArrangeFromRailyard(game.returnRound().returnPlayerPlaying().returnRailyard().getcardsremoved());
	game.returnRound().returnPlayerPlaying().returnRailyard().clearcardsremoved();
	updateRailyard(game.returnRound().returnPlayerPlaying().GetID());
	updateOnTheTrack(game.returnRound().returnPlayerPlaying().GetID());
}



/**<b>Transformer:</b> Creates Players ID area.
 *@param id -ID of Player
 *@param y (height)
 * */


public  void Player(int y,int id) {
	LineBorder border = new LineBorder(Color.decode("#412913"), 5);
	
	
	
    general=new JLayeredPane();   
    general.setBounds(0,y,995,330);
    general.setBorder(border);
    general.setOpaque(true);
    backgroundimage.add(general);
   
    
    railyard[id]=new JLayeredPane();
    railyardlabel=new JLabel();
    this.railyardlabel.setText("Railyard");
    this.railyardlabel.setForeground(Color.decode("#412913"));
    this.railyardlabel.setBounds(15,160,50,20);
    railyard[id].add(this.railyardlabel);
    railyard[id].setBounds(15,140,547,180);
    railyard[id].setBorder(border);
    railyard[id].setOpaque(true);
    railyard[id].setBackground(Color.decode("#bcb9a7"));
    general.add(railyard[id]);
	
    
    trainhand[id]=new JLayeredPane();
    trainhandlabel=new JLabel();
    this.trainhandlabel.setText("Train Cards on Hand");
    this.trainhandlabel.setForeground(Color.decode("#412913"));
    this.trainhandlabel.setBounds(15,100,180,20);
    trainhand[id].add(this.trainhandlabel);
    trainhand[id].setBounds(15,10,547,120);
    trainhand[id].setBorder(border);
    trainhand[id].setOpaque(true);
    trainhand[id].setBackground(Color.decode("#bcb9a7"));//ccb99b bcb9a7
    general.add(trainhand[id]);
      
    tickethand[id]=new JLayeredPane();
    tickethandlabel=new JLabel();
    this.tickethandlabel.setText("Ticket Cards on Hand");
    this.tickethandlabel.setForeground(Color.decode("#412913"));
    this.tickethandlabel.setBounds(15,100,180,20);
    tickethand[id].add(this.tickethandlabel);
    tickethand[id].setBounds(580,10,230,120);
    tickethand[id].setBorder(border);
    tickethand[id].setOpaque(true);
    tickethand[id].setBackground(Color.decode("#bcb9a7"));
    general.add(tickethand[id]);
    
    
    onthetrack[id]=new JLayeredPane();
    onthetracklabel=new JLabel();
    this.onthetracklabel.setText("On the Track");
    this.onthetracklabel.setForeground(Color.decode("#412913"));
    this.onthetracklabel.setBounds(15,160,100,20);
    onthetrack[id].add(this.onthetracklabel);
  
	
    onthetrack[id].setBounds(580,140,230,180);
    onthetrack[id].setBorder(border);
    onthetrack[id].setOpaque(true);
    onthetrack[id].setBackground(Color.decode("#bcb9a7"));//d9b38c  fff0b3 e0ad6c
    general.add(onthetrack[id]);//bcb9a7  e2e0c8
   
    score[id]=new JLayeredPane();
    score[id].setBounds(825,10,155,310);
    score[id].setBorder(border);
    score[id].setOpaque(true);
    score[id].setBackground(Color.decode("#bcb9a7"));
    general.add(score[id]);
    
     
    t[id]=new JButton();   
    t[id].setBackground(Color.decode("#FFFFFFF"));
	t[id].setBounds(440,90,95,20);
	t[id].setForeground(Color.decode("#412913"));
	t[id].setText("Play Cards");
	t[id].setVisible(true);
	t[id].addActionListener(new PlayCards());
	trainhand[id].add(t[id]);
	

    o[id]=new JButton();
    o[id].setBackground(Color.decode("#FFFFFFF"));
    o[id].setBounds(355,145,180,20);
    o[id].setForeground(Color.decode("#412913"));
    o[id].setText("Move Cards on The Track");
    o[id].setVisible(true);    
	//railyard[id].add(o[id]);    
	
	
	 
	tick[id]=new JButton();
	tick[id].addActionListener(null);
	tick[id].setBackground(Color.decode("#FFFFFFF"));
	tick[id].setBounds(20,135,120,50);
	tick[id].setForeground(Color.decode("#412913"));
	tick[id].setText("<html>My Destination<br/> Tickets</html>");
	tick[id].addActionListener(new TicketListener());
	tick[id].setVisible(true);
	
	
	
	score[id].add(tick[id]);
	
	scorelabel=new JLabel();
	scoreshow[id]=new JLabel();
	this.scoreshow[id].setText("Score: "+game.returnPlayer(id).GetScore());
	this.scoreshow[id].setForeground(Color.decode("#412913"));
	this.scoreshow[id].setBounds(15,50,140,20);
    this.scorelabel.setText("Player "+(id+1)+" Scoreboard");
    this.scorelabel.setBounds(15,15,140,20);
    this.scorelabel.setForeground(Color.decode("#412913"));
    score[id].add(this.scoreshow[id]);
    score[id].add(this.scorelabel);
	b[id]=new JButton();
	b[id].setBackground(Color.decode("#FFFFFFF"));
	b[id].setBounds(20,195,120,50);
	b[id].setForeground(Color.decode("#412913"));
	b[id].setText("<html>My Big Cities<br/>Bonus Cards</html>");
	b[id].addActionListener(new MyBigCitiesBcListener());
	b[id].setVisible(true);
	score[id].add(b[id]);

	colorlabel= Arrays.asList(ColorTrainCards.values());
	int x=0;
	int l=0;
	int heighth = 0;
	for(int i=0;i<8;i++) {
	  
	  JLabel col=new JLabel();
	  JLabel fol=new JLabel();
	  Image shit;
	  
	  try {
		

		  
	  shit = ImageIO.read(new File("resources/images/trainCards/"+colorlabel.get(i)+".jpg"));
	  shit= shit.getScaledInstance( 55, 90,java.awt.Image.SCALE_SMOOTH ) ;
	  col.setIcon(new ImageIcon(shit));
  	  col.setBounds(20+i*(55+10),40,55,90);
  	  
  	  col.setText(""+game.returnPlayer(id).returnRailyard().returnsum(i));
  	  col.setForeground(Color.LIGHT_GRAY);
  	  col.setFont(new Font("Arial",Font.PLAIN,40));
  	  col.setHorizontalTextPosition(JLabel.CENTER);
  	  labels.get(id+2).add(col);
	  railyard[id].add(labels.get(id+2).get(i));
	  
	   
	  shit = ImageIO.read(new File("resources/images/trainCardsRotated/"+colorlabel.get(i)+".jpg"));
	  shit= shit.getScaledInstance(70,35,java.awt.Image.SCALE_SMOOTH ) ;
	  fol.setIcon(new ImageIcon(shit));
	  fol.setText(""+game.returnPlayer(id).returnOnTheTrack().getSumColorIndex(i));
  	  fol.setForeground(Color.LIGHT_GRAY);
  	  fol.setFont(new Font("Arial",Font.PLAIN,30));
  	  fol.setHorizontalTextPosition(JLabel.CENTER);
	  if(l%3==0)  {heighth+=40; l=0; }
	  fol.setBounds(5+l*(70+5),heighth,70,35);
	  labels.get(id).add(fol);
	  onthetrack[id].add(labels.get(id).get(i));
	  
	  } catch (IOException e) {
			
		  	e.printStackTrace();
		   }
	  JLabel temp=new JLabel();	
	  temp.setText(colorlabel.get(i).toString());	
	  temp.setForeground(Color.decode("#412913"));	
	  temp.setBounds(25+x,10,50,20);
	  Colors[i]=temp;
	  x+=65;
	  railyard[id].add(temp);
	  l++;}
	
	 try {
	image_b = ImageIO.read(new File("resources/images/trainCardsRotated/"+colorlabel.get(8)+".jpg"));
	image_b= image_b.getScaledInstance(70,35,java.awt.Image.SCALE_SMOOTH ) ;
	JLabel loco=new JLabel();
	loco.setIcon(new ImageIcon(image_b));
	loco.setBounds(5+2*(70+5),120,70,35);
	 loco.setText(""+game.returnPlayer(id).returnOnTheTrack().getSumColorIndex(8));
 	 loco.setForeground(Color.LIGHT_GRAY);
 	 loco.setFont(new Font("Arial",Font.PLAIN,30));
 	 loco.setHorizontalTextPosition(JLabel.CENTER);
	labels.get(id).add(loco);
	
	onthetrack[id].add(labels.get(id).get(8));} catch (IOException e) {
			
			e.printStackTrace();}
}
	  
	  
	
public Gui returnGui() {
	return this;
}




private class BackgroundPane extends JDesktopPane
{
   public BackgroundPane()
   {      
      
   }
 
        @Override
   public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
 } 

/////////////////////////////////////////////////////
/*
This ActionListener is responsible for drawing Cards from DestinationDeck.
Creates a WindowChoise to chose from.
*/
/////////////////////////////////////////////////////   


private class DrawDestinationCards implements ActionListener 	{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(sum[game.returnRound().returnPlayerPlaying().GetID()]==0) {
	    ArrayList<DestinationCards> temporal;
		sum[game.returnRound().returnPlayernotPlaying().GetID()]=0;
		if(game.returnDestinationDeck(46).size()<4) {
        temporal=game.returnDestinationDeck(game.returnDestinationDeck(46).size());
		game.removetillDestideck(game.returnDestinationDeck(46).size());}
        else {		
		temporal=game.returnDestinationDeck(4);
		
		game.removetillDestideck(4);}
		
		WindowChoise err=new WindowChoise(temporal,game.returnRound().returnPlayerPlaying().GetID(),returnGui());
		game.returnDestinationDeck(46).addAll(err.returnNotSelected());
		Collections.shuffle(game.returnDestinationDeck(46));
        
	}
	}
 }
/////////////////////////////////////////////////////
/*
This ActionListener is responsible for chosing the TrainCards from your Hand that you want to play.
*/
/////////////////////////////////////////////////////   

private class TrainCard implements ActionListener 	{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(sum[game.returnRound().returnPlayerPlaying().GetID()]==0) {
		int ID=game.returnRound().returnPlayerPlaying().GetID();
		ArrayList<JButton>pain=map.get(Integer.toString(ID));
		
		        thesis.add(Integer.parseInt(e.getActionCommand()));
                pain.get(Integer.parseInt(e.getActionCommand())).setLocation(pain.get(Integer.parseInt(e.getActionCommand())).getX(),pain.get(Integer.parseInt(e.getActionCommand())).getY()-4);  	
                pain.get(Integer.parseInt(e.getActionCommand())).setEnabled(false);
				game.returnPlayer(ID).AddtoPlay(game.returnPlayer(ID).returnTrainHand().returnCards().get(Integer.parseInt(e.getActionCommand())));
				
			   
		
		
	}}

	

 }
/////////////////////////////////////////////////////
/*
This ActionListener is responsible for buying Cards by getting ActionCommand from specific DestinationCards JButton.
*/
/////////////////////////////////////////////////////   

private class TicketHandCard implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(sum[game.returnRound().returnPlayerPlaying().GetID()]==0) {
		 int response = JOptionPane.showConfirmDialog(null, "Do you want to use Locomotives for your purchase?", "Confirm",
 		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
 	            DestinationCards h=game.returnRound().returnPlayerPlaying().returnTicketHand().returnCards().get(Integer.parseInt(e.getActionCommand()));
 		    if (response == JOptionPane.NO_OPTION) {
 		    	game.returnRound().returnPlayerPlaying().BuyCards(Integer.parseInt(e.getActionCommand()));
 		    	
 		    } else if (response == JOptionPane.YES_OPTION) {
 		    	game.returnRound().returnPlayerPlaying().BuyCardsWithLocomotives(Integer.parseInt(e.getActionCommand()));
 		    	
 		    }
	        if(game.returnRound().returnPlayerPlaying().returnCanBuy()) {
	        	sum[game.returnRound().returnPlayernotPlaying().GetID()]=0;
	        	
	           	map.get(((game.returnRound().returnPlayerPlaying().GetID())+2)+"").get(Integer.parseInt(e.getActionCommand())).setVisible(false);
	        	map.get(((game.returnRound().returnPlayerPlaying().GetID())+2)+"").remove(Integer.parseInt(e.getActionCommand()));
	        	
	        	updateTicketHand(game.returnRound().returnPlayerPlaying().GetID());
	        	updateOnTheTrack(game.returnRound().returnPlayerPlaying().GetID());
	        	
	        	game.returnRound().returnPlayerPlaying().returnScoreboard().calculateTowns();
	            ArrayList<Integer> sa=game.returnRound().returnPlayerPlaying().returnScoreboard().CheckForBigCities();
	            if(sa.size()!=0) {
	            	for(int i=0;i<sa.size();i++) {
	            	if(game.returnAvailableBigBonus().takeFromIndex(sa.get(i))!=null) {
	            	if(((bigCitiesCards[sa.get(i)]).getText())!=null) {
	            		bigCitiesCards[sa.get(i)].setText((game.returnRound().returnPlayerPlaying().GetID()+1)+"");
	            		bigCitiesCards[sa.get(i)].setForeground(Color.WHITE);
	            		bigCitiesCards[sa.get(i)].setFont(new Font("Arial",Font.PLAIN,30));
	            		bigCitiesCards[sa.get(i)].setHorizontalTextPosition(JLabel.CENTER);
	            	}
	            	
	            	BigCitiesBonusCards l=game.returnAvailableBigBonus().takeFromIndex(sa.get(i));
	            	game.returnAvailableBigBonus().removefromIndex(sa.get(i));
	            	game.returnRound().returnPlayerPlaying().returnScoreboard().AddBigCitiesBonusCards(l);
	            	
	            	}
	            }}
	            game.returnRound().returnPlayerPlaying().setCanBuy(false);
	        	
	        	//if(game.returnRound().returnPlayerPlaying().GetScore())
	        	game.returnRound().TurnSetTrue(game.returnRound().returnPlayerPlaying().GetID());
	        	
		        fixAblePlayer();
		        game.returnRound().returnPlayerPlaying().returnRailyard().sortList();
		        game.returnRound().returnPlayerPlaying().returnRailyard().MoveTopCardsonRailyard();
		        game.returnRound().returnPlayerPlaying().returnOnTheTrack().ArrangeFromRailyard(game.returnRound().returnPlayerPlaying().returnRailyard().getcardsremoved());
		        game.returnRound().returnPlayerPlaying().returnRailyard().clearcardsremoved();
		        updateRailyard(game.returnRound().returnPlayerPlaying().GetID());
		        updateOnTheTrack(game.returnRound().returnPlayerPlaying().GetID());
		        updateScore();
		        
		        
	        }
	        else {
	        	
	        	JOptionPane.showMessageDialog(new JFrame(),"Not enough cards","You can't buy that",JOptionPane.ERROR_MESSAGE);
	        }

        	
}
		
}}

/////////////////////////////////////////////////////
/*
This ActionListener is responsible for drawing Cards from Traindeck.
*/
/////////////////////////////////////////////////////   

private class DrawTrainCards implements ActionListener 	{

	@Override
	public void actionPerformed(ActionEvent e) {

		 if((sum[game.returnRound().returnPlayerPlaying().GetID()]<2)&&(!game.returnTrainDeck(96).isEmpty())) {   
	     game.returnRound().returnPlayerPlaying().AddTrainCardsOnHand(game.returnTrainDeck(1));
	     System.out.println("add to train hand: "+game.returnTrainDeck(1).get(0).returnID());
	     System.out.println("add to train hand color"+game.returnTrainDeck(1).get(0).returnColor());
	     game.removeTraindeck(0);
	     sum[game.returnRound().returnPlayerPlaying().GetID()]++;
	     AddButtonTrain(game.returnRound().returnPlayerPlaying().GetID());
		 updateCardstoTrainHand(game.returnRound().returnPlayerPlaying().GetID());
	     if(sum[game.returnRound().returnPlayerPlaying().GetID()]==2) {
	    	
	    	 sum[game.returnRound().returnPlayernotPlaying().GetID()]=0;
		     game.returnRound().TurnSetTrue(game.returnRound().returnPlayerPlaying().GetID());
	         fixAblePlayer();
	         game.returnRound().returnPlayerPlaying().returnRailyard().sortList();
	         game.returnRound().returnPlayerPlaying().returnRailyard().MoveTopCardsonRailyard();
	         game.returnRound().returnPlayerPlaying().returnOnTheTrack().ArrangeFromRailyard(game.returnRound().returnPlayerPlaying().returnRailyard().getcardsremoved());
	         game.returnRound().returnPlayerPlaying().returnRailyard().clearcardsremoved();
	         updateRailyard(game.returnRound().returnPlayerPlaying().GetID());
	         updateOnTheTrack(game.returnRound().returnPlayerPlaying().GetID());
	     }
	    

 }}}


/////////////////////////////////////////////////////
/*
This ActionListener is responsible for Playing the Cards that have been selected.
If it is viable.
*/
/////////////////////////////////////////////////////   
private class PlayCards implements ActionListener{ 	
   @Override
	public void actionPerformed(ActionEvent e) 	{
if((sum[game.returnRound().returnPlayerPlaying().GetID()]==0 )&& (!game.returnRound().returnPlayerPlaying().returnTrainHand().returnEmpty() )) {
	   int ID=game.returnRound().returnPlayerPlaying().GetID();
	   ArrayList<JButton>pain=map.get(Integer.toString(ID));           
	   game.returnRound().checkIfValidChoise(game.returnPlayer(ID));
	       if(game.returnRound().returnPlayerPlaying().getValidCardPlacement()==true) {
	    	  
	    	   thesis.sort(null);
	    	   Collections.reverse(thesis);
	           for(int i=0;i<thesis.size();i++) {
	           game.returnPlayer(ID).returnTrainHand().returnCards().remove(thesis.get(i).intValue());
	           pain.get(thesis.get(i).intValue()).setVisible(false);
       		   
	           }
	           
	          for(int i=0;i<thesis.size();i++)pain.remove(thesis.get(i).intValue());
	          thesis.clear();
	          
           }else {
        	   JOptionPane.showMessageDialog(new JFrame(),"You can't do that","Better read the rules again...",JOptionPane.ERROR_MESSAGE);
        	   game.returnRound().returnPlayerPlaying().CleartoPlay();
        	   for(int i=0;i<thesis.size();i++) {
        		   pain.get(thesis.get(i).intValue()).setLocation(pain.get(thesis.get(i).intValue()).getX(),pain.get(thesis.get(i).intValue()).getY()+4);  
        		   pain.get(thesis.get(i).intValue()).setEnabled(true);
        	  
           }
        	   thesis.clear();
 }
	    
updateCardstoTrainHand(game.returnPlayer(ID).GetID());
updateRailyard(ID);
if(game.returnPlayer(ID).getValidCardPlacement()) {
sum[game.returnRound().returnPlayernotPlaying().GetID()]=0;
game.returnRound().TurnSetTrue(ID);
fixAblePlayer();
game.returnPlayer(ID).CleartoPlay();
game.returnPlayer(ID).setValidCardPlacement(false);
game.returnPlayer(ID).setValidChoise(false);
game.returnRound().returnPlayerPlaying().returnRailyard().sortList();
game.returnRound().returnPlayerPlaying().returnRailyard().MoveTopCardsonRailyard();
game.returnRound().returnPlayerPlaying().returnOnTheTrack().ArrangeFromRailyard(game.returnRound().returnPlayerPlaying().returnRailyard().getcardsremoved());
game.returnRound().returnPlayerPlaying().returnRailyard().clearcardsremoved();
updateRailyard(game.returnRound().returnPlayerPlaying().GetID());
updateOnTheTrack(game.returnRound().returnPlayerPlaying().GetID());

}
}
   }
   }
/////////////////////////////////////////////////////
/*
This ActionListener is responsible for poping up Window with BigCities sums inside.
*/
/////////////////////////////////////////////////////   
   
private class MyBigCitiesBcListener implements ActionListener 	{
          

   @Override
	public void actionPerformed(ActionEvent e) 	{
              BigCitiesWindow bc=new BigCitiesWindow(filepaths,game.returnRound().returnPlayerPlaying().returnScoreboard().returnSumBigCities());
              
           }
}

/////////////////////////////////////////////////////
/*
This ActionListener is responsible for poping up Window with DestinationCards owned.
*/
/////////////////////////////////////////////////////   

private class TicketListener implements ActionListener 	{

@Override
public void actionPerformed(ActionEvent e) 	{
   MyTicketsWindow win=new MyTicketsWindow(game.returnRound().returnPlayerPlaying().returnScoreboard().returnDestinationCards(),game.returnRound().returnPlayerPlaying().GetID());
}
}

/////////////////////////////////////////////////////
/*
This ActionListener is responsible for drawing Cards from RevealedTrainCards.
*/
/////////////////////////////////////////////////////   

private class FaceUpDrawListener implements ActionListener 	{

@Override
public void actionPerformed(ActionEvent e) 	{
 if((sum[game.returnRound().returnPlayerPlaying().GetID()]<2)&&(!game.returnTrainDeck(96).isEmpty())) {
	 
   
   int index=Integer.parseInt(e.getActionCommand());
   game.returnRound().returnPlayerPlaying().returnTrainHand().AddOneCard(game.returnFaceup().TakefromIndex(index));
   AddButtonTrain(game.returnRound().returnPlayerPlaying().GetID());
   updateCardstoTrainHand(game.returnRound().returnPlayerPlaying().GetID());
   
   game.returnFaceup().removeFromIndex(index);
  
   System.out.println("THE CARD ADDED TO REPLACE :"+game.returnTrainDeck(96).get(0));  
   game.returnFaceup().InserttoList(game.returnTrainDeck(96).get(0),index);
   game.removeTraindeck(0);

   imageURL= cldr.getResource("images/trainCards/"+faceuptrains[index].returnColor()+".jpg");
   System.out.println("IT SHOULD BE  "+faceuptrains[index].returnColor());
   image = new ImageIcon(imageURL).getImage().getScaledInstance(55,90, Image.SCALE_SMOOTH);
   faceuptrain[index].setIcon(new ImageIcon(image));
   
	 
   
   
   ++sum[game.returnRound().returnPlayerPlaying().GetID()];
  
   
   if(sum[game.returnRound().returnPlayerPlaying().GetID()]==2) {
		 sum[game.returnRound().returnPlayernotPlaying().GetID()]=0;
	     game.returnRound().TurnSetTrue(game.returnRound().returnPlayerPlaying().GetID());
	     fixAblePlayer();
	     game.returnRound().returnPlayerPlaying().returnRailyard().sortList();
	     game.returnRound().returnPlayerPlaying().returnRailyard().MoveTopCardsonRailyard();
	     game.returnRound().returnPlayerPlaying().returnOnTheTrack().ArrangeFromRailyard(game.returnRound().returnPlayerPlaying().returnRailyard().getcardsremoved());
	     game.returnRound().returnPlayerPlaying().returnRailyard().clearcardsremoved();
	     updateRailyard(game.returnRound().returnPlayerPlaying().GetID());
	     updateOnTheTrack(game.returnRound().returnPlayerPlaying().GetID());
	     
	}
 } 
 

}}}







