/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 2
 * Due Date: 18th Feb 2024*/

package application;

public class QueueOverflowException extends Exception{

	/*occurs when a enqueue method is called on a full queue.*/
	public QueueOverflowException()
	{
		super("Queue overflow");
	}
}
