/*
Project Team J (COEN 275)
Name : Meenakshy Harikumar	SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013
*/
package storage;
import java.lang.String;

//Class that is used to store the attributes of a book in BookList.txt.
public class Book 
{

//Variables to store the title, author, book number, location, price, status(available,borrowed,borrowed & reserved, reserved) of a book. 
private String title;
private String author;
private String bookNumber;
private int location;
private double price;
private String status;

//Variable to store the type( fiction or non fiction ) of a book.
private String type;

//Variable to store the maximum of books of its type that can be borrowed by a member at a time.
private int bookMaxNumber;

//variable to store the maximum number of days a book of its type can be checked out. 
private int bookMaxTime;

//Variable to store the daily fine accrued by a book of its type.
private double bookDailyFine;

//Variable to store the id of the member who borrowed this book.
private String memberWhoBorrowed;

//Variable to store the id of the member who reserved this book.
private String memberWhoReserved;


//Constructor which initializes values of its data members.
public Book(String bookNumber,String title,String status,String author ,String type,int location,double price,String memberWhoBorrowed,String memberWhoReserved)
{
	this.bookNumber=bookNumber;
	this.title=title;
	this.status=status;
	this.author=author;
	this.type=type;
	this.location=location;
	this.price=price;
	this.memberWhoBorrowed=memberWhoBorrowed;
	this.memberWhoReserved=memberWhoReserved;
	
	//If it's a fiction book, set the values of the following 3 attributes.
	 if(type.equals("F"))
	 {
	 this.bookMaxNumber=3;
	 this.bookMaxTime=14;
	 this.bookDailyFine=0.15;
	 }
	 
	//If it's a non-fiction book, set the values of the following 3 attributes.
	 if(type.equals("NF"))
	 {
	 this.bookMaxNumber=2;
	 this.bookMaxTime=14;
	 this.bookDailyFine=0.30;
	 }
	 
}

//Functions to get the values of a book's attributes.
public String getBookNumber()
{
	return this.bookNumber;
}
public String getTitle()
{
	return this.title;
}
public String getAuthor()
{
	return this.author;
}
public String getStatus()
{
	return this.status;
}
public double getPrice()
{
	return this.price;
}
public String getType()
{
	return this.type;
}
public double getBookDailyFine()
{
	return this.bookDailyFine;
}
public int getBookMaxTime()
{
	return this.bookMaxTime;
}
public String getMemberWhoReserved()
{
	return this.memberWhoReserved;
}
public String getMemberWhoBorrowed()
{
	return this.memberWhoBorrowed;
}

//Functions to set the values of a book's attributes.
public void setStatus(String status)
{
	this.status=status;	
}
public void setMemberWhoBorrowed(String memberWhoBorrowed)
{
	this.memberWhoBorrowed=memberWhoBorrowed;
}
public void setMemberWhoReserved(String memberWhoReserved)
{
	this.memberWhoReserved=memberWhoReserved;
}

//Function to write into the BookList.txt.
public String writeToFile()
{
	return this.bookNumber+"||"+this.title+"||"+this.status+"||"+this.author+"||"+this.type+"||"+this.location+"||"+this.price+"||"+this.memberWhoBorrowed+"||"+this.memberWhoReserved+"||";
}

//Function to display the attributes of a book when its printed.
public String toString()
{  
   return "Title =" + title + ", Book Number=" + bookNumber + ", Status=" + status +", Author="+author+", Type=" +type+", Location="+location+", Price="+price+"\n";
}
}


