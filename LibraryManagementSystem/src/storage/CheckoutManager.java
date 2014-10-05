/*
Project Team J (COEN 275)
Name : Meenakshy Harikumar	SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013
*/
package storage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import backend.CheckOut;

//Class CheckoutManager which keeps an updated checkout map.
//Also contains all functions related to checked out items.
public class CheckoutManager
{
	//Declaring checkoutMap which stores information into and from file "CheckedOutList.txt".
	private Map<String,CheckedOutItems> checkoutMap=new HashMap<String,CheckedOutItems>();
	
	//Constructor which updates checkoutMap with latest info from the file.
	public CheckoutManager()
	{
		FileInputStream fin=null;
		InputStreamReader isr = null;
	    BufferedReader br = null;
	    
		try
		{
		fin = new FileInputStream("CheckedOutList.txt");
	    isr = new InputStreamReader(fin, "UTF-8");
	    br = new BufferedReader(isr);
	    String line = br.readLine();	    
	    while (line != null) {
	       
	        String[] toks = line.split("[||]+");
	        
	      //Converting the fields in CheckedOutList.txt to the format required to store in a CheckedOutItems object.
	        DateFormat formatter ;
	        formatter = new SimpleDateFormat("dd-MMM-yy");	        
	        Date dateCheckedOut = formatter.parse(toks[2]);
	        Date dueDate = formatter.parse(toks[3]);
	        double fine=Double.parseDouble(toks[4]);
	        
	        CheckedOutItems C=new CheckedOutItems(toks[0],toks[1],dateCheckedOut,dueDate,fine);
	        checkoutMap.put(toks[0],C);
	        line = br.readLine();
	    }
	    if (br != null)  { br.close();  }
	    if (isr != null) { isr.close(); }
	    if (fin != null) { fin.close(); }
	    
		}
		catch (Exception e ){}
	
	}
	
	//Calculates fine of a single item.
	public double calcFineWhileReturning(String itemNumber,String memberId)
	{	
		//Checks if the item is a book and calculates fine to be paid.
		if( (itemNumber.charAt(0)=='F') || (itemNumber.charAt(0)=='N') )
		{
		try
		{
		double fine=0.0;
		CheckedOutItems COI=checkoutMap.get(itemNumber);
		if (COI==null) return 0;
		else
		{
		ItemManager IM=new ItemManager();
		Map<String,Book> tempBookMap=new HashMap<String,Book>();
		tempBookMap=IM.queryByBookNumber(itemNumber);
		Book Book1=tempBookMap.get(itemNumber);
		Date currentDate=new Date();
		
		//Calculating number of days passed since due date.
		double days=(currentDate.getTime()-COI.getDueDate().getTime())/(1000 * 60 * 60 * 24);
		
		//Calculating total fine on the book .
		fine=(days*Book1.getBookDailyFine());
		
		//If fine has exceeded price of book, make fine equal to price of book.
		if(fine>Book1.getPrice())
		{
			fine=Book1.getPrice();
		}
		else if (fine<0)
			fine=0;
		return fine;
		}
		}catch(Exception e){}
		}
		
		//Checking if item is a video and calculating fine on it.
		else if ( itemNumber.charAt(0)=='V')
		{
		try
		{
			double fine=0.0;
			CheckedOutItems COI=checkoutMap.get(itemNumber);
			if (COI==null) return 0;
			else
			{
			ItemManager IM=new ItemManager();
			Map<String,Video> tempVideoMap=new HashMap<String,Video>();
			tempVideoMap=IM.queryByVideoNumber(itemNumber);
			Video Video1=tempVideoMap.get(itemNumber);
			Date currentDate=new Date();
			
			//Calculating number of days passed since due date.
			double days=(currentDate.getTime()-COI.getDueDate().getTime())/(1000 * 60 * 60 * 24);
			
			//Calculating fine to be paid on this video.
			fine=(days*Video1.getVideoDailyFine());
			
			//If fine has exceeded price of video, make fine equal to price of video.
			if(fine>Video1.getPrice())
			{
				fine=Video1.getPrice();
			}
			else if (fine<0)
				fine=0;
			return fine;	
		} }catch(Exception e){}
		}
		return 0;
	}

	//Check out Book
public Map<String,CheckedOutItems> checkOutBook(String memberId,String bookNumber,CheckOut C)
{
	
	ItemManager IM=new ItemManager();
	Map<String,Book> tempBookMap=new HashMap<String,Book>();
	Map<String,Video> tempVideoMap=new HashMap<String,Video>();
	
	tempBookMap=IM.queryByBookNumber(bookNumber);
	
	Map<String,CheckedOutItems> tempCheckoutMap=new HashMap<String,CheckedOutItems>();
	// Check if invalid bookNumber, then set reason to 1 and respective values to other attributes to indicate that book cannot be checked out.
	if(tempBookMap.isEmpty()) 
	{	
		C.setReason(1);
		C.setTitle(null);
		C.setFine(0.0);
		tempCheckoutMap=null;
		return tempCheckoutMap;
	}
	
	Book Book1=tempBookMap.get(bookNumber);
	String status=Book1.getStatus();
	Date dueDate;	
	
	//Check if book is not available or not being borrowed by person who reserved it.
	//Then set reason to 3 and respective values to other attributes to indicate that book cannot be checked out.
	if (!(status.equals("A")||(status.equals("R")&& Book1.getMemberWhoReserved().equals(memberId))))
	{
		C.setReason(3);
		C.setTitle(null);
		C.setFine(0.0);
		tempCheckoutMap=null;
		return tempCheckoutMap;
		
	}	
	
	for(Map.Entry<String, CheckedOutItems> entry : checkoutMap.entrySet())
	{		
		CheckedOutItems COI=entry.getValue();			
		if(COI.getMemberId().equals(memberId) )
		{
			//tempCheckoumap contains checked out list of this member.
			tempCheckoutMap.put(COI.getItemNumber(),COI); 
			
		}
		
		
	}
	
	int countFiction=0;
	int countNonFiction=0;
	double totalFine=0.0;
	
	double days;
	double tempFine;
	double itemPrice;
	double dailyFine;
	
	//Looping through the tempCheckoutMap to find out if the member has exceeded their limit of fiction or nonfiction books.
	for(Map.Entry<String, CheckedOutItems> entry : tempCheckoutMap.entrySet())
	{
		Date currentDate=new Date();
		CheckedOutItems COI=entry.getValue();
		
		if(((entry.getKey()).charAt(0)=='N')||((entry.getKey()).charAt(0)=='F'))
		{	
			tempBookMap=IM.queryByBookNumber(entry.getKey());		
			Book Book2=tempBookMap.get(entry.getKey());		
		    
			//Incrementing value of number of fiction books checked out by this member when encountering a fiction book in tempCheckoutMap.
			if(Book2.getBookNumber().charAt(0)=='F') 
				countFiction++;		
			
			//Incrementing value of number of non fiction books checked out by this member when encountering a non fiction book in tempCheckoutMap.
			else if(Book2.getBookNumber().charAt(0)=='N') 
				countNonFiction++;	
			
			dailyFine = Book2.getBookDailyFine();
			itemPrice = Book2.getPrice();
					
		} else {
			tempVideoMap=IM.queryByVideoNumber(entry.getKey());		
			Video Video2=tempVideoMap.get(entry.getKey());			
			
			dailyFine = Video2.getVideoDailyFine();
			itemPrice = Video2.getPrice();			
		}
		//Calculating fine to be paid by member.
		days=(currentDate.getTime()-COI.getDueDate().getTime())/(1000 * 60 * 60 * 24);			
		tempFine=(days*dailyFine);
	
		if(tempFine>itemPrice)
		{
			tempFine=itemPrice;
		}		
		else if (tempFine < 0)
			tempFine=0;
		COI.setFine(tempFine);
		checkoutMap.put(entry.getKey(),COI);
		totalFine=totalFine + tempFine; 	
	}
	
	// If fine to be paid, set reason to 2 and values of other attributes to indicate that book cannot be checked out.
	if(totalFine>0)
	{
	
		C.setReason(2);
		C.setTitle(null);
		C.setFine(totalFine);
		tempCheckoutMap=null;
		return tempCheckoutMap;
	}
	
	// If maximum limit of fiction books reached,set reason to 4 and values of other attributes to indicate that a fiction book cannot be checked out.	
	if(Book1.getType().equals("F") && countFiction>=3)
	{
		 C.setReason(4); //Exceeded no of books limit. 
		 C.setTitle(null);
		 C.setFine(0.0);
		 tempCheckoutMap=null;
		 return tempCheckoutMap;
		
	}
	// If maximum limit of non fiction books reached,set reason to 4 and values of other attributes to indicate that a non fiction book cannot be checked out.
	else if (Book1.getType().equals("NF") && countNonFiction>=2)
	{
		 C.setReason(4); //Exceeded no of books limit. 
		 C.setTitle(null);
		 C.setFine(0.0);
		 tempCheckoutMap=null;
		 return tempCheckoutMap;
		
	}
	
	//Finally, add new check-out info to files.
	Date currentDate=new Date();
	Calendar c = Calendar.getInstance();
	c.setTime(new Date()); 
	c.add(Calendar.DATE,Book1.getBookMaxTime() ); 
	
	CheckedOutItems tempCOI=new CheckedOutItems(bookNumber,memberId,currentDate,c.getTime(),0.0);
	checkoutMap.put(bookNumber, tempCOI);
	
	try
	{
		    FileOutputStream fos=new FileOutputStream("CheckedOutList.txt");
	        PrintWriter pw=new PrintWriter(fos);

	        for(Map.Entry<String, CheckedOutItems> entry : checkoutMap.entrySet()){
	            pw.println(entry.getValue().writeToFile());
	        }

	        pw.flush();
	        pw.close();
	        fos.close();
	
	}catch(Exception e) {}
	
	//Setting status of book to borrowed.
	Book1.setStatus("B");
	Book1.setMemberWhoBorrowed(memberId);
	IM.getBookMap().put(bookNumber,Book1);
	IM.updateBookListFile();
	
	//Change HistoryList.txt
	
	HistoryManager hm=new HistoryManager();
	hm.createNewTransaction(memberId,currentDate,bookNumber,0.0,"C");
    
	//Putting entries corresponding to the member trying to check out now, from checkoutMap into tempCheckoutMap.
	for(Map.Entry<String, CheckedOutItems> entry : checkoutMap.entrySet())
	{
		CheckedOutItems COI=entry.getValue();			
		if(COI.getMemberId().equals(memberId) )
		{
			tempCheckoutMap.put(COI.getItemNumber(),COI);
			
		}
	}


	return tempCheckoutMap;
	
	
}

//Checkout Video
public Map<String,CheckedOutItems> checkOutVideo(String memberId,String videoNumber,CheckOut C)
{
	
	ItemManager IM=new ItemManager();
	Map<String,Video> tempVideoMap=new HashMap<String,Video>();
	Map<String,Book> tempBookMap=new HashMap<String,Book>();
	
	tempVideoMap=IM.queryByVideoNumber(videoNumber);
	Map<String,CheckedOutItems> tempCheckoutMap=new HashMap<String,CheckedOutItems>();
	//Check if invalid Video Number an set reason to 1 and values of other attributes to indicate that video cannot be checked out.
	if(tempVideoMap.isEmpty()) 
	{	
		C.setReason(1);
		C.setTitle(null);
		C.setFine(0.0);
		tempCheckoutMap=null;
		return tempCheckoutMap;
	}
	
	Video Video1=tempVideoMap.get(videoNumber);
	String status=Video1.getStatus();
	Date dueDate;	
	
	//If status is not available or member who is trying to checkout has not reserved it, set reason to 3 and values of other attributes to indicate that video cannot be checked out.
	if (!(status.equals("A")||(status.equals("R")&& Video1.getMemberWhoReserved().equals(memberId))))
	{
		C.setReason(3);
		C.setTitle(null);
		C.setFine(0.0);
		tempCheckoutMap=null;
		return tempCheckoutMap;
		
	}	
	
	for(Map.Entry<String, CheckedOutItems> entry : checkoutMap.entrySet())
	{		
		CheckedOutItems COI=entry.getValue();			
		if(COI.getMemberId().equals(memberId) )
		{
			//tempCheckoutmap contains checked out list of this member.
			tempCheckoutMap.put(COI.getItemNumber(),COI); 
			
		}		
		
	}
	
	
	int count=0;
	double totalFine=0.0;
	
	double days;
	double tempFine;
	double dailyFine;
	double itemPrice;
	for(Map.Entry<String, CheckedOutItems> entry : tempCheckoutMap.entrySet())
	{
		Date currentDate=new Date();
		CheckedOutItems COI=entry.getValue();
		
		//Check if a book checked out by this member has a fine on it.
		if(((entry.getKey()).charAt(0)=='N')||((entry.getKey()).charAt(0)=='F'))
		{	
			tempBookMap=IM.queryByBookNumber(entry.getKey());		
			Book Book2=tempBookMap.get(entry.getKey());				
			
			dailyFine = Book2.getBookDailyFine();
			itemPrice = Book2.getPrice();
					
		} else {
			//Check if a video checked out by the member has a fine on it.
			tempVideoMap=IM.queryByVideoNumber(entry.getKey());		
			Video Video2=tempVideoMap.get(entry.getKey());			
			
			dailyFine = Video2.getVideoDailyFine();
			itemPrice = Video2.getPrice();	
			
			//Counting the number of videos checked out by this member.
			count++;
		}
		days=(currentDate.getTime()-COI.getDueDate().getTime())/(1000 * 60 * 60 * 24);			
		tempFine=(days*dailyFine);
	
		if(tempFine>itemPrice)
		{
			tempFine=itemPrice;
		}		
		else if (tempFine < 0)
			tempFine=0;
		COI.setFine(tempFine);
		checkoutMap.put(entry.getKey(),COI);
		totalFine=totalFine + tempFine; 
		
	}
	
	// If fine to be paid, set reason to 2 and values of other attributes to indicate that video cannot be checked out. 
	if(totalFine>0)
	{
	
		C.setReason(2);
		C.setTitle(null);
		C.setFine(totalFine);
		tempCheckoutMap=null;
		return tempCheckoutMap;
	}
	//Check if exceeded no of videos limit. Set reason to 4 and values of other attributes to indicate that video cannot be checked out. 
	if ( count >=2)
	{
		 C.setReason(4); 
		 C.setTitle(null);
		 C.setFine(0.0);
		 tempCheckoutMap=null;
		 return tempCheckoutMap;
	}
	
	//Finally, add new check-out info to files.
	Date currentDate=new Date();
	Calendar c = Calendar.getInstance();
	// Now use today date.
	c.setTime(new Date()); 
	// Adding 7 days
	c.add(Calendar.DATE,Video1.getVideoMaxTime() ); 
	
	CheckedOutItems tempCOI=new CheckedOutItems(videoNumber,memberId,currentDate,c.getTime(),0.0);
	checkoutMap.put(videoNumber, tempCOI);
	
	try
	{
		    FileOutputStream fos=new FileOutputStream("CheckedOutList.txt");
	        PrintWriter pw=new PrintWriter(fos);

	        for(Map.Entry<String, CheckedOutItems> entry : checkoutMap.entrySet()){
	            pw.println(entry.getValue().writeToFile());
	        }

	        pw.flush();
	        pw.close();
	        fos.close();
	
	}catch(Exception e) {}
	Video1.setStatus("B");
	Video1.setMemberWhoBorrowed(memberId);
	IM.updateVideoListFile();
	
	//Change HistoryList.txt
	
	HistoryManager hm=new HistoryManager();
	hm.createNewTransaction(memberId,currentDate,videoNumber,0.0,"C");    
	
	//Looping through checkoutMap and putting total list of items checked out by this member.
	for(Map.Entry<String, CheckedOutItems> entry : checkoutMap.entrySet())
	{
		CheckedOutItems COI=entry.getValue();			
		if(COI.getMemberId().equals(memberId) )
		{
			tempCheckoutMap.put(COI.getItemNumber(),COI);
			
		}
	}
	

	return tempCheckoutMap;
	
	
}

//calculating total fine owed by member.
public double calcTotalFine(String memberId)
{
	ItemManager IM=new ItemManager();
	Map<String,Book> tempBookMap=new HashMap<String,Book>();
	Map<String,Video> tempVideoMap=new HashMap<String,Video>();
	Map<String,CheckedOutItems> tempCheckoutMap=new HashMap<String,CheckedOutItems>();
	double totalFine=0.0;
	double days;
	double tempFine;
	for(Map.Entry<String, CheckedOutItems> entry : checkoutMap.entrySet())
	{		
		CheckedOutItems COI=entry.getValue();			
		if(COI.getMemberId().equals(memberId) )
		{
			//tempCheckoumap contains checked out list of this member.
			tempCheckoutMap.put(COI.getItemNumber(),COI); 
			
		}
	}
	
		for(Map.Entry<String, CheckedOutItems> entry : tempCheckoutMap.entrySet())
		{
			
			CheckedOutItems COI=entry.getValue();
			
		//calculate fine owed by this member on books.	
		if( (entry.getKey().charAt(0)=='F') || (entry.getKey().charAt(0)=='N') )
		{
			tempBookMap=IM.queryByBookNumber(entry.getKey());
			Book Book2=tempBookMap.get(entry.getKey());
			Date currentDate=new Date();
			days=(currentDate.getTime()-COI.getDueDate().getTime())/(1000 * 60 * 60 * 24);
			tempFine=days*Book2.getBookDailyFine();
			if(tempFine>Book2.getPrice())
			{
				tempFine=Book2.getPrice();
			}
			else if(tempFine<0)
				tempFine=0;
			COI.setFine(tempFine);
			checkoutMap.put(entry.getKey(),COI);
			totalFine=totalFine + tempFine; 
			
		
		
		}
		//Calculate fine owed by this member on videos 
		else if(entry.getKey().charAt(0)=='V')
		{
			tempVideoMap=IM.queryByVideoNumber(entry.getKey());
			Video Video2=tempVideoMap.get(entry.getKey());
			Date currentDate=new Date();
			days=(currentDate.getTime()-COI.getDueDate().getTime())/(1000 * 60 * 60 * 24);
			tempFine=days*Video2.getVideoDailyFine();
			if(tempFine>Video2.getPrice())
			{
				tempFine=Video2.getPrice();
			}
			else if(tempFine<0)
				tempFine=0;
			COI.setFine(tempFine);
			checkoutMap.put(entry.getKey(),COI);
			totalFine=totalFine + tempFine; 
			
			
		}
		}
		return totalFine;
	}

//Function to return an item.
public int returnItem(String memberId,String itemNumber)
{	
	ItemManager IM=new ItemManager();
	
	//removing this item from checkoutMap.
	checkoutMap.remove(itemNumber);
	//Updating CheckedOutList.txt
	try 
	{
		    FileOutputStream fos=new FileOutputStream("CheckedOutList.txt");
	        PrintWriter pw=new PrintWriter(fos);

	        for(Map.Entry<String, CheckedOutItems> entry : checkoutMap.entrySet()){
	            pw.println(entry.getValue().writeToFile());
	        }

	        pw.flush();
	        pw.close();
	        fos.close();
	
	}catch(Exception e) {}
	
	//If item is a book, changing its status,and informing member who reserved it by email that it's available.
	if( (itemNumber.charAt(0)=='F') || (itemNumber.charAt(0)=='N') ) 
	{
	Map<String,Book> tempBookMap=new HashMap<String,Book>();
	tempBookMap=IM.queryByBookNumber(itemNumber);
	Book b=tempBookMap.get(itemNumber);
	if(b.getStatus().equals("BR"))
	{
		b.setStatus("R");
		String memId=b.getMemberWhoReserved();
		MemberManager M=new MemberManager();
		Map<String,Member> tempMemberMap=new HashMap<String,Member>();
		tempMemberMap=M.getMemberMap();
		String memName=tempMemberMap.get(memId).getName();
		String memEmail=tempMemberMap.get(memId).getEmail();
		//Send email
		SendEmail se=new SendEmail();		
		se.sendEmailNotification(memName, b.getTitle(), memEmail);
		
	}
	//Changing status of book if no one has reserved it.
	else if(b.getStatus().equals("B"))
	{
		b.setStatus("A");
	}
	b.setMemberWhoBorrowed("null");
	IM.updateBookListFile();
	HistoryManager h=new HistoryManager();
	Date currentDate=new Date();
	h.createNewTransaction(memberId,currentDate,itemNumber,0.0, "R");
	}
	//If item is a video, changing its status,and informing member who reserved it by email that it's available.
	else if ((itemNumber.charAt(0)=='V')) 
	{
		Map<String,Video> tempVideoMap=new HashMap<String,Video>();
		tempVideoMap=IM.queryByVideoNumber(itemNumber);
		Video v=tempVideoMap.get(itemNumber);
		if(v.getStatus().equals("BR"))
		{
			v.setStatus("R");
			String memId2=v.getMemberWhoReserved();
			MemberManager M2=new MemberManager();
			Map<String,Member> tempMemberMap2=new HashMap<String,Member>();
			tempMemberMap2=M2.getMemberMap();
			String memName2=tempMemberMap2.get(memId2).getName();
			String memEmail2=tempMemberMap2.get(memId2).getEmail();
			//Send email
			SendEmail se=new SendEmail();		
			se.sendEmailNotification(memName2, v.getTitle(), memEmail2);
		}
		else if(v.getStatus().equals("B"))
		{
			v.setStatus("A");
		}
		v.setMemberWhoBorrowed("null");
		IM.updateVideoListFile();
		HistoryManager h=new HistoryManager();
		Date currentDate=new Date();
		h.createNewTransaction(memberId,currentDate,itemNumber,0.0, "R");
	}
	return 1;
   
   }

//Function that returns all late items of a member.
public int returnAllLateItems(String memberId)
{
	ItemManager IM=new ItemManager();
	Map<String,Book> tempBookMap=new HashMap<String,Book>();
	Map<String,Video> tempVideoMap=new HashMap<String,Video>();
	Map<String,CheckedOutItems> tempCheckoutMap=new HashMap<String,CheckedOutItems>();
	double days;
	for(Map.Entry<String, CheckedOutItems> entry : checkoutMap.entrySet())
	{		
		CheckedOutItems COI=entry.getValue();			
		if(COI.getMemberId().equals(memberId) )
		{
			//tempCheckoumap contains checked out list of this member.
			tempCheckoutMap.put(COI.getItemNumber(),COI); 
			
		}
	}
	
	//Returning all late books of this member one by one
		for(Map.Entry<String, CheckedOutItems> entry : tempCheckoutMap.entrySet())
		{
			
			CheckedOutItems COI=entry.getValue();
			if( (entry.getKey().charAt(0)=='F') || (entry.getKey().charAt(0)=='N') )
			{
			tempBookMap=IM.queryByBookNumber(entry.getKey());
			Book Book2=tempBookMap.get(entry.getKey());
			Date currentDate=new Date();
			days=(currentDate.getTime()-COI.getDueDate().getTime())/(1000 * 60 * 60 * 24);
			
			if(days>0)
			{
			returnItem(memberId,entry.getKey());
			}	
			}
			//returning all late videos of this member one by one.
			else if((entry.getKey().charAt(0)=='V'))
			{
				tempVideoMap=IM.queryByVideoNumber(entry.getKey());
				Video Video2=tempVideoMap.get(entry.getKey());
				Date currentDate=new Date();
				days=(currentDate.getTime()-COI.getDueDate().getTime())/(1000 * 60 * 60 * 24);
				if(days>0)
				{
				returnItem(memberId,entry.getKey());
				}		
			}
		}

	return 1;
}

//Function to remove a lost item .
public int removeLostItem(String itemId) 
{
	//removing the entry from the checkoutMap.
	checkoutMap.remove(itemId);
	
	//Updating the CheckedOutList file.
	try
	{
		    FileOutputStream fos=new FileOutputStream("CheckedOutList.txt");
	        PrintWriter pw=new PrintWriter(fos);

	        for(Map.Entry<String, CheckedOutItems> entry : checkoutMap.entrySet()){
	            pw.println(entry.getValue().writeToFile());
	        }

	        pw.flush();
	        pw.close();
	        fos.close();
	
	}catch(Exception e) {}
	return 1;
}
//Admin function
//Function to return no of items checked out right now.
public int totalNoOfItemsCheckedOut() //
{

	return checkoutMap.size();
}


}