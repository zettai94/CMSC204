package application;

public class personalTesting {
	
	public static void main(String[] args)
	{
		MyQueue<String> stringQ = new MyQueue<String>(5);
		String a="a", b="b", c="c", d="d", e="e", f="f";
		
		String complexInfix = "(3+(((5*7)-(((8/2)-1)*4))*6))";
		String intermediateInfix = "((3*(5+4))+2)";
		
		MyStack<String> stringS = new MyStack<String>(5);
		try
		{
//			stringQ.enqueue(a);
//			stringQ.enqueue(b);
//			stringQ.enqueue(c);
//			System.out.println(stringQ);
//			System.out.println(stringQ.toString("/"));
//			stringQ.enqueue(e);
//			System.out.println(stringQ.toString("&"));
//			System.out.println(stringQ.size());
//			System.out.println(stringQ.dequeue());
//			System.out.println(stringQ.size());
//			System.out.println(stringQ);
			
//			stringS.push(a);
//			stringS.push(b);
//			stringS.push(c);
//			System.out.println(stringS);
//			System.out.println(stringS.toString("/"));
//			stringS.push(d);
//			stringS.push(e);
//			stringS.push(f);
//			System.out.println(stringS.size());
			MyStack<Double> doubleS = new MyStack<>(4);
//			double one = 0.5;
//			double two = 9.8;
//			double three = 1.3;
//			double four = 0.77;
//			doubleS.push(one);
//			doubleS.push(two);
//			doubleS.push(three);
//			System.out.println(doubleS);
			
			System.out.println(Notation.convertInfixToPostfix("2^"));
		}
		catch(/*QueueOverflowException | QueueUnderflowException | StackOverflowException*/ InvalidNotationFormatException ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}

}
