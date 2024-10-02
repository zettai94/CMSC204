/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 2
 * Due Date: 18th Feb 2024*/

package application;

import java.util.ArrayList;

public class MyStack <T> implements StackInterface<T>{

	//attributes
	private T[] stack;
	private int top, size;
	private static final int DEFAULT = 50;
	
	//default constructor
	public MyStack()
	{
		this(DEFAULT);
	}
	
	//second constructor
	public MyStack(int initial)
	{
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[initial];
		stack = temp;
		top = -1;
		size = 0;
	}
	
	public boolean isEmpty()
	{
		return top < 0;
	}
	
	public boolean isFull()
	{
		return (top == stack.length - 1);
	}
	
	public T pop() throws StackUnderflowException
	{
		T topEle = null;
		if(isEmpty())
		{
			throw new StackUnderflowException();
		}
		else
		{
			topEle = stack[top];
			stack[top] = null;
			top--;
			size--;
		}
		return topEle;
	}
	
	public T top() throws StackUnderflowException
	{
		if(isEmpty())
		{
			throw new StackUnderflowException();
		}
		else
		{
			return stack[top];
		}
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean push(T e) throws StackOverflowException
	{
		//add an element to to the top of Stack
		//if isFull throw StackOverflowException
		// **StackInterface is asking for return false
		// **But JUnit is not asking for it
		if(isFull())
		{
			throw new StackOverflowException();
		}
		else
		{
			top++;
			stack[top] = e;
			size++;
			return true;
		}
		
	}
	
	@Override
	public String toString()
	{
		String line = "";
		for(T s: stack)
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
		for(int i = 0; i <= top; i++)
		{
			if(stack[i]!=null)
			{
				line += stack[i];
				if(i!=top)
				{
					line += delimiter;
				}
			}
		}
		return line;
	}
	
	public void fill(ArrayList<T> list) throws StackOverflowException
	{
		if(list.size() > stack.length)
		{
			throw new StackOverflowException();
		}
		else
		{
			for(T e: list)
			{
				push(e);
			}
		}
	}
}
