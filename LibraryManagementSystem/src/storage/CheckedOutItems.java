/*
Project Team J (COEN 275)
Name : Meenakshy Harikumar	SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013
*/
package storage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

//Class that is used to store the attributes which are used to fill the "CheckedOutList.txt" file.
public class CheckedOutItems
{
	//Variable to store the id of the item that has been checked out.
	private String itemNumber;
	
	//Variable to store the id of the member who has checked out the item.
	private String memberId;
	
	//Variable to store the date at which the item has been checked out.
	private Date dateCheckedOut;
	
	//Variable to store the due date at which the checked out item must be returned to avoid fine.
	private Date dueDate;
	
	////Variable to store the fine that has to be paid when this member returns this item. 
	private double fine;
	
	//Constructor which initializes values of its data members.
	public CheckedOutItems(String itemNumber,String memberId,Date dateCheckedOut,Date dueDate,double fine)
	{
		this.itemNumber=itemNumber;
		this.memberId=memberId;
		this.dateCheckedOut=dateCheckedOut;
		this.dueDate=dueDate;
		this.fine=fine;
	}
	
	//Function to write into the CheckedOutList.txt.
	public String writeToFile()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		return this.itemNumber+"||"+this.memberId+"||"+dateFormat.format(this.dateCheckedOut)+"||"+dateFormat.format(this.dueDate)+"||"+this.fine+"||";
	}
	
	//Function to display the fields of this class when its printed.
	public String toString()
	{  
	  return "ItemNumber=" + itemNumber +", Member Id =" + memberId+", Date Checked Out=" + dateCheckedOut+", Due date="+dueDate+", Fine=" +fine+"\n";
	
	}
	
	//Functions to get and set the values of this class' attributes.
	public String getMemberId()
	{
		return this.memberId;
	}
	public String getItemNumber()
	{
		return this.itemNumber;
	}
	public void setFine(double fine)
	{
		this.fine=fine;
	}
	public Date getDueDate()
	{
		return this.dueDate;
	}
	
}