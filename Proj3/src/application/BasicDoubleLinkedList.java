/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 3
 * Due Date: 10th Mar 2024*/
package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList <T> implements Iterable<T> {
	
	//All the entries are defined as protected so they can be accessed by the subclass
	protected Node<T> head;;
	protected Node<T> tail;
	protected int size;
	
	//constructor
	public BasicDoubleLinkedList()
	{
		//set initialize head, tail, size to null null and 0
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
		
	//inner class Node, protected
	protected static class Node<T>
	{
		//3 fields: data, prev and next reference;
		protected T data;
		protected Node<T> prev;
		protected Node<T> next;
		
		protected Node(Node<T> prev, T data, Node<T> next)
		{
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		
		protected Node(T data)
		{
			//not sure if needed but just in case
			this(null, data, null);
		}
	}
	
	//inner class DoubleLinkedListIterator that implements ListIterator for iterator method
	//inner class implements only hasNext(), next(), hasPrevious() and previous() method of ListIterator
	//all other methods can throw UnsupportedOperationException:
	@SuppressWarnings("hiding")
	protected class DoubleLinkedListIterator<T> implements ListIterator<T>
	{
		protected Node<T> next;
		protected Node<T> prev;
		protected int index;
		
		@SuppressWarnings("unchecked")
		protected DoubleLinkedListIterator()
		{
			this.next = (Node<T>) head;
			this.prev = null;
			this.index = 0;
		}
		
		@Override
		public boolean hasNext()
		{
			return index < size;
		}
		
		@Override
		public T next()
		{
			if(hasNext())
			{
				prev = next;
				next = next.next;
				index++;
				return prev.data;
			}
			else
			{
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public boolean hasPrevious()
		{
			return index > 0;
		}
		
		@Override
		public T previous()
		{
			if(!hasPrevious())
			{
				throw new NoSuchElementException();
			}
			
			if(next == null)
			{
				next = prev;
			}
			else
			{
				next = next.prev;
			}
			prev = next;
			index--;
			return prev.data;
			
		}
		
		//rest of the methods throw UnsupportedOperationException
		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public int nextIndex()
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void add(T data)
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public int previousIndex()
		{
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void set(T data)
		{
			throw new UnsupportedOperationException();
		}
		
	}
	
	
	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator<T>();
	}
	

	public int getSize()
	{
		return size;
	}
	
	public void addToEnd(T data)
	{
		//format (prev, data, next)
		Node<T> temp = new Node<>(tail, data, null);
		if(tail==null)
		{
			//if tail is empty, assign temp to head
			head = temp;
		}
		else
		{
			//if tail is taken, get next Node from tail to assign as temp (which has null tail)
			tail.next = temp;
		}
		tail = temp;
		size++;
	}
	
	public void addToFront(T data)
	{
		Node<T> temp = new Node<>(null, data, head);
		if(head==null)
		{
			//empty link, assign temp to tail
			tail = temp;
		}
		else
		{
			//else if head taken, move pointer to previous to assign as temp
			head.prev = temp;
		}
		head = temp;
		size++;
	}
	
	public T getFirst()
	{
		if(head!=null)
		{
			return head.data;
		}
		else
		{
			return null;
		}
	}
	
	public T getLast()
	{
		if(tail!=null)
		{
			return tail.data;
		}
		else
		{
			return null;
		}
	}
	
	public Node<T> remove(T targetData, Comparator<T> comparator)
	{
		Node<T> current = head;
		//loop thru all nodes
		while(current!=null)
		{
			//if targetData is same as current data
			if(comparator.compare(targetData, current.data) == 0)
			{
				//and current data equals head
				//update head to next node
				if(current == head)
				{
					head = current.next;
				}
				
				//if current equals tail, move to prev
				if(current == tail)
				{
					tail = current.prev;
				}
				
				//if the pointer head wasnt the first ele
				if(current.prev!=null)
				{
					//assign ele next to current into the current location
					current.prev.next = current.next;
				}
				
				//if ele next to current also not empty
				if(current.next!=null)
				{
					current.next.prev = current.prev;
				}
				
				//complete remove
				size--;
				return current;
			}
			//update current to next
			current = current.next;
		}
		return null;
	}
	
	public T retrieveFirstElement()
	{
		//if there are no elements, return null
		if(head==null)
		{
			return null;
		}
		
		//place holder to be return
		Node<T> temp = head;
		//assign head to the next element
		head = head.next;
		//check if head now is empty or not
		//if head is empty, list is now empty
		if(head!=null)
		{
			//delete the element before head (which was the orig head)
			head.prev = null;
		}
		else
		{
			tail = null;
		}
		size--;
		
		return temp.data;
	}
	
	public T retrieveLastElement()
	{
		//if there's no element, return null
		if(head == null)
		{
			return null;
		}
		
		//place holder for tail to be return
		Node<T> temp = tail;
		//move pointer to tail.prev
		tail = tail.prev;
		if(tail!=null)
		{
			//nullify the next element to tail
			tail.next = null;
		}
		else
		{
			//when head not null and tail is null, it is end of list
			//crash when tail = null
			head = null;
		}
		size--;
		return temp.data;
	}
	
	
	public ArrayList<T> toArrayList()
	{
		//return an array list of all items in DoubleLinkedList
		ArrayList<T> temp = new ArrayList<>();
		Node<T> current = head;
		while(current!=null)
		{
			temp.add(current.data);
			current = current.next;
		}
		
		return temp;
	}

}
