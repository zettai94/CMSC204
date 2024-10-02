/*Name: Silvia Lim
 * MCID: M5114781
 * CRN: 31370
 * Professor: Gary Thai
 * Description: Project 4
 * Due Date: 31st Mar 2024*/

public class CourseDBElement implements Comparable<CourseDBElement>{

	private String courseID, roomNo, profName;
	private int CRN, credit;
	
	public CourseDBElement()
	{
		this.courseID = null;
		this.roomNo = null;
		this.profName = null;
		this.CRN = 0;
		this.credit = 0;
	}
	
	public CourseDBElement(String courseID, int CRN, int credit,
			String roomNo, String profName)
	{
		//do not populate database with any course that is missing information
		//or is invalid element, using println to display message;
		if(courseID == null|| courseID.isEmpty())
		{
			System.out.println("Course ID is required");
			return;
		}
		
		if(/*CRN>=10000 && CRN <=99999*/Integer.toString(CRN).length()!=5)
		{
			System.out.println("CRN must be a 5 digit number");
			return;
		}
		
		if(credit <1 || credit > 4)
		{
			System.out.println("Number of credits must be between 1 and 4");
			return;
		}
		
		if(roomNo.isEmpty() || roomNo == null)
		{
			System.out.println("A room number is required");
			return;
		}
		if(profName.isEmpty() || profName == null)
		{
			System.out.println("An instructor (name) must be assigned to a course");
			return;
		}
		
		
		this.courseID = courseID;
		this.roomNo = roomNo;
		this.profName = profName;
		this.CRN = CRN;
		this.credit = credit;
	}
	
	@Override
	public int compareTo(CourseDBElement object)
	{
		if(this.CRN < object.CRN)
		{
			return -1;
		}
		else if(this.CRN > object.CRN)
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
	}
	
	//getter just in case
	public String getID()
	{
		return this.courseID;
	}
	
	public String getProfName()
	{
		return this.profName;
	}
	
	public String getRoomNum()
	{
		return this.roomNo;
	}
	
	public int getCredit()
	{
		return this.credit;
	}
	
	public int getCRN()
	{
		return this.CRN;
	}
	
	@Override
	public int hashCode()
	{
		return Integer.toString(CRN).hashCode();
	}
	
	@Override
	public String toString()
	{
		return "Course:" + this.courseID + " CRN:" + this.CRN + " Credits:" + this.credit
				+ " Instructor:" + this.profName + " Room:" + this.roomNo;
	}
	
	//setter
	public void setCourseID(String courseID)
	{
		this.courseID = courseID;
	}
	
	public void setRoomNum(String room)
	{
		this.roomNo = room;
	}
	
	public void setInstructor(String prof)
	{
		this.profName = prof;
	}
	public void setCRN(int crn)
	{
		this.CRN = crn;
	}
	
	public void setCredits(int credits)
	{
		this.credit = credits;
	}
}
