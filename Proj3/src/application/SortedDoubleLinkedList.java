/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 3
 * Due Date: 10th Mar 2024*/
package application;

import java.util.Comparator;
import java.util.ListIterator;


public class SortedDoubleLinkedList <T> extends BasicDoubleLinkedList<T>{

	private Comparator<T> object;
	
	public SortedDoubleLinkedList(Comparator<T> comparableObject)
	{
		//create empty list that is associate with specified comparator
		super();
		this.object = comparableObject;

	}
	
	public void add(T data)
	{
		//insert the specified element at the correct position in the sorted list
		//can insert same element several times,
		//must traverse list only once in order to perform insertion
		Node<T> temp = new Node<>(data);
		if(head == null)
		{
			head = temp;
			tail = temp;
			
		}
		//else if data is smaller/equal to head (ascending)
		else if(object.compare(data, head.data) <= 0)
		{
			temp.next = head;
			head.prev = temp;
			head = temp;
		}
		else
		{
			Node<T> current = head.next;
			Node<T> prev = head;
			//while next isn't empty, exit loop when next element is smaller than data
			while(current != null && object.compare(data, current.data) > 0)
			{
				prev = current;
				current = current.next;
			}
			temp.next = current;
			temp.prev = prev;
			prev.next = temp;
		
			if(current != null)
			{
				current.prev = temp;
			}
			else
			{
				tail = temp;
			}
			
		}
		size++;
	}
	
	@Override
	public void addToEnd(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	@Override
	public void addToFront(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	@Override
	public ListIterator<T> iterator()
	{
		return super.iterator();
	}
	
	@Override
	public Node<T> remove(T data, Comparator<T> comparator)
	{
		//implements the remove operation by calling super class remove method
		//return a node containing the data or null
		return super.remove(data, comparator);
	}
	
}
