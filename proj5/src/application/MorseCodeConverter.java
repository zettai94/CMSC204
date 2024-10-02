package application;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseCodeConverter {

	private static MorseCodeTree tree = new MorseCodeTree();
	
	public MorseCodeConverter()
	{
		
	}
	
	public static String convertToEnglish(String code)
	{
		StringBuilder sentence = new StringBuilder();
		String[] lines = code.split("\n");
		for (String line: lines)
		{
			line = line.trim();
			String[] words = line.split(" / "); //contains "code code"
			for(String word : words)
			{
				//insert code into a string array 
				String[] letters = word.split(" ");
				for(String singleCode : letters)
				{
					//convert into sentence;
					//dont include anything that return null from fetch
					//code at most should be 4 length
					if(singleCode.length() <=4)
					{
						if(tree.fetch(singleCode) != null)
						{
							sentence.append(tree.fetch(singleCode));
							
						}
					}
				}
				sentence.append(" ");
			}
			//trim the sentence before adding newline
			sentence = new StringBuilder(sentence.toString().trim());
			sentence.append("\n");
		
		}
		return sentence.toString().trim();
	}
	
	public static String convertToEnglish(File codeFile) throws FileNotFoundException
	{
		StringBuilder codeSentence = new StringBuilder();
		Scanner read = new Scanner(codeFile);
		while(read.hasNextLine())
		{
			codeSentence.append(read.nextLine());
			codeSentence.append("\n");
		}
		read.close();
		return convertToEnglish(codeSentence.toString().trim());
	}
	
	public static String printTree()
	{
		String line = "";
		for(String letter : tree.toArrayList())
		{
			line += letter + " ";
		}
		return line.trim();
	}
	
}
