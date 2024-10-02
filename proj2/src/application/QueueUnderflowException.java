/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 2
 * Due Date: 18th Feb 2024*/

package application;

public class QueueUnderflowException extends Exception {

	/*occurs occurs when a  dequeue method is called on an empty queue */
	public QueueUnderflowException()
	{
		super("Queue underflow");
	}
}
