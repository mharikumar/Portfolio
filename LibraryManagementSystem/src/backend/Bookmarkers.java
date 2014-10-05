/*
Project Team J (COEN 275)
Name : Meenakshy Harikumar	SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013
*/
package backend;
import java.io.*;


import storage.*;

import java.util.*;
import java.lang.*;

//Class Bookmarkers which acts as a middle man for the interface classes and the storage classes.
public class Bookmarkers
{
	//Declaring the objects of the various managers.
	private ItemManager IM;
	private MemberManager M;
	private CheckoutManager C;
	private HistoryManager H;
	
	//Constructor which creates the objects of the various managers.
  public Bookmarkers()
  {
	IM=new ItemManager();
	M=new MemberManager();
	C=new CheckoutManager();
    H=new HistoryManager();
  }
  
  //Function to query by book number.
  public Map<String,Book> queryByBookNumber(String Booknumber)
  {
	  Map<String,Book> tempBookMap=new HashMap<String,Book>();		
		tempBookMap=IM.queryByBookNumber(Booknumber);
		return tempBookMap;
  }
  
//Function to query by book title.
	public Map<String,Book> queryByBookTitle(String Booktitle)
	{
		Map<String,Book> tempBookMap=new HashMap<String,Book>();		
		tempBookMap=IM.queryByBookTitle(Booktitle);
		System.out.println(tempBookMap);
		return tempBookMap;
	}
	
	//Function to query by video number.
	public Map<String,Video> queryByVideoNumber(String Videonumber)
	{
		Map<String,Video> tempVideoMap=new HashMap<String,Video>();		
		tempVideoMap=IM.queryByVideoNumber(Videonumber);
		System.out.println(tempVideoMap);
		return tempVideoMap;
	}
	
	//Function to query by video title.
	public Map<String,Video> queryByVideoTitle(String Videotitle)
	{
		Map<String,Video> tempVideoMap=new HashMap<String,Video>();		
		tempVideoMap=IM.queryByVideoTitle(Videotitle);
		System.out.println(tempVideoMap);
		return tempVideoMap;
	}
	
	//Function to query by author of the book.
	public Map<String,Book> queryByAuthor(String Author)
	{
		return IM.queryByAuthor(Author);
	}
	
	//Function to query by director of the video.
	public Map<String,Video> queryByDirector(String director)
	{
		return IM.queryByDirector(director);
	}
	
	//Function to reserve a video.
	public int reserveVideo(String memberId,String videoNumber)
	{
		return IM.reserveVideo(memberId,videoNumber);
	}
	
	//Function to create a new member.
	public String createNewMember(String name,String email,String phone,String address,Date membershipStartDate,String county)
	{
		return M.createNewMember(name,email,phone,address,membershipStartDate,county);
	}
	
	//Function to check if  a member exists.
	public int checkIfMember(String memberId)
	{
		return M.checkIfMember(memberId);
	}
	
	//Function to reserve a book.
	public int reserveBook(String memberId,String bookNumber)
	{
		return IM.reserveBook(memberId,bookNumber);
	}
	
	//Function to calculate fine of an item while returning it.
	public double calcFineWhileReturning(String itemNumber,String memberId)
	{
		return C.calcFineWhileReturning(itemNumber,memberId);
	}
	
	//Function to check out a book.
	public Map<String,CheckedOutItems> checkOutBook(String memberId,String bookNumber,CheckOut C1)
	{
		Map<String,CheckedOutItems> checkoutMap;
		checkoutMap=C.checkOutBook(memberId,bookNumber,C1);
		
		return checkoutMap;
	}
	
	//Function to check out a video.
	public Map<String,CheckedOutItems> checkOutVideo(String memberId,String videoNumber,CheckOut Co)
	{
		return C.checkOutVideo(memberId,videoNumber,Co);
	}
	
	//Function to calculate the total fine owed by a member.
	public double calcTotalFine(String memberId)
	{
		return C.calcTotalFine(memberId);
	}
	
	//Function to return all late items borrowed by a member.
	public int returnAllLateItems(String memberId)
	{
		int fine=C.returnAllLateItems(memberId);
		return fine;
	}
	
	//Function to return an item.
	public int returnItem(String memberId,String itemNumber)
	{
		return C.returnItem(memberId,itemNumber);
	}
	
	//Function to create a new transaction entry in "HistoryList.txt".
	public void createNewTransaction(String memberId,Date currentDate,String itemId,double fine, String type)
	{
		H.createNewTransaction(memberId,currentDate,itemId,fine,type);
	}
	
	//Function to query the transaction history.
	public Map<String,History> queryTransactionHistory(String memberId,Date date1,Date date2)
	{
		return H.queryTransactionHistory(memberId,date1,date2);
	}
	
	//Function to claim lost item.
	public double claimLostItem(String memberId, String itemId)
	{
        return IM.claimLostItem(memberId,itemId);
	}
	
	//Function to remove an item claimed as lost from all the lists.
	public int removeLostItem(String memberId,Date date,String itemId,double fine)
	{
		IM.removeLostItem(memberId,date,itemId,fine);
		C.removeLostItem(itemId);
		return 1;
	}
	
	//Admin Functions 
	
	//Function to find total no of items in library.
	public int totalNoOfItemsInLib() 
	{
		return IM.totalNoOfItemsInLib();
	}
	
	//Function to find total no of members.
	public int totalNoOfMembers() 
	{
		return M.totalNoOfMembers();
	}
	
	//Function to find the total number of items checked out.
	public int totalNoOfItemsCheckedOut()
	{
		return C.totalNoOfItemsCheckedOut();
	}
	
	//Function to find the most checkedout/popular item in the library.
	public String mostPopularItem(Date date1, Date date2)
	{
		return H.mostPopularItem(date1,date2);
	}
	
	//Function to find the total amount of fines collected during a specific period of time.
	public double totalFinesCollected(Date date1,Date date2)
	{
		return H.totalFinesCollected(date1,date2);
	}
	
	//Function to find total number of fiction books.
	public int totalNoOfFicBooks()
	{
		return IM.totalNoOfFicBooks();
	}
	
	//Function to find the total number of non-fiction books in the library.
	public int totalNoOfNonFicBooks()
	{
		return IM.totalNoOfNonFicBooks();
	}
	
	//Function to find total number of video in the library.
	public int totalNoOfVideos()
	{
		return IM.totalNoOfVideos();
	}
	
	//Function to get the book map from item manager.
	public Map<String,Book> getBookMap()
	{
		return IM.getBookMap();
	}
	
	//Function to get the latest book in the library to show on the moving banner in UI.
	public Book getLatestBook()
	{
		return IM.getLatestBook();
	}
	
	//Function to get the latest video in the library to show on the moving banner in UI.
	public Video getLatestVideo()
	{
		return IM.getLatestVideo();
	}
}