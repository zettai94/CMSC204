package application;

import static org.junit.Assert.*;
import java.io.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MorseCodeConverter_STUDENT_Test {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testConvertToEnglishString() {
		assertEquals("zombifying brain with codes", 
				MorseCodeConverter.convertToEnglish("--.. --- -- -... .. ..-. -.-- .. -. --. / -... .-. .- .. -. / .-- .. - .... / -.-. --- -.. . ..."));
		assertEquals("should skip number", MorseCodeConverter.convertToEnglish("... .... --- ..- .-.. -.. / ... -.- .. .--. / -. ..- -- -... . .-. 123"));
	}

	@Test
	void testConvertToEnglishFile() {
		try
		{
			assertEquals("sleepless from coding\ntesting newline", MorseCodeConverter.convertToEnglish(new File("sleepless.txt")));
			assertEquals("how do i love thee let me count the ways", MorseCodeConverter.convertToEnglish(new File("howDoILoveThee.txt")));
			assertEquals("love looks not with the eyes but with the mind", MorseCodeConverter.convertToEnglish(new File("LoveLooksNot.txt")));
			assertEquals("im half crazy all for the love of you", MorseCodeConverter.convertToEnglish(new File("DaisyDaisy.txt")));
			assertEquals("give me your answer do", MorseCodeConverter.convertToEnglish(new File("Daisy.txt")));
		}
		catch (FileNotFoundException e)
		{
			assertTrue("No exception thrown", false);
		}
		
	}

	@Test
	void testPrintTree() {
		assertEquals("h s v i f u e l r a p w j  b d x n c k y t z g q m o", MorseCodeConverter.printTree());
	}

}
