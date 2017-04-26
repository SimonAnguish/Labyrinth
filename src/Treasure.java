/**
   @Authors YifanChang, SeanKates
 * Treasure
 */

import java.util.*;

public class Treasure {

	// Initialize the value property for the treasure
	private int value;

	/**
	 * Constructor creates a new Treasure with a specific value
	 * @param value the value to be created
	 */
	public Treasure(int value) {
		this.value = value;
	}

	/**
	 * getValue() meturns the numeric value of the Treasure
	 * @return value The numeric value of the Treasure.
	 */
	public int getValue() {     
		return value;
	}
   
   /**
	 * setValue() sets the numeric value of the Treasure
	 * @param value The numeric value of the Treasure.
	 */
	public void setValue(int v) {     
		this.value = v;
	}

	/**
	 * toString method converts the value to a string
	 * @return nString the value of the Treasure as a string
	 */
	public String toString(){    
		String nString = null;
		switch (value) {
		case 1:
			nString = "A";
			break;
		case 2:
			nString = "B";
			break;
		case 3:
			nString = "C";
			break;
		case 4:
			nString = "D";
			break;
		case 5:
			nString = "E";
			break;
		case 6:
			nString = "F";
			break;
		case 7:
			nString = "G";
			break;
		case 8:
			nString = "H";
			break;
		case 9:
			nString = "I";
			break;
		case 10:
			nString = "J";
			break;
		case 11:
			nString = "K";
			break;
		case 12:
			nString = "L";
			break;
		case 13:
			nString = "M";
			break;
		case 14:
			nString = "N";
			break;
		case 15:
			nString = "O";
			break;
		case 16:
			nString = "P";
			break;
		case 17:
			nString = "Q";
			break;
		case 18:
			nString = "R";
			break;
		case 19:
			nString = "S";
			break;
		case 20:
			nString = "T";
			break;
		case 21:
			nString = "U";
			break;
		case 22:
			nString = "V";
			break;
		case 23:
			nString = "W";
			break;
		case 24:
			nString = "X";
			break;
		case 0:
			nString = "no treasure";
		}
		return nString;
	}
}
