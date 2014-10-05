/*
Project Team J (COEN 275)
Name : Meenakshy Harikumar	SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013
*/
package storage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;

//Class MemberManager which keeps an updated member map.
//Also contains all functions related to members.
public class MemberManager
{
	//Declaring memberMap which stores information into and from file "MemberList.txt".
	private Map<String,Member> memberMap=new HashMap<String,Member>();	
	
	//Constructor which updates memberMap with latest info from the file.
	public MemberManager()
	{		
		FileInputStream fin=null;
		InputStreamReader isr = null;
	    BufferedReader br = null;
		try
		{
		fin = new FileInputStream("MemberList.txt");
	    isr = new InputStreamReader(fin, "UTF-8");
	    br = new BufferedReader(isr);
	    String line = br.readLine();
	    
	    while (line != null) {	       
	    	
	        String[] toks = line.split("[||]+");
	        
	      //Converting the date field in MemberList.txt to the format required to store in a member object.
	        DateFormat formatter ;
	        formatter = new SimpleDateFormat("dd-MMM-yy");	        
	        Date membershipStartDate = formatter.parse(toks[5]);
	       
	       Member Member1=new Member(toks[0],toks[1],toks[2],toks[3],toks[4],membershipStartDate,toks[6]);
	       memberMap.put(toks[0],Member1);
	        line = br.readLine();
	    }
	    if (br != null)  { br.close();  }
	    if (isr != null) { isr.close(); }
	    if (fin != null) { fin.close(); }
	    
		}
		catch (Exception e ){}
	}
	
	//Function to create a new member. 
	public String createNewMember(String name,String email,String phone,String address,Date membershipStartDate,String county)
	{
	   //Generating a member id of 5 characters.
	   String memberId="M"+String.format("%04d",memberMap.size()+1);
	   
		Member m=new Member(memberId,name,email,phone,address,membershipStartDate,county);
		memberMap.put(m.getMemberId(),m);
		
		//Adding the new member entry from memberMap into MemberList.txt.
		try
		{
		    FileOutputStream fos=new FileOutputStream("MemberList.txt");
		    PrintWriter pw=new PrintWriter(fos);
            for(Map.Entry<String, Member> entry : memberMap.entrySet())
            {
		            pw.println(entry.getValue().writeToFile());
		    }

		        pw.flush();
		        pw.close();
		        fos.close();
		}catch(Exception e) {}
		
		return m.getMemberId();
	}
	
	//Function to check if a member exists.
	public int checkIfMember(String memberId)
	{
		Member m=memberMap.get(memberId);
		if(m==null)return 0;
		else return 1;
	}
	
	//To find total no of members.
	public int totalNoOfMembers() 
	{
		return memberMap.size();
		
	}
	
	//Function to return the member map.
    public Map<String,Member> getMemberMap()
    {
	return memberMap;
    }
	
}