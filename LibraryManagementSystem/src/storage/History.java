/*
Project Team J (COEN 275)
Name : Meenakshy Harikumar	SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013
*/
package storage;
import java.lang.String;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

//Class that is used to store the fields of a transaction in HistoryList.txt.
public class History 
{
	//Variable to store the Transaction Id of a transaction( checkout, fine paid, return ).
	private String transactionId;
	
	//Variable to store the id of the member who performed the transaction.
	private String memberId;
	
	//Variable to store the date at which the transaction occurred.
	private Date date;
	
	//Variable to store the id of the item for which the transaction happened.
	private String itemId;
	
	//Variable to store the fine collected during this particular transaction.
	private double fine;
	
	//Variable to store the type of transaction that took place(Checkout='C',return='R', fine paid='F').
	private String type;
	
	//Constructor which initializes values of its data members.
	public History(String transactionId,String memberId,Date date,String itemId,double fine,String type)
	{
		this.transactionId=transactionId;
		this.memberId=memberId;
		this.date=date;
		this.itemId=itemId;
		this.fine=fine;
		this.type=type;
	}
	
	//Functions to get the values of a transaction's attributes.
	public String getTransactionId()
	{
		return this.transactionId;
	}
	public String getMemberId()
	{
		return this.memberId;
	}
	public Date getDate()
	{
		return this.date;
	}
	public String getType()
	{
		return this.type;
	}
	public String getItemId()
	{
		return this.itemId;
	}
	public double getFine()
	{
		return this.fine;
	}
	
	//Function to write into the HistoryList.txt.
	public String writeToFile()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		return this.transactionId+"||"+this.memberId+"||"+dateFormat.format(this.date)+"||"+this.itemId+"||"+this.fine+"||"+this.type+"||";
	}
	
	//Function to display the attributes of a transaction when its printed.
	public String toString()
	{  
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
	
	   return "[TransactionId =" + this.transactionId + ", member Id=" + this.memberId +",Transaction date="+dateFormat.format(this.date)+",Item Id="+this.itemId+",Fine Paid="+this.fine+",Type="+this.type+"]";
	}
}