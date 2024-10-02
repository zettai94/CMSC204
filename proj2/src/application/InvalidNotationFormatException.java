/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 2
 * Due Date: 18th Feb 2024*/
package application;

public class InvalidNotationFormatException extends Exception {
	
	/*occurs when a Notation format is incorrect*/
	public InvalidNotationFormatException()
	{
		super("Invalid notation format");
	}

}
