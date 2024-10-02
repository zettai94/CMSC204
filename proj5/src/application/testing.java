package application;

import java.io.*;

import java.util.ArrayList;

public class testing {

	public static void main(String[] args)
	{
		MorseCodeTree test = new MorseCodeTree();
		
		//displayTree(test.getRoot());
		
		//System.out.println(test.fetch("..-."));
		ArrayList<String> a = test.toArrayList();
		
		//System.out.println(a);
		String converter1 = MorseCodeConverter.convertToEnglish(".... . .-.. .-.. --- / .-- --- .-. .-.. -.. ");
		//System.out.println(converter1);
		
		String withNum = MorseCodeConverter.convertToEnglish("... .... --- ..- .-.. -.. / ... -.- .. .--. / -. ..- -- -... . .-. 123");
		//System.out.println(withNum);
		
		//assuming shoulnt do anything if not morsecode
		//System.out.println(MorseCodeConverter.convertToEnglish("abc"));
		
		
		try
		{
			String convertFile = MorseCodeConverter.convertToEnglish(new File("sleepless.txt"));
			System.out.println(convertFile);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println(MorseCodeConverter.printTree());
	}
	
	public static void displayTree(TreeNode<String> node)
	{
	   if(node != null) 
	    {
	    	//in order
	        displayTree(node.getLeftChild());
	        System.out.println(node.getData());
	        displayTree(node.getRightChild());
	    }
	 }
}
