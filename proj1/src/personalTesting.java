
public class personalTesting {
	
	public personalTesting()
	{
		try {
			System.out.println(PasswordCheckerUtility.isWeakPassword​("4Sales!"));
		} catch (WeakPasswordException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
