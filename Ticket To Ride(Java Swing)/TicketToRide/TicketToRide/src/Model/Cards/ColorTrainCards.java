package Model.Cards;

/**
 *  Colors that can be used
 *  <ul>
 *  <li>{@link #Yellow}</li>
 *  <li>{@link #Blue}</li>
 *  <li>{@link #Red}</li>
 *  <li>{@link #White}</li>
 *  <li>{@link #Black}</li>
 *  <li>{@link #Green}</li>
 *  <li>{@link #Purple}</li>
 *  <li>{@link #Orange}</li>
 *  <li>{@link #Locomotive}</li>
 *  </ul>
 */
public enum ColorTrainCards {
	
/*Yellow color
 *  * */	
Yellow(0),
/*Blue color
 *  * */	
Blue(1),
/*Red color
 *  * */	
Red(2),
/*White color
 *  * */	
White(3),
/*Black color
 *  * */	
Black(4),
/*Green color
 *  * */	
Green(5),
/*Purple color
 *  * */	
Purple(6),
/*Orange color
 *  * */	
Orange(7),
/*Locomotive color
 *  * */	
Locomotive(8);

/**{@link #value}:
 * Used to access Enum:ColoTrainCards by int value
 */
private int value;


/**<b><Constructor></b>:
  *Initializes ColorTrainsCards object with int value
  *@param value
  */
ColorTrainCards(int value){
	this.value=value;
}	

/**<b>Accessor</b>
  *
  * @return value
  */

public int getValue() {
	return value;
	
}



}

