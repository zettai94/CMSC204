/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 2
 * Due Date: 18th Feb 2024*/

package application;

import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {

	private T[] queue;
	private int frontIndex, backIndex, size;
	//int as the size of queue
	private static int DEFAULT = 50;
	
	//default constructor
	public MyQueue()
	{
		this(DEFAULT);
	}
	
	//constructor has int initial as parameter and assign to queue's size
	public MyQueue(int initial)
	{
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[initial +1];
		queue = temp;
		frontIndex = 0;
		backIndex = initial;
	}
	
	public boolean isEmpty()
	{
		return frontIndex == ((backIndex + 1) % queue.length);
	}
	
	public boolean isFull()
	{
		return frontIndex == ((backIndex + 2) % queue.length);
	}
	
	public T dequeue() throws QueueUnderflowException
	{
		T front = null;
		//throw QueueUndeflowException if the queue is empty
		//else assign first element of queue to front and delete the element
		//frontIndex will be updated
		if(isEmpty())
		{
			throw new QueueUnderflowException();
		}
		else
		{
			front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			size--;
		}
		
		return front;
	}
	
	public boolean enqueue(T e) throws QueueOverflowException
	{
		if(isFull())
		{
			throw new QueueOverflowException();
		}
		else
		{
			backIndex = (backIndex + 1) % queue.length;
			queue[backIndex] = e;
			size++;
			return true;
		}
	}
	
	@Override
	public String toString()
	{
		String line = "";
		for(T s: queue)
		{
			if(s!=null)
			{
				line += s;
			}
		}
		return line;
	}
	
	@Override
	public String toString(String delimiter)
	{
		String line = "";
		for(int i = 0; i <= backIndex; i++)
		{
			if(queue[i]!=null)
			{
				line += queue[i];
				if(i!=backIndex)
				{
					line += delimiter;
				}
			}
		}
		return line;
	}
	
	public void fill(ArrayList<T> list) throws QueueOverflowException
	{
		if(list.size() > queue.length)
		{
			throw new QueueOverflowException();
		}
		else
		{
			for(T e: list)
			{
				enqueue(e);
			}
		}
	}
	
	public int size()
	{
		return size;
	}

}
