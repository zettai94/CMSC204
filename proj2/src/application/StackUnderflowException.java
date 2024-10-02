/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 2
 * Due Date: 18th Feb 2024*/

package application;

public class StackUnderflowException extends Exception{
	
	/*occurs when a top or pop method is called on an empty stack.*/
	public StackUnderflowException()
	{
		super("Stack underflow");
	}

}
