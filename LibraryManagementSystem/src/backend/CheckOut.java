/*
Project Team J (COEN 275)
Name : Meenakshy Harikumar	SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013
*/
package backend;

//Class to store additional info when a member tries to check out an item. 
public class CheckOut
{
	//Variable to store reason why a member is not allowed to check out.
	private int reason;
	
	//Variable to store fine owed by a member if any.
	private double fine;
	
	//Variable to store title of the item.
	private String title;
	
	//Functions to set the value of the data members of this class.
	public void setReason(int reason)
	{
		this.reason=reason;
	}
	public void setTitle(String title)
	{
		this.title=title;
	}
	public void setFine(double fine)
	{
		this.fine=fine;
	}
	
	//Function to return the reason .
	public int getReason()
	{
		return this.reason;
	}
}