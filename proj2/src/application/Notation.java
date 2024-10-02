/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 2
 * Due Date: 18th Feb 2024*/

package application;

public class Notation {

	//default constructor
	public Notation()
	{
		
	}
	
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException
	{
		MyStack<String> value = new MyStack<>();
		try
		{
			for(int i = 0; i < postfixExpr.length(); i++)
			{
				char letter = postfixExpr.charAt(i);
				if(Character.isDigit(letter))
				{
					//push the operand into value Stack
					value.push(Character.toString(letter));
				}
				else if(letter == '^' ||letter == '+' || letter == '-' || letter == '*' || letter == '/' || letter == '%')
				{
					//when encountering operators, check the value Stack
					//if there's less than 2 operands, throw exception
					if(value.size() < 2)
					{
						throw new InvalidNotationFormatException();
					}
					
					//first pop is last entry
					//second pop is second last entry
					double num2 = Double.parseDouble(value.pop());
					double num1 = Double.parseDouble(value.pop());
					double result = 0;
					switch (letter)
					{
						case'^': 
							result = Math.pow(num1, num2);
							break;
						case '+':
							result = num1 + num2;
							break;
						case '-':
							result = num1 - num2;
							break;
						case '*':
							result = num1 * num2;
							break;
						//throw InvalidNotationFormatException when num2 = 0
						case '/':
							if(num2 == 0)
							{
								throw new InvalidNotationFormatException();
							}
							//return int instead of double here
							//"5/3" example from instruction
							result = (int) (num1 / num2);
							break;
						case '%':
							result = num1 % num2;
					}
					value.push(Double.toString(result));
				}
			}
		}
		catch(StackOverflowException | StackUnderflowException e)
		{
			
		}
		
		//check if all values have been operated
		//else throw exception
		if(value.size() != 1)
		{
			throw new InvalidNotationFormatException();
		}
		
		return Double.parseDouble(value.toString());
	}
	
	
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException
	{	
		MyStack<String> stackExpression = new MyStack<>();
		
		try
		{
			for(int i = 0; i < postfix.length(); i++)
			{
				char letter = postfix.charAt(i);
				if(Character.isDigit(letter))
				{
					//push operand into stack
					stackExpression.push(Character.toString(letter));
				}
				else if(letter == '^' ||letter == '+' || letter == '-' || letter == '*' || letter == '/' || letter == '%')
				{
					//if there's less than 2 numbers in stack
					//throw exception
					if(stackExpression.size() < 2)
					{
						throw new InvalidNotationFormatException();
					}
					//else reconstruct a string with the operands and current operator
					//and push back into stack
					String letter2 = stackExpression.pop();
					String letter1 = stackExpression.pop();
					String infix = "(" + letter1 + letter + letter2 +")";
					stackExpression.push(infix);
				}
			}
			
		}
		catch(StackOverflowException | StackUnderflowException e)
		{
			
		}
		
		//all values should be operated and converted into a string
		//if stackExpression doesnt contain only 1 string, throw exception
		if(stackExpression.size() != 1)
		{
			throw new InvalidNotationFormatException();
		}
		return stackExpression.toString();
	}
	
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException
	{
		//throw InvalidNotationFormatException if the brackets are imbalance
		if(!checkBalance(infix))
		{
			throw new InvalidNotationFormatException();
		}
		
		MyStack<Character> stackOperator = new MyStack<>();
		MyQueue<Character> queuePostfix = new MyQueue<>();
		char topOperator;
		
		//for comparing previous letter
		char prevChar = '\0';
		
		try
		{
			for (int i = 0; i < infix.length(); i++)
			{
				//assign char at i to letter
				char letter = infix.charAt(i);
				
				if (Character.isDigit(letter))
				{
					queuePostfix.enqueue(letter);
				}
				else if(letter == '^')
				{ 
					//throw invalid if first letter or last letter equals ^
					//also throw if ^ appear back to back
					if(i == 0 || i == infix.length() -1 || prevChar == '^')
					{
						throw new InvalidNotationFormatException();
					}
					stackOperator.push(letter);
				}
				else if (letter == '+' || letter == '-' || letter == '*' || letter == '/' || letter == '%')
				{
					//if first and last character is operator
					//throw exception
					if (i == 0 || i == infix.length()-1)
					{
						throw new InvalidNotationFormatException();
					}
					
					//if character before is also operator, throw exception
					if(prevChar == '+' || prevChar == '-' || prevChar == '*' || prevChar == '/' || prevChar == '%')
					{
						throw new InvalidNotationFormatException();
					}
					
					//say if letter is + and top of stackOperator is ^
					//enqueue ^ before popping it out of stack
					//and repeat until either the stack is empty or 
					//a the precedence of popped operator is no longer larger or equal to precedence of letter
					while(!stackOperator.isEmpty() && (precedence(letter) <= precedence(stackOperator.top())))
					{
						queuePostfix.enqueue(stackOperator.top());
						stackOperator.pop();
					}
					
					//then method will push letter into stackOperator
					stackOperator.push(letter);
				}
				else if(letter == '(')
				{
					stackOperator.push(letter);
				}
				else if (letter == ')')
				{
					//when encountering close bracket,
					//check if last entry in stackOperator is opened bracket
					topOperator = stackOperator.pop();
					while(topOperator != '(')
					{
						//while it is not, enqueue into postfix expression
						//and continue to check the last entry until encounter open bracket
						queuePostfix.enqueue(topOperator);
						topOperator = stackOperator.pop();
					}
				}
				//assign prevChar as letter before another loop
				prevChar = letter;
			}
			
			while(!stackOperator.isEmpty())
			{
				topOperator = stackOperator.pop();
				queuePostfix.enqueue(topOperator);
			}
		}
		catch(StackOverflowException | StackUnderflowException | QueueOverflowException e)
		{
			
		}
			
		return queuePostfix.toString();
	}
	
	
	public static boolean checkBalance(String expression)
	{
		//declare a stack for all the delimiter
		MyStack<Character> openDelimiterStack = new MyStack<>();
		
		//declare count as the length of the expression
		int count = expression.length();
		
		//default balanced as true
		boolean balanced = true;
		
		//initiate index, start at first element
		int index = 0;
		try
		{
			//continue reading the expression while the delimiters are balanced
			while(balanced && index < count)
			{
				char nextChar = expression.charAt(index);
				switch(nextChar)
				{
					case '(':
						openDelimiterStack.push(nextChar);
						break;
					case ')':
						if(openDelimiterStack.isEmpty())
						{
							//exit while loop when balance = false;
							balanced = false;
						}
						else
						{
							//pop the last entry from stack
							char openDelimiter = openDelimiterStack.pop();
							//check if what was pop was balanced with nextChar
							balanced = isPaired(openDelimiter, nextChar);
						}
						break;
					//ignore those that's not brackets
					default:
						break;
				}
				index++;
			}
			
		}
		catch (StackOverflowException  | StackUnderflowException e)
		{
			
		}
		
		//also not balance if the stack has leftover
		if(!openDelimiterStack.isEmpty())
		{
			balanced = false;
		}
		
		return balanced;
	}
	
	//check if characters being popped and compared to are the same
	public static boolean isPaired(char open, char close)
	{
		return (open == '(' && close == ')');
	}
	
	//return precedence of operators
	public static int precedence(char operator)
	{
		switch (operator)
		{
			case '^':
				return 3;
			case '*' : case '/': case '%':
				return 2;
			case '+': case '-':
				return 1;
			default:
				return 0;
		}
	}
}
