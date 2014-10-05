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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

//Class HistoryManager which keeps an updated history map.
//Also contains all functions related to transaction history.
public class HistoryManager
{
	//Declaring historyMap which stores information into and from file "HistoryList.txt".
		private Map<String,History> historyMap=new HashMap<String,History>();	
		
		//Constructor which updates historyMap with latest info from the file.
		public HistoryManager()
		{		
			FileInputStream fin=null;
			InputStreamReader isr = null;
		    BufferedReader br = null;
			try
			{
			fin = new FileInputStream("HistoryList.txt");
		    isr = new InputStreamReader(fin, "UTF-8");
		    br = new BufferedReader(isr);
		    String line = br.readLine();
		    
		    while (line != null) {	       
		    	
		        String[] toks = line.split("[||]+");
		        
		        //Converting the fields in HistoryList.txt to the format required to store in a History object.
		       double fine=Double.parseDouble(toks[4]);        
		        DateFormat formatter ;
		        formatter = new SimpleDateFormat("dd-MMM-yy");	        
		        Date date = formatter.parse(toks[2]);
		       
		       History History1=new History(toks[0],toks[1],date,toks[3],fine,toks[5]);
		       historyMap.put(toks[0],History1);
		        line = br.readLine();
		    }
		    if (br != null)  { br.close();  }
		    if (isr != null) { isr.close(); }
		    if (fin != null) { fin.close(); }
		    
			}
			catch (Exception e ){}
	}
		
		//Function to update the "HistoryList.txt" file.
		public void updateHistoryListFile()
		{
			try
			{
			FileOutputStream fos=new FileOutputStream("HistoryList.txt");
		    PrintWriter pw=new PrintWriter(fos);
		    for(Map.Entry<String, History> entry : historyMap.entrySet()){
		        pw.println(entry.getValue().writeToFile());
		    }

		    pw.flush();
		    pw.close();
		    fos.close();
			}catch(Exception e){}
		}
		
		//Function to create a new transaction entry in the Historylist.txt file.
		public void createNewTransaction(String memberId,Date date,String itemId,double fine, String type)
		{
			String transactionId=String.format("%04d",historyMap.size()+1);
			History h=new History(transactionId,memberId,date,itemId,fine,type);
			historyMap.put(h.getTransactionId(),h);
			try
			{
			    FileOutputStream fos=new FileOutputStream("HistoryList.txt");
			        PrintWriter pw=new PrintWriter(fos);

			        for(Map.Entry<String, History> entry : historyMap.entrySet()){
			            pw.println(entry.getValue().writeToFile());
			        }

			        pw.flush();
			        pw.close();
			        fos.close();
			}catch(Exception e) {}
			
		}
		
		//Function to query the transaction history within of a member within a specified period of time.
		public Map<String,History> queryTransactionHistory(String memberId,Date date1,Date date2)
		{
			Map<String,History> tempHistoryMap=new HashMap<String,History>();
			for(Map.Entry<String, History> entry : historyMap.entrySet())
			{		
				History h=entry.getValue();			
				if( (h.getMemberId().equals(memberId)) && (h.getDate().compareTo(date1)>=0) && (h.getDate().compareTo(date2)<=0) )
				{
					//tempHistorymap contains transaction history of this member  within the specified period of time.
					tempHistoryMap.put(h.getTransactionId(),h); 
					
				}			
			}			
			return tempHistoryMap;
		}
		
		//Function to find the most popular/checked out item in the library.
		public String mostPopularItem(Date date1, Date date2)
		{
			ItemManager IM=new ItemManager();
			int highestCountBook=0;
			int countBook=0;
			int highestCountVideo=0;
			int countVideo=0;
			String itemId=null;
			String bookId=null;
			String videoId=null;
			String title=null;
			History h2=null;
			History h3=null;
			
			//Extracting entries within specified period of time.
			Map<String,History> tempHistoryMap=new HashMap<String,History>(); 
			for(Map.Entry<String, History> entry : historyMap.entrySet())
			{		
				History h=entry.getValue();	
				if( (h.getDate().compareTo(date1)>=0) && (h.getDate().compareTo(date2)<=0) )
				{
					tempHistoryMap.put(entry.getKey(), h);
				}
			}	
			
			//Searching Bookmap.
			Map<String,Book> tempBookMap=new HashMap<String,Book>();
			tempBookMap=IM.getBookMap();
			
			for(Map.Entry<String, Book> entry : tempBookMap.entrySet())
			{
				countBook=0;
				for(Map.Entry<String, History> entry2 : tempHistoryMap.entrySet())
				{
					h2=entry2.getValue();
					if( (entry.getKey().equals(h2.getItemId())) && (h2.getType().equals("C")) )
					{
						countBook++;						
					}
					
					if(countBook>highestCountBook)
					{
						highestCountBook=countBook;
						bookId=h2.getItemId();
					}
				}		
				
			}
			
			//Searching Videomap
			Map<String,Video> tempVideoMap=new HashMap<String,Video>();
			tempVideoMap=IM.getVideoMap();
			
			for(Map.Entry<String, Video> entry : tempVideoMap.entrySet())
			{
				
				countVideo=0;
				for(Map.Entry<String, History> entry2 : tempHistoryMap.entrySet())
				{
					h3=entry2.getValue();
					
					if( (entry.getKey().equals(h3.getItemId())) && (h3.getType().equals("C")) )					
					{
						countVideo++;						
					}
					
					if(countVideo>highestCountVideo)
					{
						highestCountVideo=countVideo;						
						videoId=h3.getItemId();
					}
				}				
				
			}
			
			//Comparing the number of times most popular book has been checked out with the number of times most popular video has been checked out. 
			if(highestCountBook>highestCountVideo) 
				itemId=bookId;
			else  
				itemId=videoId;
			
			// Getting title of popular book. 
			if( (itemId.charAt(0)=='F') || (itemId.charAt(0)=='N' ) )
					{
				      Book b;
				      
				      for(Map.Entry<String, Book> entry : tempBookMap.entrySet())
				      {
				    	  b=entry.getValue();				      
				    	  if(b.getBookNumber().equals(itemId))
				    	  {
				    		  title=b.getTitle();
				    	  }
				      }
					}
			// Getting title of popular video. 
			else if((itemId.charAt(0)=='V') )
			{
				Video v;
				for(Map.Entry<String, Video> entry : tempVideoMap.entrySet())
			      {
					v=entry.getValue();
					if(v.getVideoNumber().equals(itemId))
					{
						title=v.getTitle();
					}
			      }
			}
			//Return item title
			return title;
		}
	
		//Function to calculate the total fines collected within a specified period of time.
   public double totalFinesCollected(Date date1,Date date2)
		{
			double totalFine=0.0;
			
			//Extracting entries within specified period of time.
			Map<String,History> tempHistoryMap=new HashMap<String,History>(); 
			for(Map.Entry<String, History> entry : historyMap.entrySet())
			{		
				History h=entry.getValue();	
				if( (h.getDate().compareTo(date1)>=0) && (h.getDate().compareTo(date2)<=0) )
				{
					tempHistoryMap.put(entry.getKey(), h);
				}
			}	
			
			//Calculate total fines collected.
			for(Map.Entry<String, History> entry : tempHistoryMap.entrySet())
			{
				
				History h=entry.getValue();	
				if( (h.getType().equals("F")) )
				{					
					totalFine=totalFine + h.getFine();
				}
			}
			
			return totalFine;
		}
   
}