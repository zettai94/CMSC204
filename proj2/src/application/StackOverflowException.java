/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 2
 * Due Date: 18th Feb 2024*/

package application;

public class StackOverflowException extends Exception{

	/*occurs when a push method is called on a full stack. */
	public StackOverflowException()
	{
		super("Stack overflow");
	}
}
