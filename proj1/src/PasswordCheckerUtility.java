
public class PasswordCheckerUtility {
	
	private static final int MIN = 6;
	private static final int MAX = 9;
	
	//constructor
	public PasswordCheckerUtility()
	{
		
	}
	
	
	
	public static void comparePasswords(java.lang.String password, java.lang.String passwordConfirm) throws UnmatchedException
	{
		//UnmatchedException - thrown if not same (case sensitive)
		if(!password.equals(passwordConfirm))
		{
			throw new UnmatchedException();
		}
	}
	
	public static boolean comparePasswordsWithReturn​(java.lang.String password, java.lang.String passwordConfirm)
	{
		//true if both same (case sensitive), false otherwise
		if(password.equals(passwordConfirm))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public static boolean isValidLength​(java.lang.String password) throws LengthException
	{
		//checks the password length requirement - The password must be at least 6 characters long
		//true if meet min length
		//LengthException - thrown if does not meet minimum length requirement
		if(password.length() >= MIN)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public static boolean hasUpperAlpha​(java.lang.String password) throws NoUpperAlphaException
	{
		
		int upper = 0;
		for(int i = 0; i < password.length(); i++)
		{
			if(Character.isUpperCase(password.charAt(i)))
			{
				upper++;
			}
		}
		if(upper == 0)
		{
			throw new NoUpperAlphaException();
		}
		return true;
	}
	
	public static boolean hasLowerAlpha​(java.lang.String password) throws NoLowerAlphaException
	{
		//Checks the password lowercase requirement - Password must contain at least one lowercase alpha character
		//NoLowerAlphaException - thrown if does not meet lowercase requirement
				int lower = 0;
				for(int i = 0; i < password.length(); i++)
				{
					if(Character.isLowerCase(password.charAt(i)))
					{
						lower++;
					}
				}
				if(lower == 0)
				{
					throw new NoLowerAlphaException();
				}
				return true;
		
	}
	
	public static boolean hasDigit​(java.lang.String password) throws NoDigitException
	{
		//Checks the password Digit requirement - Password must contain a numeric character
		//NoDigitException - thrown if does not meet Digit requirement
		int digit = 0;
		for(int i = 0; i < password.length(); i++)
		{
			if(Character.isDigit(password.charAt(i)))
			{
				digit++;
			}
		}
		if(digit == 0)
		{
			throw new NoDigitException();
		}
		return true;
	}
	
	public static boolean hasSpecialChar​(java.lang.String password) throws NoSpecialCharacterException
	{
		//Checks the password SpecialCharacter requirement - Password must contain a Special Character
		//NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
		int special = 0;
		for(int i = 0; i < password.length(); i++)
		{
			//if neither letter or digit
			if(!Character.isLetterOrDigit(password.charAt(i)))
			{
				special++;
			}
		}
		if(special == 0)
		{
			throw new NoSpecialCharacterException();
		}
		return true;
		
	}
	
	public static boolean NoSameCharInSequence​(java.lang.String password) throws InvalidSequenceException
	{
		//Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
		//InvalidSequenceException - thrown if meets Sequence requirement
		char char1, char2;
		int count;
		for(int i = 0; i < password.length()-1; i++)
		{
			//initiate count as 1
			char1 = password.charAt(i);
			//reinitiate count to 1 each round
			count = 1;
			//j always 1 letter after charAt(i)
			for(int j = i+1; j < password.length(); j++)
			{
				char2 = password.charAt(j);
				//check if first letter and second are the same
				//and is not a digit
				if(char1 == char2 && !Character.isDigit(char1))
				{
					count++;
					//once the count ever reach more than 2 throw exception
					if(count > 2)
					{
						throw new InvalidSequenceException();
					}
				}
			}
		}
		
		return true;
	}
	
	public static boolean isValidPassword​(java.lang.String password) throws LengthException, NoUpperAlphaException, 
		NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
	{
		//true if valid password (follows all rules from above), false if an invalid password
		//initiate status as true
		try
		{
			isValidLength​(password);
			hasUpperAlpha​(password);
			hasLowerAlpha​(password);
			hasDigit​(password);
			hasSpecialChar​(password);
			NoSameCharInSequence​(password);
			return true;
		}
		catch(LengthException | NoUpperAlphaException | NoLowerAlphaException
				 | NoDigitException | NoSpecialCharacterException | InvalidSequenceException e)
		{
			return false;
		}
		
	}
	
	public static boolean hasBetweenSixAndNineChars​(java.lang.String password)
	{
		//true if password contains 6 to 9 characters, false otherwise
		if(password.length() >= MIN && password.length() <= MAX)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isWeakPassword​(java.lang.String password) throws WeakPasswordException
	{
		//false if the password is valid and the length of password is NOT between 6 and 9 (inclusive).
		try
		{
			boolean validStatus = isValidPassword​(password);
			boolean notBetween = !hasBetweenSixAndNineChars​(password);
			
			if(validStatus && notBetween)
			{
				throw new WeakPasswordException();
			}
			return false;
			
		}
		catch(LengthException | NoUpperAlphaException | NoLowerAlphaException
				 | NoDigitException | NoSpecialCharacterException | InvalidSequenceException e)
		{
			return false;
		}
	}
	
	public static java.util.ArrayList<java.lang.String> getInvalidPasswords​(java.util.ArrayList<java.lang.String> passwords)
	{
		//ArrayList of invalid passwords in the correct format
	}

}
