/*
Project Team J (COEN 275)
Name : Meenakshy Harikumar	SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013
*/
package storage;
import java.io.*;
import java.lang.*;
import java.util.*;

//Class ItemManager which keeps an updated book map and video map.
//Also contains all functions related to books and videos.
public class ItemManager
{
	//Declaring bookMap and videoMap which store information into and from files "BookList.txt" and "VideoList.txt".
	private Map<String,Book> bookMap=new HashMap<String,Book>();
	private Map<String,Video> videoMap=new HashMap<String,Video>();
	
	//Constructor which updates bookmap and videomap with latest info from the files.
	public ItemManager()
	{

	FileInputStream fin=null;
	InputStreamReader isr = null;
    BufferedReader br = null;
	try
	{
		
	fin = new FileInputStream("BookList.txt");
    isr = new InputStreamReader(fin, "UTF-8");
    br = new BufferedReader(isr);
    String line = br.readLine();
    
    
    while (line != null) {    	
        String[] toks = line.split("[||]+");
        
        //Converting the fields in BookList.txt to the format required to store in a Book object.
        int location=Integer.parseInt(toks[5]);
        double price=Double.parseDouble(toks[6]);
        
        Book Book1=new Book(toks[0],toks[1],toks[2],toks[3],toks[4],location,price,toks[7],toks[8]);
        bookMap.put(toks[0],Book1);
        line = br.readLine();
    }
    if (br != null)  { br.close();  }
    if (isr != null) { isr.close(); }
    if (fin != null) { fin.close(); }
    
	}
	catch (Exception e ){}
	
	try
	{
		fin = new FileInputStream("VideoList.txt");
	    isr = new InputStreamReader(fin, "UTF-8");
	    br = new BufferedReader(isr);
	    String line = br.readLine();
	    
	    while (line != null) {
	       
	        String[] toks = line.split("[||]+");
	        
	      //Converting the fields in VideoList.txt to the format required to store in a Video object.
	        int location=Integer.parseInt(toks[5]);
	        double price=Double.parseDouble(toks[6]);
	        
	        Video Video1=new Video(toks[0],toks[1],toks[2],toks[3],toks[4],location,price,toks[7],toks[8]);
	        videoMap.put(toks[0],Video1);
	        line = br.readLine();
	    }
	    if (br != null)  { br.close();  }
	    if (isr != null) { isr.close(); }
	    if (fin != null) { fin.close(); }
	}catch(Exception e) {}
	}
	
	//Function which returns the book map.
	public Map<String,Book> getBookMap()
	{
		return this.bookMap;
	}
	
	//Function which returns the video map.
	public Map<String,Video> getVideoMap()
	{
		return this.videoMap;
	}
	
	//Query By BookTitle	
	public Map<String,Book> queryByBookTitle(String Booktitle)
	{
	   Book Book2;
	   Map<String,Book> tempBookMap=new HashMap<String,Book>();

	   //Loops through the book map and puts required book into tempBookMap.
		for(Map.Entry<String, Book> entry : bookMap.entrySet())
		{
			Book2=entry.getValue();			
			if(Book2.getTitle().contains(Booktitle) && (Book2.getStatus().equals("A") ||Book2.getStatus().equals("R")||Book2.getStatus().equals("B")||Book2.getStatus().equals("BR") ))
			{
				tempBookMap.put(Book2.getBookNumber(),Book2);
				
			}
		}
		return tempBookMap;
	}
	
	//Query by Videotitle	
	public Map<String,Video> queryByVideoTitle(String Videotitle)
	{
	   Video Video2;
	   Map<String,Video> tempVideoMap=new HashMap<String,Video>();

	 //Loops through the video map and puts required video into tempVideoMap.
		for(Map.Entry<String, Video> entry : videoMap.entrySet())
		{
			Video2=entry.getValue();			
			if(Video2.getTitle().contains(Videotitle) && (Video2.getStatus().equals("A") ||Video2.getStatus().equals("R")||Video2.getStatus().equals("B")||Video2.getStatus().equals("BR") ))
			{
				tempVideoMap.put(Video2.getVideoNumber(),Video2);
				
			}
		}
		return tempVideoMap;
	}	
	
//Query by BookNumber
	public Map<String,Book> queryByBookNumber(String Booknumber)
	{
	   Book Book2;
	   Map<String,Book> tempBookMap=new HashMap<String,Book>();
	   
	   //Loops through the book map and puts required book into tempBookMap.
		for(Map.Entry<String, Book> entry : bookMap.entrySet())
		{
			Book2=entry.getValue();
			if(Book2.getBookNumber().equals(Booknumber))
			{
				tempBookMap.put(Book2.getBookNumber(),Book2);			
				
			}		
			
		}
		return tempBookMap;
	}
//QueryByVideoNumber
	public Map<String,Video> queryByVideoNumber(String Videonumber)
	{
	   Video Video2;
	   Map<String,Video> tempVideoMap=new HashMap<String,Video>();
	   
	   //Loops through the video map and puts required video into tempVideoMap.
		for(Map.Entry<String, Video> entry : videoMap.entrySet())
		{
			Video2=entry.getValue();
			if(Video2.getVideoNumber().equals(Videonumber))
			{
				tempVideoMap.put(Video2.getVideoNumber(),Video2);			
				
			}		
			
		}
		return tempVideoMap;
	}
//QueryByAuthor
	public Map<String,Book> queryByAuthor(String Author)
	{
	   Book Book2;
	   Map<String,Book> tempBookMap=new HashMap<String,Book>();
	   
	   //Loops through the book map and puts required book into tempBookMap.
		for(Map.Entry<String, Book> entry : bookMap.entrySet())
		{
			Book2=entry.getValue();
			if(Book2.getAuthor().equalsIgnoreCase(Author))
			{
				tempBookMap.put(Book2.getBookNumber(),Book2);			
			}		
		}
		return tempBookMap;
	}
	
//QueryByDirector
	public Map<String,Video> queryByDirector(String director)
	{
	   Video Video2;
	   Map<String,Video> tempVideoMap=new HashMap<String,Video>();
	   
	   //Loops through the video map and puts required video into tempVideoMap.
		for(Map.Entry<String, Video> entry : videoMap.entrySet())
		{
			Video2=entry.getValue();
			if(Video2.getDirector().equalsIgnoreCase(director))
			{
				tempVideoMap.put(Video2.getVideoNumber(),Video2);			
				
			}		
			
		}
		return tempVideoMap;
	}
	
//updateBookListFile
public void updateBookListFile()
{
	try
	{
		
	//Updates "BookList.txt" with the latest book map.
	FileOutputStream fos=new FileOutputStream("BookList.txt");
    PrintWriter pw=new PrintWriter(fos);
    for(Map.Entry<String, Book> entry : bookMap.entrySet()){
        pw.println(entry.getValue().writeToFile());
    }

    pw.flush();
    pw.close();
    fos.close();
	}catch(Exception e){}
}
//update VideoList file
public void updateVideoListFile()
{
	try
	{
		
	//Updates "VideoList.txt" with the latest video map.
	FileOutputStream fos=new FileOutputStream("VideoList.txt");
    PrintWriter pw=new PrintWriter(fos);
    for(Map.Entry<String, Video> entry : videoMap.entrySet()){
        pw.println(entry.getValue().writeToFile());
    }

    pw.flush();
    pw.close();
    fos.close();
	}catch(Exception e){}	
}

//Reserve a book
	public int reserveBook(String memberId,String bookNumber)
	{
		Book b=bookMap.get(bookNumber);
		
		//If this book does not exist, return 0.
		if(b==null)return 0;
		else
		{			
			//If this book has been borrowed, allow it to be reserved.
			if(b.getStatus().equals("B"))
			{
				b.setStatus("BR");
				b.setMemberWhoReserved(memberId);
				updateBookListFile();
				return 1;
			}
			return 0;
		}
		
	}
	
	//Reserve a video
	public int reserveVideo(String memberId,String videoNumber)
	{
		Video v=videoMap.get(videoNumber);
		
		//If this video does not exist, return 0.
		if(v==null)return 0;
		else
		{
			//If this video has been borrowed, allow it to be reserved.
			if(v.getStatus().equals("B"))
			{
				v.setStatus("BR");
				v.setMemberWhoReserved(memberId);
				updateVideoListFile();
				return 1;
			}
			return 0;
		}
		
	}
	
//Member claims an item is lost. 	
	public double claimLostItem(String memberId, String itemId)
	{
		double fine=0.0;
		
		//If item is a fiction or non-fiction book, calculate its price and return that as fine to be paid.
		if( (itemId.charAt(0)=='F') || (itemId.charAt(0)=='N') )
		{
		Map<String,Book> tempBookMap2=new HashMap<String,Book>(); 
		 tempBookMap2=queryByBookNumber(itemId);
		 if(tempBookMap2.isEmpty()) return -1;
		 Book Book1=tempBookMap2.get(itemId);
		 if(Book1.getMemberWhoBorrowed().equals(memberId))
		 {
         fine=Book1.getPrice();
         return fine;
		 }
		 else return -1;
		}
		
		//If item is a video, calculate its price and return that as fine to be paid.
		if ( itemId.charAt(0)=='V')
		{
			Map<String,Video> tempVideoMap2=new HashMap<String,Video>(); 
			 tempVideoMap2=queryByVideoNumber(itemId);
			 if(tempVideoMap2.isEmpty()) return -1;
			 Video Video1=tempVideoMap2.get(itemId);			
			 if(Video1.getMemberWhoBorrowed().equals(memberId))
			 {
	         fine=Video1.getPrice();
	         return fine;
			 }
			 else return -1;
	    }
		return 0;
	}
	
	//Removing lost item from bookMap/videoMap.
	public int removeLostItem(String memberId,Date date,String itemId,double fine) 
	{
		//If item is a fiction or non-fiction book, remove it from the BookList.txt.
		if( (itemId.charAt(0)=='F') || (itemId.charAt(0)=='N') )
		{
		bookMap.remove(itemId);
		updateBookListFile();
		}
		
		//If item is a video, remove it from the VideoList.txt.
		else if ( itemId.charAt(0)=='V')
		{
			videoMap.remove(itemId);
			updateVideoListFile();			
		}
		HistoryManager H=new HistoryManager();
		H.createNewTransaction(memberId, date, itemId, fine, "F");
		return 1;
	}
	
	//To find total no of items in library.
	public int totalNoOfItemsInLib() 
	{
		int total=0;
		
		//Calculate and return size of book map plus video map.
		total=bookMap.size()+videoMap.size();
		return total;
	}
	
	//To find total no of fiction books in library.
	public int totalNoOfFicBooks()
	{
		int total=0;
	    int fiction=0;
	  //Calculate and return number of entries in book map that start with "F".
		for(Map.Entry<String, Book> entry : bookMap.entrySet())
		{
			if(entry.getKey().charAt(0)=='F')
				fiction++;				
		}
		return fiction;
	}
	
	//To find total no of non fiction books in library.
	public int totalNoOfNonFicBooks()
	{
		int total=0;
	    int nonfiction=0;
	    
	  //Calculate and return number of entries in book map that start with "N".
		for(Map.Entry<String, Book> entry : bookMap.entrySet())
		{
			if(entry.getKey().charAt(0)=='N')
				nonfiction++;				
		}
		return nonfiction;
	}
	
	//Function to find total number of videos in library.
	public int totalNoOfVideos()
	{
		//Return the size of the video map.
		return videoMap.size();
	}
	
	//Function to find the latest book to show on the moving banner in UI.
	public Book getLatestBook()
	{
		Book Book1=null;
		FileInputStream fin=null;
		InputStreamReader isr = null;
	    BufferedReader br = null;
		try
		{
		
		//Updating the book map with latest info from file.
		fin = new FileInputStream("BookList.txt");
	    isr = new InputStreamReader(fin, "UTF-8");
	    br = new BufferedReader(isr);
	    String line = br.readLine();
	    
	    
	    while (line != null) {    	
	        String[] toks = line.split("[||]+");
	        int location=Integer.parseInt(toks[5]);
	        double price=Double.parseDouble(toks[6]);
	        Book1=new Book(toks[0],toks[1],toks[2],toks[3],toks[4],location,price,toks[7],toks[8]);
	        line = br.readLine();
	    }
	    if (br != null)  { br.close();  }
	    if (isr != null) { isr.close(); }
	    if (fin != null) { fin.close(); }
	    
		}
		catch (Exception e ){}
		
		return Book1;
	}
	
	//Function to find the latest video to show on the moving banner in UI.
	public Video getLatestVideo()
	{
		Video Video1=null;
		FileInputStream fin=null;
		InputStreamReader isr = null;
	    BufferedReader br = null;
		try
		{
			//Updating the video map with latest info from file.
			fin = new FileInputStream("VideoList.txt");
		    isr = new InputStreamReader(fin, "UTF-8");
		    br = new BufferedReader(isr);
		    String line = br.readLine();
		    
		    while (line != null) {
		       
		        String[] toks = line.split("[||]+");
		        int location=Integer.parseInt(toks[5]);
		        double price=Double.parseDouble(toks[6]);
		        Video1=new Video(toks[0],toks[1],toks[2],toks[3],toks[4],location,price,toks[7],toks[8]);
		       // videoMap.put(toks[0],Video1);
		        line = br.readLine();
		    }
		    if (br != null)  { br.close();  }
		    if (isr != null) { isr.close(); }
		    if (fin != null) { fin.close(); }
		}catch(Exception e) {}
		
		return Video1;
	}
	
}